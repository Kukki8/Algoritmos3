import java.util.Hashtable;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class GrafoDirigido implements Grafo{
    	/*Atributos de la clase GrafoDirigido
	 * listaVertices: Lista de lista de vertices.Es la representacion como lista de adyacencia del grafo. Los primeros elementos (vertices) de cada lista,
     * seran los representantes de la misma, siendo los demas, sus adyacencias,mas especificamente, sus sucesores.
	 * Arcos: lista de todos los arcos que posee el grafo
	 */
    
    public LinkedList<Arco> listaArcos;
    public LinkedList<LinkedList<Vertice>> listaVertices;
    public Hashtable<String , Integer> lineas;

    public GrafoDirigido(){
        this.listaArcos = new LinkedList<Arco>();
        this.listaVertices = new LinkedList<LinkedList<Vertice>>();
        this.lineas = new Hashtable<String , Integer>() ;
    }

    public GrafoDirigido crearGrafoDirigido(){
       return new GrafoDirigido();

    }
 
    //Si no existe, agrega un arco a la lista y retorna true. En caso contrario, retorna false.
    //Este metodo solo requiere el un objeto arco.
    public boolean agregarArco(Arco a){

        if(!estaArco(a.obtenerExtremoInicial().obtenerNombre(),a.obtenerExtremoFinal().obtenerNombre(), a.obtenerTipo())){
            listaArcos.add(a);
            return true;
           
        }
        return false;
        
    }
    //Si no existe, agrega un arco a la lista y retorna true. En caso contrario, retorna false.
    //Este metodo requiere todos los atributos del objeto arco, ya que crea un arco nuevo.
    public  boolean agregarArco(String vi, String vf, int tipo, double peso){
        
        if(!estaArco(vi, vf, tipo)){
            if(estaVertice(vi) && estaVertice(vf)){
                Vertice nuevov1 = obtenerVertice(vi);
                Vertice nuevov2 = obtenerVertice(vf);
                Arco nuevoArco = new Arco(peso ,tipo, nuevov1, nuevov2);
                listaArcos.add(nuevoArco);
    
                for(LinkedList<Vertice> v : listaVertices){
                    if(v.get(0).obtenerId() == nuevov1.obtenerId()){
                        if(!v.contains(nuevov2)){
                            v.add(nuevov2);
                        }

                        break;
                    }
                }
                return true;
            }
            return false;   
        }
        return false;

    }

    public  boolean agregarArco(int vi, int vf, int tipo, double peso){             //Metodo extra: agrega arcos por ID de los vertices
        
        if(!estaArco(vi, vf, tipo)){
            if(estaVertice(vi) && estaVertice(vf)){
                Vertice nuevov1 = obtenerVertice(vi);
                Vertice nuevov2 = obtenerVertice(vf);
                Arco nuevoArco = new Arco(peso ,tipo, nuevov1, nuevov2);
                listaArcos.add(nuevoArco);
    
                for(LinkedList<Vertice> v : listaVertices){
                    if(v.get(0).obtenerId() == nuevov1.obtenerId()){
                        if(!v.contains(nuevov2)){
                            v.add(nuevov2);
                        }
                        break;
                    }
                }
                return true;
            }
            return false;   
        }
        return false;

    }

    //Verificamos si existe el arco en el grafo, recorriendo la lista y comparando todos los atributos de estos mismos con el arco en cuestion.
    //Si existe, retorna true. En caso contrario, retorna false.
    public boolean estaArco(String vi, String vf, int tipo){

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerNombre() == vi && b.obtenerExtremoFinal().obtenerNombre() == vf && b.obtenerTipo() == tipo){
                    return true;
                }
            }
        }else if(!estaVertice(vi) && estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque el vertice " + vi + " no existe.");
            return false;

        }else if(estaVertice(vi) && !estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque el vertice " + vf + " no existe.");
            return false;

        }else if(!estaVertice(vi) && !estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque ninguno de los vertices existe." );
            return false;  
        }
        return false;
    }

    public boolean estaArco(int vi, int vf, int tipo){

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerId() == vi && b.obtenerExtremoFinal().obtenerId() == vf && b.obtenerTipo() == tipo){
                    return true;
                }
            }
        }else if(!estaVertice(vi) && estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque el vertice " + vi + " no existe.");
            return false;

        }else if(estaVertice(vi) && !estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque el vertice " + vf + " no existe.");
            return false;

        }else if(!estaVertice(vi) && !estaVertice(vf)){
            System.out.println("No se puede agregar el arco porque ninguno de los vertices existe." );
            return false;  
        }
        return false;
    }

    //Verifica si el arco existe, antes de eliminarlo.
    //Si existe, lo elimina de la lista, verifica si existen vertices unidos por el; de ser si, los desenlaza, sacandolos de las respectivas listas adyacentes.
    //Finalmente, retorna true.
    //En caso contrario, retorna false.
    public boolean eliminarArco(String vi, String vf, int tipo){

        if(estaArco(vi, vf, tipo)){

            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerNombre() == vi && b.obtenerExtremoFinal().obtenerNombre() == vf && b.obtenerTipo() == tipo){
                    listaArcos.remove(b);
                    for(LinkedList<Vertice> listaActual : listaVertices){
                        if(listaActual.get(0).obtenerNombre().equals(vi)){
                            for(Vertice v : listaActual){
                                if(v.obtenerNombre().equals(vf)){
                                    eliminarVertice(v.obtenerId());
                                }
                            }return true;
                        }      
                    }
                }
            }
        }
        return false;
        
    }

    public boolean eliminarArco(Arco arco){

        int id1 = arco.obtenerExtremoInicial().obtenerId();
        int id2 = arco.obtenerExtremoFinal().obtenerId();
        int tipo = arco.obtenerTipo();
        if(estaArco(id1,id2,tipo)){

            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerId() == id1 && b.obtenerExtremoFinal().obtenerId() == id2 && b.obtenerTipo() == tipo){
                    listaArcos.remove(b);
                    for(LinkedList<Vertice> listaActual : listaVertices){
                        if(listaActual.get(0).obtenerId() == id1){
                            LinkedList<Vertice> aEliminar = new LinkedList<Vertice>();

                            for(Vertice v : listaActual){
                                if(v.obtenerId() == id2){
                                    aEliminar.add(v);
                                }
                            }

                            for(Vertice c : aEliminar){
                                listaActual.remove(c);

                            }
                            return true;
                        }      
                    }
                }
            }
        }
        return false;
        
    }

    //Verifica si existe el arco en el grafo, dados los atributos correspondientes.
    //Si existe, lo retorna. En caso contrario, se lanza una excepcion.
    public Arco obtenerArco(String vi, String vf, int tipo){

        if(estaVertice(vi) && estaVertice(vf)){

            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerNombre().equals(vi) && b.obtenerExtremoFinal().obtenerNombre().equals(vf) && b.obtenerTipo() == tipo){
                    return b;
                }
            }
        }
        throw new NoSuchElementException("No existe un arco con vertice inicial " + vi + ", vertice final " + vf + " de tipo " + tipo);

    }

    //Si ambos vertices existen. De ser asi, buscamos un arco cuyo vertice final sea el dado y al conseguirlo, aumentamos el contador.
    //Si no, el vertice esta aislado, se mantiene el contador en 0.
    public int gradoInterior(String vi, String vf, int tipo){
        int GradoIn=0;

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerNombre() == vf && A.obtenerTipo() == tipo){ 
                    GradoIn+=1;    
                }
            }
            return GradoIn;
        }
        throw new NoSuchElementException("Los datos no son validos." ); 
    }

    //Si ambos vertices existen. De ser asi, buscamos un arco cuyo vertice inicial sea el dado y al conseguirlo, aumentamos el contador.
    //Si no, el vertice esta aislado, se mantiene el contador en 0. 

    public int gradoExterior(String vi, String vf, int tipo){
        int GradoExt=0;

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco A: listaArcos){
                if(A.obtenerExtremoInicial().obtenerNombre() == vi && A.obtenerTipo() == tipo){
                    GradoExt+=1;
                }
            }
            return GradoExt;
        }  
        throw new NoSuchElementException("Los datos no son validos." );  
    }

    //Verificamos si esta el vertice. De ser asi, buscamos arcos que lo posean como vertice inicial y agregamos a la lista el vertice final de dicho arco.
    //En caso contrario, se lanza una excepcion.

    public LinkedList<Vertice> sucesores(int id){

        if(estaVertice(id)){
            LinkedList<Vertice> sucesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoInicial().obtenerId() == id){
                    sucesores.add(A.obtenerExtremoFinal());
                }
            }
            return sucesores;
        }  
        throw new NoSuchElementException("No existe un vertice con el id "  + id + "."); 
    
    }


    //Verificamos si esta el vertice. De ser asi, buscamos arcos que lo posean como vertice inicial y agregamos a la lista el vertice final de dicho arco.
    //En caso contrario, se lanza una excepcion.

    public LinkedList<Vertice> sucesores(String nombre){

        if(estaVertice(nombre)){
            LinkedList<Vertice> sucesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoInicial().obtenerNombre() == nombre){
                    sucesores.add(A.obtenerExtremoFinal());
                }
            }
            return sucesores;
        }
        throw new NoSuchElementException("No existe un vertice llamado "  + nombre + ".");
    }

    //Verificamos si esta el vertice. De ser asi, buscamos arcos que lo posean como vertice final y agregamos a la lista el vertice inicial de dicho arco.
    //En caso contrario, se lanza una excepcion.
    public LinkedList<Vertice> predecesores(int id){

        if(estaVertice(id)){
            LinkedList<Vertice> predecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerId() == id){
                    predecesores.add(A.obtenerExtremoInicial());
                }
            }
            return predecesores;
        }
        throw new NoSuchElementException("No existe un vertice con el id "  + id + ".");
    }

    //Verificamos si esta el vertice. De ser asi, buscamos arcos que lo posean como vertice final y agregamos a la lista el vertice inicial de dicho arco.
    //En caso contrario, se lanza una excepcion. 

    public LinkedList<Vertice> predecesores(String nombre){


        if(estaVertice(nombre)){
            LinkedList<Vertice> predecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerNombre() == nombre){
                    predecesores.add(A.obtenerExtremoInicial());
                }
            }
            return predecesores;
        }
        throw new NoSuchElementException("No existe un vertice llamado "  + nombre + ".");

        
    }

//########################################################################################################################
   //Leemos archivo para cargar el grafo en cuestion, siguiendo el formato especificado.


    @Override
    public boolean cargarGrafo(String Archivo) throws IOException{
        try{ 
            
            BufferedReader lector = new BufferedReader(new FileReader(Archivo));   //Escaner
            String lineaActual = lector.readLine();                               
            String orientacion = lineaActual;                                      //Primera linea representa la orientacion
            System.out.println("Cargando grafo " + orientacion);
            lineaActual = lector.readLine();
            int n = Integer.parseInt(lineaActual);                                  //Segunda linea representa el numero de vertices
            lineaActual = lector.readLine();
            int m = Integer.parseInt(lineaActual);                                  //Tercera linea representa el numero de arcos
    
            //GrafoDirigido nuevoGrafo = crearGrafoDirigido();
    
            for(int vertices = 0 ; vertices < n ; vertices++){                      //Las siguientes filas, tantas como vertices existan, representan
                lineaActual = lector.readLine();
                String [] datos = lineaActual.split(" ");                     // sus atributos individuales. Cramos un arreglo de strings, quitando el
                int id = Integer.parseInt(datos[0]);                                // espacio entre ellos.

                if(datos.length==5) {                                               // Cada vertice debe tener 5 atributos:
                    String nombre = datos[1];                                       // Nombre
                    double x = Double.parseDouble(datos[2]);                        // Coordenada x
                    double y = Double.parseDouble(datos[3]);                        // Coordenada y
                    double peso = Double.parseDouble(datos[4]);                     // Peso
        
                    //nuevoGrafo.agregarVertice(id, nombre, x, y, peso);              //Agregamos los vertices con dichos atributos.
                    agregarVertice(id, nombre, x, y, peso); 
                }else{
                    throw new IllegalArgumentException(" No es una linea de vertices valida");    //En caso de que exceda la cantidad de atributos.
                }
            }
    
            for(int arcos = 0 ; arcos < m ; arcos++){                               //Las siguientes filas, tantos arcos  existan, representan
                lineaActual = lector.readLine();                          
                String [] datos = lineaActual.split(" ");                     // sus atributos individuales. Cramos un arreglo de strings, quitando el
                if(datos.length==4) {	                                            // espacio entre ellos. Cada arco debe tener 4 atributos:
                    String vi = datos[0];                                           // Vertice inicial
                    String vf = datos[1];                                           // Vertice final
                    int tipo = Integer.parseInt(datos[2]);                          // Tipo de arco
                    double peso = Double.parseDouble(datos[3]);                     // Peso del arco
        
                    //nuevoGrafo.agregarArco(vi, vf, tipo, peso);
                    agregarArco(vi, vf, tipo, peso);
                }else{
                    throw new IllegalArgumentException("No es una linea de arcos valida");      //En caso de que exceda la cantidad de atributos.
                }
            }
    
            lector.close();
            return true;

        }catch(IOException e){
			return false;
        }
       
    }
	// metodo getter que devuelve un valor bolleano dependienso si carrga cada atributo del mapa bien o no.
    public boolean cargarGrafoMetro(String archivo) throws IOException{
        try{
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea = lector.readLine();
            int n = Integer.parseInt(lector.readLine());
            int m = Integer.parseInt(lector.readLine());

            for(int i = 0; i < n ; i++){

                linea = lector.readLine();
                String [] datos = linea.split("\\s+");                     // sus atributos individuales. Cramos un arreglo de strings, quitando el
                int id = Integer.parseInt(datos[0]);                                // espacio entre ellos.
                
                if(datos.length==5) {                                               // Cada vertice debe tener 5 atributos:
                    String nombre = datos[1];                                       // Nombre
                    double latitud = Double.parseDouble(datos[2]);                  // Latitud
                    double longitud = Double.parseDouble(datos[3]);                 // Longitud
                    double tiempo = Double.parseDouble(datos[4]);                   // Tiempo
        
                    agregarVertice(id, nombre, latitud, longitud, tiempo); 
                }else{
                    throw new IllegalArgumentException(" No es una linea de vertices valida");    //En caso de que exceda la cantidad de atributos.
                } 


            }

            int contador = 0;

            for(int arcos = 0 ; arcos < m ; arcos++){                               //Las siguientes filas, tantos arcos  existan, representan
                linea = lector.readLine();                          
                String [] datos = linea.split("\\s+");                     // sus atributos individuales. Cramos un arreglo de strings, quitando el
                if(datos.length==4) {	                                            // espacio entre ellos. Cada arco debe tener 4 atributos:
                    int pi = Integer.parseInt(datos[0]);                                           // Parada inicial
                    int pf = Integer.parseInt(datos[1]);                                           // Parada final
                    String tipo = datos[2];                          // Tramo/color de arco
                    int clave = 0;

                    if(lineas.containsKey(tipo)){                   //Verifica si la linea ya existe en la tabla de hash de las lineas
                        clave = lineas.get(tipo);                   //Si ya existe, buscamos su clave.
                
                    }else{                                          //En caso de no existir la linea en la tabla, se le asigna una clave,
                        clave = contador ;                          //se agrega a la tabla de hash (lineas) y
                        lineas.put(tipo,contador);                  // aumentamos al contador
                        contador += 1;
                    }

                    double tiempo = Double.parseDouble(datos[3]);                     // Tiempo del arco
        
                    agregarArco(pi, pf, clave, tiempo);
                }else{
                    throw new IllegalArgumentException("No es una linea de arcos valida");      //En caso de que exceda la cantidad de atributos.
                }
            }



            lector.close();
            return true;
        }catch(IOException e){
		    return false;
		}

    }
	// Metodo getter que retorna el grafo inducido dependiendo de las lienas activas.
    public GrafoDirigido cargarGrafoInducido(String archivo) throws IOException{

        try{
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            GrafoDirigido inducido = (GrafoDirigido) clonar();
            String linea = lector.readLine();
            
            while( linea != null){
                int clave = lineas.get(linea);
                inducido.lineas.put(linea,clave);               //Aqui agregamos todas las lineas
                linea = lector.readLine();
            }

            LinkedList<Arco> aEliminar = new LinkedList<Arco>();

            for(Arco a : inducido.listaArcos){
                int tipo = a.obtenerTipo();

                if(!inducido.lineas.contains(tipo)){            //Buscamos aquellas lineas que no pertenezcan al grafo inducido y las agregamos a
                    aEliminar.add(a);                           //la lista de lineas que vamos a eliminar
                }
            }

            for(Arco b : aEliminar){
                inducido.eliminarArco(b);                        //Eliminamos todas las lineas de nuestra lista aEliminar que esten en el inducido
            }
            
            lector.close();
            return inducido;

        }catch(IOException e){
		    return null;
		}


    }
        
    //Retorna el tamano de la lista de vertices, es decir, la cantidad de vertices en total.
    @Override
    public int numeroDeVertices(){         
        return listaVertices.size();
    }

    //Retorna el tamano de la lsta de arcos, es decir, la cantidad de arcos.
    @Override
    public int numeroDeLados(){
        return listaArcos.size();
    }

    //Si el vertice no existe, introduce una nueva lista a la lista de listas y retorna true.
    //En caso contrario, retorna false.
    @Override
    public boolean agregarVertice(Vertice v){
        if(!estaVertice(v.obtenerId())){
            LinkedList<Vertice> nuevoVert =new LinkedList<Vertice>();
            nuevoVert.add(v);
            listaVertices.add(nuevoVert);            
        }
        return false;
    }

    //Si el vertice no existe, introduce una nueva lista a la lista de listas y retorna true. 
    //Recibe todos los atributos, creando un vertice nuevo.
    //En caso contrario, retorna false.
    @Override
    public boolean agregarVertice(int id, String nombre,double x, double y,double p){

        if(!estaVertice(id)){
            Vertice v = new Vertice(id, nombre, x, y, p);
            LinkedList<Vertice> nuevoVert =new LinkedList<Vertice>();
            nuevoVert.add(v);
            listaVertices.add(nuevoVert);
            return true;
        }
        return false;
 
    }

    //A traves del id, verifica si existe el vertice. De ser asi, lo retorna.

    @Override
    public Vertice obtenerVertice(int id){

        if(estaVertice(id)){
            for(LinkedList<Vertice> v : listaVertices){
                if(v.get(0).obtenerId() == id){
                    return v.get(0);
                }
            }
        }
        throw new NoSuchElementException("No existe un vertice en el grafo con" + id + " de id.");
    }

        //A traves del nombre, verifica si existe el vertice. De ser asi, lo retorna.
    public Vertice obtenerVertice(String nombre){

        if(estaVertice(nombre)){
            for(LinkedList<Vertice> v : listaVertices){
                if(v.get(0).obtenerNombre().equals(nombre) ){
                    return v.get(0);
                }
            }
        }
        throw new NoSuchElementException("No existe un vertice en el grafo llamado " + nombre + ".");
    }

        //A traves del id, verifica si existe el vertice. De ser asi, retorna true.
        //En caso contrario, retorna false.
    @Override
    public boolean estaVertice(int id){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).obtenerId() == id){
                return true;
            }
        }
        return false;
    }

        //A traves del nombre, verifica si existe el vertice. De ser asi, retorna true.
        //En caso contrario, retorna false.

    public boolean estaVertice(String nombre){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).obtenerNombre().equals(nombre) ){
                return true;
            }
        }
        return false;
    }

    //Si el vertice existe, primero eliminamos la lista de la lista que lo representa. Luego, lo buscamos en el resto de las listas,
    // y al conseguirlo, se elimina ese elemento de la lista. Finalmente, si el vertice fue un extremo inicial o final de algun arco, eliminamos dicho arco
    //y retornamos true.
    //En caso contrario, se retorna false.
    @Override
    public boolean eliminarVertice(int id){

        if(estaVertice(id)){

            for(LinkedList<Vertice> listaActual : listaVertices){
                if(listaActual.get(0).obtenerId() == id){
                    listaVertices.remove(listaActual);
                }else{
                    for(Vertice v : listaActual){
                        if(v.obtenerId() == id){
                            listaActual.remove(v);
                        }
                    }
                }
            }
                
            for(Arco AR : listaArcos){
                if(id == AR.obtenerExtremoInicial().obtenerId() || id == AR.obtenerExtremoFinal().obtenerId()){
                    listaArcos.remove(AR);
                }
            }
            return true;
        }
        return false;
    }

    // Creamos una lista de enteros donde colocamos los id de todos los vertices del grafo.

    @Override
    public LinkedList<Integer> vertices(){
        LinkedList<Integer> V = new LinkedList<Integer>();
        for(LinkedList<Vertice> L : listaVertices){
            V.add(L.get(0).obtenerId());
        }
        return V;

    }

    //Creamos una lista de lados donde agregamos a todos los arcos.
    @Override
    public LinkedList<Lado> lados(){
        LinkedList<Lado> lados = new LinkedList<Lado>();
        lados.addAll(listaArcos);
        return lados;
    }

    //Si existe el vertice, calculamos su grado total, sumando su grado interior y exterior.
    //Buscamos su tipo para poder llamar a la funcion respectiva, luego de verificar si es un vertice inicial, final o ambos.
    //En caso contrario, lanza una excepcion.
    @Override
    public int grado(int id){

        int Grado = 0;
        int Tipo;

        if(estaVertice(id)){
            for(Arco a : listaArcos){
                    if(a.obtenerExtremoInicial().obtenerId() == id){
                        Tipo = a.obtenerTipo();
                        Grado += gradoExterior(a.obtenerExtremoInicial().obtenerNombre(), a.obtenerExtremoFinal().obtenerNombre(), Tipo);

                    }if(a.obtenerExtremoFinal().obtenerId()  == id){
                        Tipo = a.obtenerTipo();
                        Grado += gradoInterior(a.obtenerExtremoInicial().obtenerNombre(), a.obtenerExtremoFinal().obtenerNombre(), Tipo);
                    }
            }
            return Grado;
        }
        throw new NoSuchElementException("No existe un vertice en el grafo con" + id + " de id.");
    }

    //Si el vertice existe, suma tanto sucesores como predecesores.
    //En caso contrario, lanza una excepcion.
    @Override
    public LinkedList<Vertice> adyacentes(int id){
        if(estaVertice(id)){
            LinkedList<Vertice> adyacentes = new LinkedList<Vertice>();
            adyacentes.addAll(sucesores(id));
            adyacentes.addAll(predecesores(id));
            return adyacentes;
        }
        throw new NoSuchElementException("No existe un vertice en el grafo con" + id + " de id.");
    }

    //Si el vertice existe, busca aquellos arcos de los cuales es vertice final y los agrega a la lista creada.
    // En caso contrario, lanza una excepcion.
    @Override
    public LinkedList<Lado> incidentes(int id){

        if(estaVertice(id)){
            LinkedList<Lado> incidentes = new LinkedList<Lado>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerId() == id){
                    incidentes.add(A);
                }
            }
            return incidentes;
        }
        throw new NoSuchElementException("No existe un vertice en el grafo con" + id + " de id.");

    }

    //Se crea un nuevo grafo y se recorre el grafo original. En primer lugar, se recorre la lista de listas, solo importando el representante de cada lista
    // y buscando sus atributos, los cuales se pasaran por valor al nuevo grafo, creando nuevos vertices en el.
    //Luego, recorremos la lista de arcos y pasamos los atributos del grafo original por valor, creando nuevos arcos en el grafo clonado.
    //Retionamos el grafo.
    @Override
    public Grafo clonar(){

        GrafoDirigido grafoClonado = new GrafoDirigido();
        for(LinkedList<Vertice> v : listaVertices){
            Vertice vRepresen = v.get(0);
            grafoClonado.agregarVertice(vRepresen.obtenerId(), vRepresen.obtenerNombre(), vRepresen.obtenerX(), vRepresen.obtenerY(),vRepresen.obtenerPeso());
        }
        
        for(Arco a: listaArcos){
            grafoClonado.agregarArco(a.obtenerExtremoInicial().obtenerNombre(), a.obtenerExtremoFinal().obtenerNombre(), a.obtenerTipo(), a.obtenerPeso());
        }
        return grafoClonado;
    }

    @Override
    public String toString(){
    
            String info = "";
    
            for(LinkedList<Vertice> listaActual: listaVertices){
                info += listaActual.get(0).obtenerNombre() + " ----> ";
    
                    for(Vertice v : listaActual){
                        info += "       "+ v.obtenerNombre() + " ";
                    }
                    info += "\n";
            }
            info += "\n" + "Vertices: " + "\n";
            for(LinkedList<Vertice> v : listaVertices){
                info += "\n" +"Info de vertice: " + v.get(0).obtenerNombre() + "\n" + v.get(0).toString() + "\n";
    
            }
            info += "Arcos: " + "\n";
            int i = 0;
            for(Arco arco : listaArcos){
                info += "\n" + "Info del arco: " + i + "\n"  + arco.toString() + "\n";
                i += 1;
            }
            return info;
        }
    
}
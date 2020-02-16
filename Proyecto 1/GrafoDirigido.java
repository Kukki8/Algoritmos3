import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class GrafoDirigido implements Grafo{
    
    public LinkedList<Arco> listaArcos;
    public LinkedList<LinkedList<Vertice>> listaVertices;

    public GrafoDirigido(){
        this.listaArcos = new LinkedList<Arco>();
        this.listaVertices = new LinkedList<LinkedList<Vertice>>();
    }

    public GrafoDirigido crearGrafoDirigido(){
       return new GrafoDirigido();

    }
 
    public boolean agregarArco(Arco a){

        if(!estaArco(a.obtenerExtremoInicial().obtenerNombre(),a.obtenerExtremoFinal().obtenerNombre(), a.obtenerTipo())){
            listaArcos.add(a);
            return true;
           
        }
        return false;
        
    }

    public  boolean agregarArco(String vi, String vf, int tipo, double peso){
        
        if(!estaArco(vi, vf, tipo)){
            if(estaVertice(vi) && estaVertice(vf)){
                Vertice nuevov1 = obtenerVertice(vi);
                Vertice nuevov2 = obtenerVertice(vf);
                Arco nuevoArco = new Arco(peso ,tipo, nuevov1, nuevov2);
                listaArcos.add(nuevoArco);
    
                for(LinkedList<Vertice> v : listaVertices){
                    if(v.get(0).obtenerId() == nuevov1.obtenerId()){
                        v.add(nuevov2);
                    }
                }
                return true;
            }
            return false;   
        }
        return false;

    }


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

    public boolean eliminarArco(String vi, String vf, int tipo){

        if(estaArco(vi, vf, tipo)){

            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerNombre() == vi && b.obtenerExtremoFinal().obtenerNombre() == vf && b.obtenerTipo() == tipo){
                    listaArcos.remove(b);
                    for(LinkedList<Vertice> listaActual : listaVertices){
                        if(listaActual.get(0).obtenerNombre().equals(vi)){
                            for(Vertice v : listaActual){
                                if(listaActual.get(0).obtenerNombre().equals(vi)){
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

    public Arco obtenerArco(String vi, String vf, int tipo){

        if(estaVertice(vi) && estaVertice(vf)){

            for(Arco b : listaArcos){
                if(b.obtenerExtremoInicial().obtenerNombre() == vi && b.obtenerExtremoFinal().obtenerNombre() == vf){
                    return b;
                }
            }
        }
        throw new NoSuchElementException("No existe un arco con vertice inicial " + vi + ", vertice final " + vf + " de tipo " + tipo);

    }

    public int gradoInterior(String vi, String vf, int tipo){
        int GradoIn=0;

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerNombre() == vf){
                    if(A.obtenerTipo() == tipo){
                        GradoIn+=1;
                    }
                }
            }
            return GradoIn;
        }
        throw new NoSuchElementException("Los datos no son validos." ); 
    }

    public int gradoExterior(String vi, String vf, int tipo){
        int GradoExt=0;

        if(estaVertice(vi) && estaVertice(vf)){
            for(Arco A: listaArcos){
                if(A.obtenerExtremoInicial().obtenerNombre() == vi){
                    if(A.obtenerTipo() == tipo){
                        GradoExt+=1;
                    }
                }
            }
            return GradoExt;
        }  
        throw new NoSuchElementException("Los datos no son validos." );  
    }

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

    public LinkedList<Vertice> antecesores(int id){

        if(estaVertice(id)){
            LinkedList<Vertice> antecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerId() == id){
                    antecesores.add(A.obtenerExtremoInicial());
                }
            }
            return antecesores;
        }
        throw new NoSuchElementException("No existe un vertice con el id "  + id + ".");
    }

    public LinkedList<Vertice> antecesores(String nombre){


        if(estaVertice(nombre)){
            LinkedList<Vertice> Antecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.obtenerExtremoFinal().obtenerNombre() == nombre){
                    Antecesores.add(A.obtenerExtremoInicial());
                }
            }
            return Antecesores;
        }
        throw new NoSuchElementException("No existe un vertice llamado "  + nombre + ".");

        
    }

//########################################################################################################################

    @Override
    public boolean cargarGrafo(String Archivo) throws IOException{
        try{ 
            
            BufferedReader lector = new BufferedReader(new FileReader(Archivo));
            String lineaActual = lector.readLine();
            String orientacion = lineaActual;
            System.out.println("Cargando grafo " + orientacion);
            lector.readLine();
            int n = Integer.parseInt(lineaActual);
            lector.readLine();
            int m = Integer.parseInt(lineaActual);
    
            GrafoDirigido nuevoGrafo = crearGrafoDirigido();
    
            for(int vertices = 0 ; vertices < n ; vertices++){
                String [] datos = lector.readLine().split(" ");
                int id = Integer.parseInt(datos[0]);

                if(datos.length==5) {
                    String nombre = datos[1];
                    double x = Double.parseDouble(datos[2]);
                    double y = Double.parseDouble(datos[3]);
                    double peso = Double.parseDouble(datos[4]);
        
                    nuevoGrafo.agregarVertice(id, nombre, x, y, peso);
                }else{
                    throw new IllegalArgumentException(" No es una linea de vertices valida");
                }
            }
    
            for(int arcos = 0 ; arcos < m ; arcos++){
                String [] datos = lector.readLine().split(" ");
                if(datos.length==4) {	
                    String vi = datos[0];
                    String vf = datos[1];
                    int tipo = Integer.parseInt(datos[2]);
                    double peso = Double.parseDouble(datos[3]);
        
                    nuevoGrafo.agregarArco(vi, vf, tipo, peso);
                }else{
                    throw new IllegalArgumentException("No es una linea de arcos valida"); 
                }
            }
    
            lector.close();
            return true;

        }catch(IOException e){
			return false;
		}
       
    }
        
    @Override
    public int numeroDeVertices(){
        return listaVertices.size();
    }

    @Override
    public int numeroDeLados(){
        return listaArcos.size();
    }

    @Override
    public boolean agregarVertice(Vertice v){
        if(!estaVertice(v.obtenerId())){
            LinkedList<Vertice> nuevoVert =new LinkedList<Vertice>();
            nuevoVert.add(v);
            listaVertices.add(nuevoVert);            
        }
        return false;
    }


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

    @Override
    public boolean estaVertice(int id){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).obtenerId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean estaVertice(String nombre){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).obtenerNombre().equals(nombre) ){
                return true;
            }
        }
        return false;
    }

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

    @Override
    public LinkedList<Integer> vertices(){
        LinkedList V = new LinkedList<Vertice>();
        for(LinkedList<Vertice> L : listaVertices){
            V.add(L.get(0).obtenerId());
        }
        return V;

    }

    @Override
    public LinkedList<Lado> lados(){
        LinkedList<Lado> lados = new LinkedList<Lado>();
        lados.addAll(listaArcos);
        return lados;
    }

    @Override
    public int grado(int id){

        int Grado = 0;
        int Tipo;

        if(estaVertice(id)){
            Vertice v = obtenerVertice(id);
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

    @Override
    public LinkedList<Vertice> adyacentes(int id){
        if(estaVertice(id)){
            LinkedList<Vertice> adyacentes = new LinkedList<Vertice>();
            adyacentes.addAll(sucesores(id));
            adyacentes.addAll(antecesores(id));
            return adyacentes;
        }
        throw new NoSuchElementException("No existe un vertice en el grafo con" + id + " de id.");
    }

    
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

    @Override
    public Grafo clone(){

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
    public String aString(){

    String info = "";

    for(LinkedList<Vertice> listaActual: listaVertices){
        info += listaActual.get(0).obtenerNombre() + " -----> ";

            for(Vertice v : listaActual){
                info += "       "+ v.obtenerNombre() + " ";
            }
            info += "\n";
    }

    info += "Vertices: " + "\n";


    for(LinkedList<Vertice> v : listaVertices){
        info += "Info de vertice " + v.get(0).obtenerNombre() + " : " + v.get(0).aString() + "\n";

    }

    info += "Arcos: " + "\n";
    int i = 0;
    for(Arco arco : listaArcos){
        info += "Info del arco " + i + " : " + arco.aString() + "\n";
        i += 1;
    }
    return info;
}

}
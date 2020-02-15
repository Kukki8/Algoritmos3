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

        if(!estaArco(a.getExtremoInicial().getNombre(),a.getExtremoFinal().getNombre(), a.getTipo())){
            listaArcos.add(a);
            return true;
           
        }else{
            return false;
        }
    }

    public  boolean agregarArco(String vi, String vf, int tipo, double peso){
        
        if(!estaArco(vi, vf, tipo)){
            if(estaVertice(vi) && estaVertice(vf)){
                Vertice nuevov1 = obtenerVertice(vi);
                Vertice nuevov2 = obtenerVertice(vf);
                Arco nuevoArco = new Arco(peso ,tipo, nuevov1, nuevov2);
                listaArcos.add(nuevoArco);
    
                for(LinkedList<Vertice> v : listaVertices){
                    if(v.get(0).getId() == nuevov1.getId()){
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
                if(b.getExtremoInicial().getNombre() == vi && b.getExtremoFinal().getNombre() == vf && b.getTipo() == tipo){
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
                if(b.getExtremoInicial().getNombre() == vi && b.getExtremoFinal().getNombre() == vf && b.getTipo() == tipo){
                    listaArcos.remove(b);
                    for(LinkedList<Vertice> listaActual : listaVertices){
                        if(listaActual.get(0).getNombre().equals(vi)){
                            for(Vertice v : listaActual){
                                if(listaActual.get(0).getNombre().equals(vi)){
                                    eliminarVertice(v.getId());
                                }
                            }return true;
                        }      
                    }
                }
            }
        }

        System.out.print("No existe un arco con vertice inicial " + vi + ", vertice final " + vf + " de tipo " + tipo);
        return false;
        

    }

    public Arco obtenerArco(String vi, String vf, int tipo){

        if(estaVertice(vi) == true && estaVertice(vf)){

            for(Arco b : listaArcos){
                if(b.getExtremoInicial().getNombre() == vi && b.getExtremoFinal().getNombre() == vf){
                    return b;
                }
            
            }
        }else{
            System.out.println("No existe un arco con vertice inicial " + vi + ", vertice final " + vf + " de tipo " + tipo);
        }
        return null;

    }

    public int gradoInterior(String vi, String vf, int tipo){
        int GradoIn=0;

        for(Arco A: listaArcos){
            if(A.getExtremoFinal().getNombre() == vf){
                if(A.getTipo() == tipo){
                    GradoIn+=1;
                }
            }
        }
        return GradoIn;
    }

    public int gradoExterior(String vi, String vf, int tipo){
        int GradoExt=0;

        for(Arco A: listaArcos){
            if(A.getExtremoInicial().getNombre() == vi){
                if(A.getTipo() == tipo){
                    GradoExt+=1;
                }
            }
        }
        return GradoExt;
    }

    public LinkedList<Vertice> sucesores(int id){

        LinkedList<Vertice> sucesores = new LinkedList<Vertice>();

        for(Arco A: listaArcos){
            if(A.getExtremoInicial().getId() == id){
                sucesores.add(A.getExtremoFinal());
            }
        }
        return sucesores;
    
    }

    public LinkedList<Vertice> sucesores(String name){

        LinkedList<Vertice> sucesores = new LinkedList<Vertice>();

        for(Arco A: listaArcos){
            if(A.getExtremoInicial().getNombre() == name){
                sucesores.add(A.getExtremoFinal());
            }
        }
        return sucesores;
    
    }

    public LinkedList<Vertice> antecesores(int id){

        if(estaVertice(id)){
            LinkedList<Vertice> antecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.getExtremoFinal().getId() == id){
                    antecesores.add(A.getExtremoInicial());
                }
            }
            return antecesores;
        }
        return null;

        
    }

    public LinkedList<Vertice> antecesores(String name){

        if(estaVertice(name)){
            LinkedList<Vertice> Antecesores = new LinkedList<Vertice>();

            for(Arco A: listaArcos){
                if(A.getExtremoFinal().getNombre() == name){
                    Antecesores.add(A.getExtremoInicial());
                }
            }
            return Antecesores;
        }
        return null;

        
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
                String nombre = datos[1];
                double x = Double.parseDouble(datos[2]);
                double y = Double.parseDouble(datos[3]);
                double peso = Double.parseDouble(datos[4]);
    
                nuevoGrafo.agregarVertice(id, nombre, x, y, peso);
            }
    
            for(int arcos = 0 ; arcos < m ; arcos++){
                String [] datos = lector.readLine().split(" ");
                String vi = datos[0];
                String vf = datos[1];
                int tipo = Integer.parseInt(datos[2]);
                double peso = Double.parseDouble(datos[3]);
    
                nuevoGrafo.agregarArco(vi, vf, tipo, peso);
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
        if(!estaVertice(v.getId())){
            LinkedList<Vertice> nuevoVert =new LinkedList<Vertice>();
            nuevoVert.add(v);
            listaVertices.add(nuevoVert);            
        }else{
                System.out.println("No existe un vertice en el grafo con" + v.getId() + " de id.");
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
        }else{
            return false;
        }
 
    }

    @Override
    public Vertice obtenerVertice(int id){

        if(estaVertice(id)){
            for(LinkedList<Vertice> v : listaVertices){
                if(v.get(0).getId() == id){
                    return v.get(0);
                }
            }
        }
        System.out.println("No existe un vertice en el grafo con" + id + " de id.");
        return null;                                        //Aqui va excepcion
    }

    public Vertice obtenerVertice(String name){

        if(estaVertice(name)){
            for(LinkedList<Vertice> v : listaVertices){
                if(v.get(0).getNombre().equals(name) ){
                    return v.get(0);
                }
            }
        }else{
            System.out.println("No existe un vertice en el grafo llamado " + name);
        }
        return null;

    }

    @Override
    public boolean estaVertice(int id){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).getId() == id){
                return true;
            }
        }
        System.out.println("No existe un vertice en el grafo con " + id + " de id.");
        return false;
    }

    public boolean estaVertice(String nombre){
        for(LinkedList<Vertice> v : listaVertices){
            if(v.get(0).getNombre().equals(nombre) ){
                return true;
            }
        }
        System.out.println("No existe un vertice en el grafo llamado " +nombre);
        return false;
    }

    @Override
    public boolean eliminarVertice(int id){

        if(estaVertice(id)){

            for(LinkedList<Vertice> listaActual : listaVertices){
                if(listaActual.get(0).getId() == id){
                    listaVertices.remove(listaActual);
                }else{
                    for(Vertice v : listaActual){
                        if(v.getId() == id){
                            listaActual.remove(v);
                        }
                    }
                }
            }
                
            for(Arco AR : listaArcos){
                if(id == AR.getExtremoInicial().getId() || id == AR.getExtremoFinal().getId()){
                    listaArcos.remove(AR);
                }
            }
            return true;
        }else{
            System.out.println("No existe un vertice en el grafo con" + id + " de id.");
            return false;
        }
    }

    @Override
    public LinkedList<Integer> vertices(){
        LinkedList V = new LinkedList<Vertice>();
        for(LinkedList<Vertice> L : listaVertices){
            V.add(L.get(0).getId());
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
                    if(a.getExtremoInicial().getId() == id){
                        Tipo = a.getTipo();
                        Grado += gradoExterior(a.getExtremoInicial().getNombre(), a.getExtremoFinal().getNombre(), Tipo);

                    }if(a.getExtremoFinal().getId()  == id){
                        Tipo = a.getTipo();
                        Grado += gradoInterior(a.getExtremoInicial().getNombre(), a.getExtremoFinal().getNombre(), Tipo);
                    }
            }
            return Grado;
        }
        return 0;
    }

    @Override
    public LinkedList<Vertice> adyacentes(int id){
        if(estaVertice(id)){
            LinkedList<Vertice> adyacentes = new LinkedList<Vertice>();
            adyacentes.addAll(sucesores(id));
            adyacentes.addAll(antecesores(id));
            return adyacentes;
        }
        return null;
    }

    
    @Override
    public LinkedList<Lado> incidentes(int id){

        if(estaVertice(id)){
            LinkedList<Lado> incidentes = new LinkedList<Lado>();

            for(Arco A: listaArcos){
                if(A.getExtremoFinal().getId() == id){
                    incidentes.add(A);
                }
            }
            return incidentes;
        }
        return null;

    }

    @Override
    public Grafo clone(){

        GrafoDirigido grafoClonado = new GrafoDirigido();
        for(LinkedList<Vertice> v : listaVertices){
            Vertice vRepresen = v.get(0);
            grafoClonado.agregarVertice(vRepresen.getId(), vRepresen.getNombre(), vRepresen.getX(), vRepresen.getY(),vRepresen.getPeso());
        }
        
        for(Arco a: listaArcos){
            grafoClonado.agregarArco(a.getExtremoInicial().getNombre(), a.getExtremoFinal().getNombre(), a.getTipo(), a.getPeso());
        }
        return grafoClonado;
    }

    @Override
    public String toString(){
        return null;
    }

}
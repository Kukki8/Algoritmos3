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
        
        if(estaVertice(vi)== true && estaVertice(vf)== true){
            Vertice VI = obtenerVertice(vi);
            Vertice VF = obtenerVertice(vf);
            int V1 = VI.getId();
            int V2 = VF.getId();

            for(Arco b : listaArcos){
                int bt = b.getTipo();
                String bn1 = b.getExtremoInicial().getNombre();
                String bn2 = b.getExtremoFinal().getNombre();
                int bp = b.getTipo();
            
                    if(bn1 == vi && bn1 == vf){
                        if(bt != tipo){
                            Arco A = new Arco(peso, tipo, VI, VF);
                            listaArcos.add(A);
                            return true;
                        }else{
                            System.out.println("Ya existe un arco entre los nodos " + vi + " y " + vf + " del mismo tipo " + tipo);
                            return false;
                        }
   
                    }else if(bn1 != vi && bn1 == vf && estaVertice(vi)== true) {
                        Arco A = new Arco(peso, tipo, VI, VF);
                        listaArcos.add(A);
                        return true;


                    }else if(bn1 == vi && bn1 != vf && estaVertice(vf)== true){
                        Arco A = new Arco(peso, tipo, VI, VF);
                        listaArcos.add(A);
                        return true;
                    }else if(bn1 != vi && bn1 != vf && estaVertice(vi)== true && estaVertice(vf)== true){
                        Arco A = new Arco(peso, tipo, VI, VF);
                        listaArcos.add(A);
                        return true;
                    }
            }

        }else if(estaVertice(vi)== false && estaVertice(vf)== true){
            System.out.println("No se puede agregar el arco porque el vertice " + vi + " no existe.");
            return false;

        }else if(estaVertice(vi)== true && estaVertice(vf)== false){
            System.out.println("No se puede agregar el arco porque el vertice " + vf + " no existe.");
            return false;

        }else if(estaVertice(vi)== false && estaVertice(vf)== false){
            System.out.println("No se puede agregar el arco porque ninguno de los vertices existe." );
            return false;  
        }

        return false;
        
    }


    public boolean estaArco(String vi, String vf, int tipo){

        if(estaVertice(vi) == true && estaVertice(vf)){
            Vertice VI = obtenerVertice(vi);
            Vertice VF = obtenerVertice(vf);

            for(Arco b : listaArcos){
                if(b.getExtremoInicial() == VI && b.getExtremoFinal() == VF){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;

    }

    public boolean eliminarArco(String vi, String vf, int tipo){

        if(estaArco(vi, vf, tipo)){
            Vertice VI = obtenerVertice(vi);
            Vertice VF = obtenerVertice(vf);

            for(Arco b : listaArcos){
                if(b.getExtremoInicial() == VI && b.getExtremoFinal() == VF){
                    listaArcos.remove(b);
                }
            }
            return true;
        }else{
            System.out.print("No existe un arco con vertice inicial " + vi + ", vertice final " + vf + " de tipo " + tipo);
            return false;
        }

    }

    public Arco obtenerArco(String vi, String vf, int tipo){

        if(estaVertice(vi) == true && estaVertice(vf)){
            Vertice VI = obtenerVertice(vi);
            Vertice VF = obtenerVertice(vf);

            for(Arco b : listaArcos){
                if(b.getExtremoInicial() == VI && b.getExtremoFinal() == VF){
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
        Vertice VI = obtenerVertice(id);

        for(Arco A: listaArcos){
            if(A.getExtremoInicial() == VI){
                sucesores.add(A.getExtremoFinal());
            }
        }
        return sucesores;
    
    }

    public LinkedList<Vertice> sucesores(String name){

        LinkedList<Vertice> sucesores = new LinkedList<Vertice>();
        Vertice VI = obtenerVertice(name);

        for(Arco A: listaArcos){
            if(A.getExtremoInicial() == VI){
                sucesores.add(A.getExtremoFinal());
            }
        }
        return sucesores;
    
    }

    public LinkedList<Vertice> antecesores(int id){

        if(estaVertice(id)){
            LinkedList<Vertice> antecesores = new LinkedList<Vertice>();
            Vertice VF = obtenerVertice(id);
    
            for(Arco A: listaArcos){
                if(A.getExtremoFinal() == VF){
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
            Vertice VF = obtenerVertice(name);
    
            for(Arco A: listaArcos){
                if(A.getExtremoFinal() == VF){
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

            for(LinkedList<Vertice> v : listaVertices){
                if(v.get(0).getId() == id){
                    listaVertices.remove(v);
                }
            }
                
            for(LinkedList<Vertice> listaVerActual : listaVertices){
                for(Vertice verActual : listaVerActual){
                    if(verActual.getId() == id){
                        listaVerActual.remove(verActual);
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
            Vertice V = obtenerVertice(id);
    
            for(Arco A: listaArcos){
                if(A.getExtremoFinal() == V){
                    incidentes.add(A);
                }
            }
            return incidentes;
        }
        return null;

    }

    @Override
    public Clonacion clone(){

    }

    @Override
    public String toString(){

    }

}
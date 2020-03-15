import java.util.LinkedList;

public class Grafo{

    public LinkedList<Lados> grafo;
    public LinkedList<Vertices> vertices;

    Grafo(){
        this.grafo = new LinkedList<Lados>();
        this.vertices = new LinkedList<Vertices>();

    }

    public boolean agregarLado(Lados l){
        if(estaLado(l) == false){
            grafo.add(l);
            return true;
        }
        return false;

    }

    public boolean agregarVertice(Vertices v){

        String nombrev = v.obtenerNombre();

        if(estaVertice(nombrev) == false){
            vertices.add(v);
            return true;
        }
        return false;
    }

    public boolean estaVertice(String n){
        for(Vertices v : vertices){
            if(v.obtenerNombre().equals(n)){
                return true;
            }
        }
        return false;
    }

    public Vertices buscarVertice(String n){

        for(Vertices v : vertices){
            if(v.obtenerNombre().equals(n)){
                return v;
            }
        }
        return null;
    }

    public boolean estaLado(Lados a){

        String vini = a.verIni.obtenerNombre();
        String vfin = a.verFin.obtenerNombre(); 

        for(Lados l : grafo){

            String ini = l.verIni.obtenerNombre();
            String fin = l.verFin.obtenerNombre();

            if(ini.equals(vini) && fin.equals(vfin)){
                return true;
            }
        }
        return false;
    }
}
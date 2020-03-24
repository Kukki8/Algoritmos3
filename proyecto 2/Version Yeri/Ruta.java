import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Ruta {

    public int transbordo;
    public LinkedList<Lado> recorrido;
    public Hashtable<String, Integer> tablita;
    
    Ruta(Hashtable<String, Integer> tablita){
        this.transbordo = 0;
        this.recorrido = new LinkedList<Lado>();
        this.tablita = tablita ; 
    }

    public void cargarRutaD(Stack<Arco> caminoActual){
        
        recorrido.clear();
        for(Arco a : caminoActual){
            recorrido.add(a);
        }

    }

    public void cargarRutaND(Stack<Arista> caminoActual){
        
        recorrido.clear();
        for(Arista a : caminoActual){
            recorrido.add(a);
        }

    }


    public void cambiarTransbordo(int transbordo){

        this.transbordo = transbordo ;
    }

    public void imprimirRutaD(){                                       //Funcion para caso de ruta en un GrafoDirigido
        if(!recorrido.isEmpty()){
            for(Lado ar : recorrido){
                Arco a = (Arco) ar;                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
                int vInID = a.obtenerExtremoInicial().obtenerId();
                int vFinID = a.obtenerExtremoFinal().obtenerId();
                String vInNom = a.obtenerExtremoInicial().obtenerNombre();
                String vFinNom = a.obtenerExtremoFinal().obtenerNombre();
                int aColor = a.obtenerTipo();
                String aLlave = "" ;

                for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                    if(aColor == entrada.getValue()){
                        aLlave = entrada.getKey();
                        break;
                    }
                }

                System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
            }
            System.out.println("Transbordos totales: " + transbordo);
            
        }else{
            System.out.println("No hay recorrido que realizar.");     //Llegara a este caso en caso de que no consiga una ruta del punto inicial al
        }                                                             // punto final
    }

    public void imprimirRutaND(){                               //Funcion para caso de ruta en un GrafoNoDirigido
        if(!recorrido.isEmpty()){
            for(Lado ar : recorrido){
                Arista a = (Arista) ar;                         //Realizamos un casting para trabajar con atributos de GrafoNoDirigido
                int vInID = a.obtenerExtremo1().obtenerId();
                int vFinID = a.obtenerExtremo2().obtenerId();
                String vInNom = a.obtenerExtremo1().obtenerNombre();
                String vFinNom = a.obtenerExtremo2().obtenerNombre();
                int aColor = a.obtenerTipo();
                String aLlave = "" ;

                for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                    if(aColor == entrada.getValue()){
                        aLlave = entrada.getKey();
                        break;
                    }
                }
                System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
            }
            System.out.println("Transbordos totales: " + transbordo);
            
        }else{
            System.out.println("No hay recorrido que realizar.");     //Llegara a este caso en caso de que no consiga una ruta del punto inicial al
        }                                                             // punto final
    }


}
import java.util.LinkedList;
import java.util.*;

public class BacktrackingD{

    public GrafoDirigido grafo;
    public LinkedList<Integer> tipos;
    public int transbordoActual;
    public Stack<Arco> rutaActual ;
    public Hashtable<Integer , Integer> visitados;
    public Ruta ruta;
    
    BacktrackingD(GrafoDirigido grafo){
        this.grafo = grafo;
        this.tipos = new LinkedList<Integer>();
        this.transbordoActual = grafo.numeroDeVertices();
        this.rutaActual =  new Stack<Arco>();
        this.visitados = new Hashtable<Integer , Integer>() ;
        this.ruta = new Ruta();

        for(int i = 0; i < grafo.lineas.size() ; i++){
            tipos.add(i);
        }

        for(Integer id : grafo.vertices()){
            visitados.put(id , 0);
        }
    }

    public Ruta DFS(String nombrePin, String nombrePfin){
        
        Vertice vertPin = grafo.obtenerVertice(nombrePin);
        Vertice vertPfin = grafo.obtenerVertice(nombrePfin);
        int pin = vertPin.obtenerId();
        int pfin = vertPfin.obtenerId();

        if(pin == pfin){
            return null;
        }
        for(Vertice l : grafo.sucesores(pin)){                                               //Verifica los sucesores del vertice inicial

            for(Integer j : tipos){                                                          //Recorre los tipos/colores de las lineas
                if(grafo.estaArco(pin, l.obtenerId(), j.intValue())){                       //Verifica si existe algun arco que contenga al vertice inicial y al final

                    String pfinNombre = grafo.obtenerVertice(l.obtenerId()).obtenerNombre() ;   //del tipo/color que la linea actual. 
                    rutaActual.push(grafo.obtenerArco(nombrePin, pfinNombre, j.intValue()));
                    hacerDFS(l.obtenerId(), pfin, 0, j.intValue());
                }
            }
        }
        return ruta;
    }

    public void hacerDFS(int pin, int pfin, int transbordos, int tipo){

        if(pin == pfin){
            if(transbordos < transbordoActual){               //Compara cantidad de transbordos para conservar el camino con menor cantidad de los mismos
                transbordoActual = transbordos;
                ruta.cargarRutaD(rutaActual);
                ruta.cambiarTransbordo(transbordos);

                rutaActual.pop();                              //Vuelve al vertice predecesor de nuestro punto final,
                visitados.replace(pin, 0);                     //listo para evaluar otro sucesor/otra linea.

                return;                                     
            }                                               
        }

        for(Vertice l : grafo.sucesores(pin)){
            int lid = l.obtenerId();
            if(visitados.get(lid) == 0){
                if(grafo.estaArco(pin, lid,tipo)){                                   //Damos prioridad a los arcos del mismo tipo/color
                    String pinNombre =  grafo.obtenerVertice(pin).obtenerNombre();
                    String pfinNombre = grafo.obtenerVertice(lid).obtenerNombre() ;
                    rutaActual.push(grafo.obtenerArco(pinNombre, pfinNombre, tipo));                //Agrego el arco a la ruta
                    visitados.replace(lid, 1);
                    hacerDFS(lid , pfin , transbordos , tipo);

                }else{                                                                //En caso de que no exista un arco del mismo tipo, verificamos
                    for(Integer j : tipos){                                           // las opciones restantes
                        if(j != tipo){                                    //Verificacion de lineas
                            if(grafo.estaArco(pin, lid, j.intValue())){
                                String pinNombre =  grafo.obtenerVertice(pin).obtenerNombre();
                                String pfinNombre = grafo.obtenerVertice(lid).obtenerNombre();
                                rutaActual.push(grafo.obtenerArco(pinNombre, pfinNombre, j.intValue()));             //Agrego el arco a la ruta
                                visitados.replace(lid, 1);
                                hacerDFS(lid , pfin , transbordos + 1 , j.intValue());
                            }
                        }                                            
                    }
                }
            }
        }
        rutaActual.pop();                   //Al acabarse los sucesors del vertice actual, se regresa al estado anterior, para evaluar otro sucesor del      
        visitados.replace(pin, 0);          //predecesor del vertice actual. Ademas, marcamos el vertice actual como no visitado.
    }
}
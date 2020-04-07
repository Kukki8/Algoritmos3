import java.util.LinkedList;
import java.util.*;

public class BacktrackingND{
	/*Atributos de la Clase BacktrackingD:
	 * GrafoDirigido: GrafoNoDirigido(representacion del mapa)
	 * tipos: lista enlazada que posee los tipos de las lineas
	 * transbordoActual: Cantidad de vertices (estaciones) de la ruta actual
	 * visitados: Tabla de hash de enteros para guardar los vertices visitados (estaciones)
	 * ruta: objeto ruta(para guardar la posible ruta)
	 * 
	 */
    public GrafoNoDirigido grafo;
    public LinkedList<Integer> tipos;
    public int transbordoActual;
    public Stack<Arista> rutaActual ;
    public Hashtable<Integer , Integer> visitados;
    public Ruta ruta;
    //Constructor de la clase BacktrackingD
    BacktrackingND(GrafoNoDirigido grafo){
        this.grafo = grafo;
        this.tipos = new LinkedList<Integer>();
        this.transbordoActual = grafo.numeroDeVertices();
        this.rutaActual =  new Stack<Arista>();
        this.visitados = new Hashtable<Integer , Integer>() ;
        this.ruta = new Ruta(grafo.lineas);

        for(int i = 0; i < grafo.lineas.size() ; i++){ // guarda todas las lineas disponibles del grafo(mapa)
            tipos.add(i);
        }

        for(Integer id : grafo.vertices()){ //inicializa la tabla de hash con 0 y sus respectivos id de cada parada(vertice del grafo) del mapa
            visitados.put(id , 0);
        }
    }
	// metodo getter: que genera la ruta usando el algoritmo de DFS.
    public Ruta DFS(String nombrePin, String nombrePfin){
        
        Vertice vertPin = grafo.obtenerVertice(nombrePin);
        Vertice vertPfin = grafo.obtenerVertice(nombrePfin);
        int pin = vertPin.obtenerId();
        int pfin = vertPfin.obtenerId();

        if(pin == pfin){
            return null;
        }
        for(Vertice l : grafo.adyacentes(pin)){                                               //Verifica los sucesores del vertice inicial

            for(Integer j : tipos){                                                          //Recorre los tipos/colores de las lineas
                if(grafo.estaArista(pin, l.obtenerId(), j.intValue())){                       //Verifica si existe algun arco que contenga al vertice inicial y al final

                    String pfinNombre = grafo.obtenerVertice(l.obtenerId()).obtenerNombre() ;   //del tipo/color que la linea actual. 
                    rutaActual.push(grafo.obtenerArista(nombrePin, pfinNombre, j.intValue()));
                    hacerDFS(l.obtenerId(), pfin, 0, j.intValue());
                }
            }
        }
        return ruta;
    }
	// metodo que utiliza el metodo anterior y crea la mejor ruta con menos transbordos posibles(Backtracking)/Aplicamos DFS Recursivo
    public void hacerDFS(int pin, int pfin, int transbordos, int tipo){
        if(pin == pfin){
            if(transbordos < transbordoActual){               //Compara cantidad de transbordos para conservar el camino con menor cantidad de los mismos
                transbordoActual = transbordos;
                ruta.cargarRutaND(rutaActual);
                ruta.cambiarTransbordo(transbordos);

                rutaActual.pop();                              //Vuelve al vertice predecesor de nuestro punto final,
                visitados.replace(pin, 0);                     //listo para evaluar otro sucesor/otra linea.

                return;                                     
            }                                               
        }

        for(Vertice l : grafo.adyacentes(pin)){
            int lid = l.obtenerId();
            if(visitados.get(lid) == 0){
                if(grafo.estaArista(pin, lid,tipo)){                                   //Damos prioridad a los arcos del mismo tipo/color
                    String pinNombre =  grafo.obtenerVertice(pin).obtenerNombre();
                    String pfinNombre = grafo.obtenerVertice(lid).obtenerNombre() ;
                    rutaActual.push(grafo.obtenerArista(pinNombre, pfinNombre, tipo));                //Agrego el arco a la ruta
                    visitados.replace(lid, 1);
                    hacerDFS(lid , pfin , transbordos , tipo);

                }else{                                                                //En caso de que no exista un arco del mismo tipo, verificamos
                    for(Integer j : tipos){                                           // las opciones restantes
                        if(j != tipo){                                    //Verificacion de lineas
                            if(grafo.estaArista(pin, lid, j.intValue())){
                                String pinNombre =  grafo.obtenerVertice(pin).obtenerNombre();
                                String pfinNombre = grafo.obtenerVertice(lid).obtenerNombre();
                                rutaActual.push(grafo.obtenerArista(pinNombre, pfinNombre, j.intValue()));             //Agrego el arco a la ruta
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
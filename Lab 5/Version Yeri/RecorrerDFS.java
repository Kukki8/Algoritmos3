import java.util.*;
public class RecorrerDFS{
    
    public GrafoD mapa;
    public int[] camino;
    public boolean[] visitados;
    public Stack<Integer> abiertos;
    public int index;
    public int Mega;


    RecorrerDFS(GrafoD g){                              //Constructor RecorrerDFS
        mapa = g;
        camino = new int[g.vertice];
        visitados = new boolean[g.vertice];  
        abiertos = new Stack<Integer>(); 
        Mega = 0;
        for(int i = 0; i<g.vertice;i++){
            camino[i] = -1;
        }
        
    }

    public boolean eliminar(int vertice){                   //Revisa los criterios de eliminacion
        if(visitados[vertice] == true){
            return chequearCiclo(vertice);                  //Llamada a chequear ciclo, por vertice repetido en arreglo visitados
        }else if(abiertos.contains(vertice)== true){
            return true;
        }else{
            return false;
        }

    }

    public boolean chequearCiclo(int vertice){

        boolean result = true;

        for(int i =0; i< mapa.vertice; i ++){           //Buscara la primera vez en q aparece el vertice repetido
            if(camino[i] == vertice){
                resizeGrafo(i, vertice);                //Llamada a modificar el grafo
                result = false;
                break;                                  //No necesitamos seguir recorriendo luego de conseguir la primera instancia
            }else{
                result = true;
            }
        }
        return result;
    }

    public void resizeGrafo(int i, int vertice){

        int k = i + 1;

        while(k< mapa.vertice && camino[k]!= -1){                       //Se revisaran los nodos intermedio entre la primera y segunda instancia del nodo representante
            for(int j = 0; j<mapa.vertice;j++){
                if(mapa.matrizAdyacencia[camino[k]][j]==1 && j!= vertice){          //Verificaremos las relaciones de nodos internos con otros (salida). No contamos bucles
                    mapa.add(vertice,j);                                            //Se agrega la relacion del nodo intermedio al nodo representante, cnservando el camino
                    mapa.delete(camino[k],j);                                       //Se aislan los nodos intermedios
                
                }
                else if(mapa.matrizAdyacencia[j][camino[k]]==1 && j!= vertice){    //Verificaremos las relaciones de otros nodos con nodos intermedios (entrada). No contamos bucles
                    mapa.add(j,vertice);                                            // Agregamos tal relacion de entrada al nodo representante
                    mapa.delete(j,camino[k]);                                       //Aislamos nodo intermedio
                }
            }
            mapa.delete(vertice,camino[k]);                                         //Terminamos de aislar nodos intermedios con nodo representante
            camino[k] = -1;
            k= k+1;
            index = i;                                                              //Nos permite seguir el camino desde la primera aparicion del representante, es decir, retrocedemos la dif entre intermedios y repetidos
        }
        Mega += 1;                                                                  //Sumamos un megaedificio, ya que conseguimos ciclo y terminamos todo el proceso

    }


    public void chequearVia(){                                                  //Se encargara de verificar que se ha pasado por todos los vertices del grafo, sin dejar partes sin procesar.
                                                                                //Util en casos extremos en los que no es posible llegar a ciertos vertices por caminos unidireccionales
        for(int i = 0; i < mapa.vertice ; i++){
            if(visitados[i] == false){
                hacerDFS(i);                                                    //Una vez verificado el vertice a trabajar, llamada al proceso de recorrido

            }
        }
        System.out.println("Existen " + Mega + " mega edificios");               //Imprime todos los ciclos(megaedificios)
    }


    public void hacerDFS(int vertIni){                                      //Comienzo recorrido con DFS desde nodo no visitado
        abiertos.push(vertIni);                                             //Vertice a visitar pasa a abiertos
        visitados[vertIni] = true;                                         //Vertice a visitar entra en visitado
        index = 0;
        System.out.println("Recorrido desde: " + vertIni);                  //Indicamos desde donde empezaremos el recorrido actual

            while(!abiertos.empty()){                                       //Seguiremos nuestro camino hasta que ya no existan nodos abiertos o por recorrer
                int p= abiertos.pop();                                      //Saca el nodo visitado de abiertos
                visitados[p] = true;                                        //Introduce nodos visitados en arreglo visitados
                for(int j=0;j< mapa.vertice;j++){
                    if(mapa.matrizAdyacencia[p][j]==1){                     //Verifica si el nodo en cuestion tiene relacion con algun otro
                        camino[index] = p;                                     //De ser cierto, lo agrega al camino
                        if(!eliminar(j)){                                       //Verificamos nodo de llegada. Si eliminar es falso, quiere decir que no esta repetido, no hay ciclo ni esta abierto
                            abiertos.push(j);                                   //Agrega el futuro nodo a abiertos
                            index = index+1;
                            j = mapa.vertice;                                   //No seguimos recorriendo, para continuar desde el ultimo nodo valido a seguir

                        }   

                    }

                }
            }
    }

}
     


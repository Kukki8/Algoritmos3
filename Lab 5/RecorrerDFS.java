   
import java.util.*;

public class RecorrerDFS{

    public Grafo mapa;
    public int[] camino;
    public boolean[] visitados;
    public Stack<Integer> abiertos;


    DFS(Grafo g){
        mapa = g;
        camino = new int[g.vertice];
        visitados = new boolean[g.vertice];  
        abiertos = new Stack<Integer>(); 
        
    }

    public boolean eliminar(int vertice){
        if(visitados[vertice] == true){
            System.out.print("CONSEGUIMOS UN CICLO!")
            //Llamada a build
            //return true;
            return false;
        }else if(abiertos.contains(vertice)== true ){
            return true;
        }else{
            return false;
        }

    }

    public void hacerDFS(int vertIni){
        abiertos.push(vertIni);
        visitados[vertIni] = true;
        camino[0] = vertIni;
        int i = 1;

            while(!abiertos.empty()){
                int p= abiertos.pop();
                visitados[p] = true;
                System.out.print("Actual" + p);

                for(int j=0;j< mapa.vertice;j++){
                    if(mapa.matrizAdyacencia[vertIni][j]==1){

                        if(!eliminar(j)){
                            abiertos.push(j);
                            visitados[i]=true;
                            camino[i] = j;
                            i = i+1;
                            //System.out.print("Eliminamos falso");

                        }else{
                           // System.out.print(vertIni "-" + j + "ya esta visitado");
                        }

                    }

                }

            }



            
        }
       
    } 

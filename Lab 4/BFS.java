import java.util.*;
import java.util.LinkedList; 
import java.util.Queue; 

public class BFS{
    public Grafo mapa;
    public boolean [] visitados;
    public int [] camino;
    public Queue<Integer> abiertos;

    BFS(Grafo g){
        mapa = g;
        visitados = new boolean [g.vertice];
        camino = new int [g.vertice];
        abiertos = new LinkedList<>();
    }

    public boolean eliminar(int vertice){
        if(abiertos.contains(vertice) || visitados[vertice]== true){
            return true;
        }else{
            return false;
        }
    }

    public void hacerBFS(int vertIni){
        abiertos.add(vertIni);
        visitados[vertIni] = true;
        int i= 0;
        System.out.println("Recorrido desde: " + vertIni);


        while(abiertos.size() != 0){
            int p = abiertos.remove();
            visitados[p] = true;
            for(int j=0; j< mapa.vertice; j++){
                if(mapa.matrizAdyacencia[vertIni][j] == 1){
                    camino[i]=p;
                    if(!eliminar(j)){
                        abiertos.add(j);
                        i=i+1;
                        System.out.println("["+ p + "-" + j+ "]");
                        j = mapa.vertice;
                    }else{
                        System.out.println(j + " ya esta visitado");
                    }
                }
            }
        }

        int b = 1;
        for(int j=0 ; j< mapa.vertice - 1; j++){
            if(camino[j] != -1){
                b = b+1;
            }
            
        }

            if(b == mapa.vertice ){
                System.out.print("[");
                for(int k=0 ; k< mapa.vertice - 1; k++){
                    System.out.print(camino[k] + "-");
                }
                System.out.print(camino[mapa.vertice-1] + "]" + " ");
                System.out.println("Es un camino Hamiltoniano");
                System.out.println("El camino tiene: " + mapa.vertice + " vertices");

            }else{
                System.out.print("No se encontro un camino Hamiltoniano");  
            }   


    }

}
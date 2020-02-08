import java.util.*;

public class BFS{
    public Grafo mapa;
    public boolean [] visitados;
    public int [] camino;
    public Queue<Integer> abiertos;

    BFS(Grafo g){
        mapa = g;
        visitados = new boolean [g.vertice];
        camino = new int [g.vertice];
        abiertos = new Queue<Integer>();
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
        camino[0] = vertIni;
        int i= 1;

        while(!abiertos.empty()){
            int p = abiertos.remove();
            visitados[p] = true;
            for(int j=0; j< mapa.vertice; j++){
                if(mapa.matrizAdyacencia[vertIni][j] == 1){
                    if(!eliminar(j)){
                        visitados[j]= true;
                        abiertos.add(j);
                        camino[j]=i;
                        i=i+1;
                    }else{
                        System.out.print("");
                    }
                }
            }
        }

        if(camino.length==mapa.vertice){
            for(int k=0; k< mapa.vertice; k++){
                System.out.print(camino[k]+ "-");
            }
            System.out.print("Es un camino Hamiltoniano");
        }else{
            System.out.print("No existe un camino Hamiltoniano...");
        }
    }

}

//Probando ando
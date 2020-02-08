import java.util.*;
public class DFS{
    
    public Grafo mapa;
    public int[] camino;
    public boolean[] visitados;
    public Stack<Integer> abiertos;


    DFS(Grafo g){
        mapa = g;
        camino = new int[g.vertice];
        visitados = new boolean[g.vertice];  
        abiertos = new Stack<Integer>(); 
        for(int i = 0; i<g.vertice;i++){
            camino[i] = -1;
        }
        
    }

    public boolean eliminar(int vertice){
        if(visitados[vertice] == true){
            return true;
        }else if(abiertos.contains(vertice)== true){
            return true;
        }else{
            return false;
        }

    }

    public void hacerDFS(int vertIni){
        abiertos.push(vertIni);
        visitados[vertIni] = true;
        int i = 0;
        System.out.println("Recorrido desde: " + vertIni);

            while(!abiertos.empty()){
                int p= abiertos.pop();
                visitados[p] = true;
                for(int j=0;j< mapa.vertice;j++){
                    if(mapa.matrizAdyacencia[p][j]==1){
                        camino[i] = p;
                        if(!eliminar(j)){
                            abiertos.push(j);
                            i = i+1;
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
                System.out.println("No se encontro un camino Hamiltoniano");  
            }
    }

       
}

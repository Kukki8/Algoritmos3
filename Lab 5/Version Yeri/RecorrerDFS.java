import java.util.*;
public class RecorrerDFS{
    
    public GrafoD mapa;
    public int[] camino;
    public boolean[] visitados;
    public Stack<Integer> abiertos;
    public int index;


    RecorrerDFS(GrafoD g){
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
            return chequearCiclo(vertice);          
        }else if(abiertos.contains(vertice)== true){
            return true;
        }else{
            return false;
        }

    }

    public boolean chequearCiclo(int vertice){

        boolean result = true;

        for(int i =0; i< mapa.vertice; i ++){
            if(camino[i] == vertice){
                resizeGrafo(i, vertice);
                result = false;
                break;
            }else{
                result = true;
            }
        }
        return result;
    }

    public void resizeGrafo(int i, int vertice){

        int k = i + 1;

        while(k< mapa.vertice && camino[k]!= -1){
            for(int j = 0; j<mapa.vertice;j++){
                if(mapa.matrizAdyacencia[camino[k]][j]==1){
                    mapa.add(vertice,j);
                    mapa.delete(camino[k],j);
                
                }
                else if(mapa.matrizAdyacencia[j][camino[k]]==1){
                    mapa.add(j,vertice);
                    mapa.delete(j,camino[k]); 
                }
            }
            camino[k] = -1;
            k= k+1;
            index = i;
        }

    }

    public void hacerDFS(int vertIni){
        abiertos.push(vertIni);
        visitados[vertIni] = true;
        index = 0;
        System.out.println("Recorrido desde: " + vertIni);

            while(!abiertos.empty()){
                int p= abiertos.pop();
                visitados[p] = true;
                for(int j=0;j< mapa.vertice;j++){
                    if(mapa.matrizAdyacencia[p][j]==1){
                        camino[index] = p;
                        if(!eliminar(j)){
                            abiertos.push(j);
                            index = index+1;
                            System.out.println("["+ p + "-" + j+ "]");
                            j = mapa.vertice;
                        }else{
                        }

                    }

                }

            }


            int b = 0;
            for(int j=0 ; j< mapa.vertice; j++){
                if(camino[j] != -1){
                    b = b+1;
                }
                
            }

            System.out.println("Existen " + b + " mega edificios");

    }

       
}

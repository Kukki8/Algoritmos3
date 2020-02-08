import java.util.*;
public class RecorrerDFS{
    
    public GrafoD mapa;
    public int[] camino;
    public boolean[] visitados;
    public Stack<Integer> abiertos;


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

        for(int i =0; i< mapa.vertice;i++){
            if(camino[i] == vertice){
                resizeGrafo(i, vertice);
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    public void resizeGrafo(int i, int vertice){
        int k = i;
        i= i+1;
        while(camino[i]!= vertice){
            for(int j = 0; j<mapa.vertice;j++){
                if(mapa.matrizAdyacencia[i][j]==1){
                    mapa.add(k,j);
                    mapa.delete(i,j);
                }
            }
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

            System.out.println("Existen " + b + " mega edificios");

            // if(b == mapa.vertice ){
            //     System.out.print("[");
            //     for(int k=0 ; k< mapa.vertice - 1; k++){
            //         System.out.print(camino[k] + "-");
            //     }
            //     System.out.print(camino[mapa.vertice-1] + "]" + " ");
            //     System.out.println("Es un camino Hamiltoniano");
            //     System.out.println("El camino tiene: " + mapa.vertice + " vertices");

            // }else{
            //     System.out.println("No se encontro un camino Hamiltoniano");  
            // }
    }

       
}

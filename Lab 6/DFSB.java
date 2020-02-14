

import java.util.*;

class DFSB{

    int[] P;                                                                     //Areglo de Predecesores
    String[] C;                                                                  //Arreglo de Colores
    int[] D;                                                                     //Arreglo de Ordinales

    public int[] Inicio(int Vertice, String arg, int Numero, Grafo grafo){
        if(arg.equals("pred")){

            int Time = 0;
            P = new int[grafo.Dametamaño()];                                 
			C = new String[grafo.Dametamaño()];                                                          
            D = new int[grafo.Dametamaño()];                                 
            
            for(int i=0; i<grafo.Dametamaño(); i++){
				P[i] = -1;
				D[i] = -1;
				C[i] = "White";
            }
            
            P[Vertice]=Vertice;                                                            //El predecesor de el vertice inicial es el mismo
            DFS(Numero, 0, Vertice, Time, grafo);
            return P;                                                            //Retorna el arreglo de Predecesores
        }

        else{

            int Time = 0;
            P = new int[grafo.Dametamaño()];                                  //
			C = new String[grafo.Dametamaño()];                               // Inicializa cada arreglo                             
            D = new int[grafo.Dametamaño()];                                  //
            
            for(int i=0; i<grafo.Dametamaño(); i++){
				P[i] = -1;
				D[i] = -1;
				C[i] = "White";
            }
            
            P[Vertice]=Vertice;                                                            //El predecesor de el vertice inicial es el mismo
            DFS(Numero, 0, Vertice, Time, grafo);
            return D;                                                            //Retorna el arreglo de Ordinales

        }
       
    }


    /*Este codigo es Utilizado cuando el Usuario ingrese como --arb y quiera imprimir el arbol
    * int V0: El vertice inicial del recorrido
    * String arg: Indica que funcion debe ejecutarse
    * int Num: Cota de profundidad de recorrido
    * Grafo grafo: contiene la informacion del grafo
    */
    public void InicioPrint(int V0, String arg, int Num, Grafo grafo){
                             
		C = new String[grafo.Dametamaño()];                                   // Inicializa el Arreglo de Colores

        for(int i=0; i<grafo.Dametamaño(); i++){
            C[i] = "White";
        }

        String Espacio = "";                                                     //String que contiene la identacion necesaria para cada nivel
        System.out.println(String.valueOf(0)+" - "+String.valueOf(0)+" (Raiz)");
        DFSPrint(Espacio, Num, 0, V0, grafo);
    }

    /*Este metodo es DFS para cuando no necesitemos imprimir el recorrido
    */
    private int DFS(int Num, int ContadorNiv, int v, int Time, Grafo grafo){

        if(ContadorNiv<Num){                                                     //Si el recorrido llega a la profundidad cota, no sigue el recorrido
            D[v] = Time;                                                         //Este sera el arreglo donde tendremos el tiempo en el cual fue descubierto un vertice
            Time = Time + 1;
		    C[v] = "Gray";
		    for(int i=0; i<grafo.Dametamañoadyacentes(v); i++){
			    if (C[grafo.DamePos(v, i)].equals("White")){
                    P[grafo.DamePos(v, i)] = v;
                    ContadorNiv++;
                    Time = DFS(Num, ContadorNiv, grafo.DamePos(v, i), Time, grafo);
                    ContadorNiv--;
			    }  
		    }
        }
        C[v] = "Black";
        return Time;
    }

    /*Este metodo es DFS para cuando necesitemos imprimir el recorrido
    */
    private void DFSPrint(String Espacio, int Numero, int ContadorNiv, int vertice, Grafo grafo){

        if(ContadorNiv<Numero){                                                     //Si el recorrido llega a la profundidad cota, no sigue el recorrido
            Espacio = Espacio+" ";
		    C[vertice] = "Gray";
		    for(int i=0; i<grafo.Dametamañoadyacentes(vertice); i++){
			    if (C[grafo.DamePos(vertice, i)].equals("White")){
                    System.out.println(Espacio+String.valueOf(vertice)+" - "+String.valueOf(grafo.DamePos(vertice,i)));
                    ContadorNiv++;
                    DFSPrint(Espacio, Numero, ContadorNiv, grafo.DamePos(vertice, i), grafo);
                    ContadorNiv--;
			    }  
		    }
        }
        C[vertice] = "Black";
    }


}
/*Estudiantes:
 * Yerimar Manzo: 14-10611
 *Jonathan Bautista: 16-10109
 */

import java.io.*;

public class Build{

    public static int Ini;

    public static GrafoD leerMapa(String Archivo) throws IOException {

        BufferedReader Lector = new BufferedReader(new FileReader(Archivo));   //Carga lector
        String linea = Lector.readLine();                                      //Lee primera linea
        int n = Integer.parseInt(linea);                                       //Primera linea representa los vertices. Los transformamos a enteros
        linea = Lector.readLine();                                             //Lee segunda linea
        int m = Integer.parseInt(linea);                                       //Segunda linea representa los arcos. Los transformamos a enteros

        GrafoD ciudad = new GrafoD(n);

        int edif1;
        int edif2;

        for(int i=0; i<m;i++){                                         //Recorremos el resto del texto, linea por linea, dependiendo de cuantos arcos nos dieron
                linea = Lector.readLine();                              // anteriormente
                String[] sinespacio = linea.split(" ");             //Dividimos siquientes lineas justo en el espacio en blanco, haciendo un arreglo con las posiciones
                edif1 = Integer.parseInt(sinespacio[0]);            // Primera posicion del arreglo indica nodo de salida
                edif2 = Integer.parseInt(sinespacio[1]);            //Segunda posicion del arreglo indica nodo de llegada

                ciudad.add(edif1,edif2);                            //Agregamos arcos en el grafo


        }

        Lector.close();
        return ciudad;                                              //Retornamos grafo con todos su arcos
        
    }

        public static void main( String [] args) throws IOException{
            GrafoD ciudad = leerMapa(args[0]);                          //Llamamos al lector
            RecorrerDFS dfs = new RecorrerDFS(ciudad);                  //Creamos clase DFS con el grafo retornado por leerMapa
            dfs.chequearVia();                                          //Llamada al programa final




        }


}


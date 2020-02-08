import java.io.*;

public class Hamilton{


    public static Grafo cargarGrafo(String Archivo){

        BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
        String linea = Lector.readLine();
        int n = Integer.parseInt(linea);
        linea = Lector.readLine();
        m = Integer.parseInt(linea);
        
        Grafo mapa = new Grafo(n);

        int vert1;
        int vert2;

        for(int i= 0 ; i < m ; i++){

            linea = Lector.readLine();
            String[] ladoslinea = linea.split(" ");
            vert1 = Integer.parseInt(ladoslinea[0]);
            vert2 = Integer.parseInt(ladoslinea[1]);

            mapa.add(vert1, vert2);

        }
        return mapa;


    }

    public static void main(String[] args){

        Grafo mapa = cargarGrafo(args[0]);
        int r = random.nextInt(mapa.vertice);

        if(args[1].equals("DFS")){

            DFS dfs = new DFS(mapa);
            dfs.hacerDFS(r);

        }else if(args[1].equals("BFS")){

            BFS bfs = new BFS(mapa);
            bfs.hacerBFS(r);
        }





    }




    // Grafo mapa = new Grafo(6);
    // mapa.add(0,3);
    // mapa.add(3,1);
    // mapa.add(1,5);
    // mapa.add(5,4);
    // mapa.add(4,2);
    // mapa.add(0,4);
    
    // DFS dfs = new DFS(mapa);
    // //dfs.hacerDFS(0);
    // dfs.hacerBFS(0);
    
}

//probando
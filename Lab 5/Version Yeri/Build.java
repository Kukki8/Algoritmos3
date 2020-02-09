import java.io.*;
import java.util.Random;

public class Build{

    public static int Ini;

    public static GrafoD leerMapa(String Archivo) throws IOException {

        BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
        String linea = Lector.readLine();
        int n = Integer.parseInt(linea);
        linea = Lector.readLine();
        int m = Integer.parseInt(linea);

        GrafoD ciudad = new GrafoD(n);

        int edif1;
        int edif2;

        for(int i=0; i<m;i++){
                linea = Lector.readLine();
                String[] sinespacio = linea.split(" ");
                edif1 = Integer.parseInt(sinespacio[0]);
                edif2 = Integer.parseInt(sinespacio[1]);

                ciudad.add(edif1,edif2);


        }

        Lector.close();
        return ciudad;
        
    }

        public static void main( String [] args) throws IOException{
            GrafoD ciudad = leerMapa(args[0]);
            RecorrerDFS dfs = new RecorrerDFS(ciudad);
            Random random = new Random();
            int r = random.nextInt(ciudad.vertice);
            dfs.hacerDFS(r);




        }


}


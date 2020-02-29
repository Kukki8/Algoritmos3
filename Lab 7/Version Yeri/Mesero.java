import java.io.BufferedReader;
import java.io.*;

public class Mesero{
    
    public static GrafoND cargarRestaurante(String Archivo)throws IOException{
        BufferedReader lector = new BufferedReader(new FileReader(Archivo));
        String linea = lector.readLine();
        int n = Integer.parseInt(linea);        //Numero de nodos

        GrafoND restaurante = new GrafoND();

        for(int i = 0 ; i< n ; i++){
            linea = lector.readLine();
            String[] lineasinespacio = linea.split(" ");
            int x = Integer.parseInt(lineasinespacio[0]);
            int y = Integer.parseInt(lineasinespacio[1]);

           // System.out.println("X = " +x + " Y = "+ y+ " id = " + i);

            restaurante.agregarMesa(x, y, i);

        }

        linea = lector.readLine();
        int m = Integer.parseInt(linea);        //Numero de lados

        for(int j = 0 ; j< m ; j++){
            linea = lector.readLine();
            String[] lineasinespacio = linea.split(" ");
            int id1 = Integer.parseInt(lineasinespacio[0]);
            int id2 = Integer.parseInt(lineasinespacio[1]);

            Mesa m1 = restaurante.obtenerMesa(id1);
            Mesa m2 = restaurante.obtenerMesa(id2);

            restaurante.agregarLado(m1,m2);
        }

        lector.close();
        return restaurante;
    }

    public static void main(String[] args) throws IOException {
        GrafoND restaurante = cargarRestaurante(args[0]);
        Bellman bellman = new Bellman(restaurante);
        bellman.hacerBellman(Integer.parseInt(args[1]));


    }
}
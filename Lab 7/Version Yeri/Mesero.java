import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.*;

public class Mesero{
    
    public void cargarRestaurante(String Archivo){
        BufferedReader lector = new BufferedReader(new FileReader(Archivo));
        String linea = lector.readLine();
        int n = Integer.parseInt(linea);        //Numero de nodos

        GrafoND restaurante = new GrafoND()();

        for(int i = 0 ; i<n ; i++){
            linea = lector.readLine();
            String[] lineasinespacio = linea.split(" ");
            int x = Integer.parseInt(lineasinespacio[0]);
            int y = Integer.parseInt(lineasinespacio[1]);

            restaurante.agregarMesa(x, y,i);
        }

        linea = lector.readLine();
        int m = Integer.parseInt(linea);        //Numero de lados

        for(int j = 0 ; j<n ; j++){
            linea = lector.readLine();
            String[] lineasinespacio = linea.split(" ");
            int id1 = Integer.parseInt(lineasinespacio[0]);
            int id2 = Integer.parseInt(lineasinespacio[1]);

            
        }

    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class Orden{
    
    public static Grafo cargarGrafo(String doc) throws IOException{

        BufferedReader lector = new BufferedReader(new FileReader(doc));
        String linea = lector.readLine();

        Grafo grafito = new Grafo();

        int i = 0;
        while(linea != null){

            String[] division = linea.split(":");
            Vertices archivo = new Vertices(division[0] , i);
            grafito.agregarVertice(archivo);
            i += 1;
            String[] dependencias = division[1].split(" ");

            for(int j = 0 ; j< dependencias.length ; j++){
                Vertices dependencia = new Vertices(dependencias[j] , i);
                Lados l = new Lados( archivo , dependencia);
                grafito.agregarLado(l);
                i += 1;
            }
            i += 1;
            linea = lector.readLine();
        }

        System.out.println("Termine de leer");
        lector.close();
        return grafito;

    }



    public static void main(String args[]) throws IOException{

        Grafo grafito = cargarGrafo(args[0]);
        OrdenTop ordencito = new OrdenTop(grafito) ;
        ordencito.hacerdfsREC();


    }
}
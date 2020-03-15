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

            if(grafito.estaVertice(archivo.obtenerNombre()) == false){
                grafito.agregarVertice(archivo);
                i += 1;  
            }else{
                archivo = grafito.buscarVertice(division[0]);
            }
            
            String[] dependencias = division[1].split(" ");

            for(int j = 1 ; j< dependencias.length ; j++){
                Vertices dependencia = new Vertices(dependencias[j] , i);

                if(grafito.agregarVertice(dependencia) == true){
                    i+=1;
                }else{
                    dependencia = grafito.buscarVertice(dependencias[j]);
                }

                Lados l = new Lados(dependencia, archivo);
                grafito.agregarLado(l);
                
            }
            linea = lector.readLine();
        }

        
        lector.close();
        return grafito;

    }

    public static void main(String args[]) throws IOException{

        Grafo grafito = cargarGrafo(args[0]);
        OrdenTop ordencito = new OrdenTop(grafito) ;
        ordencito.hacerdfsREC();


    }
}
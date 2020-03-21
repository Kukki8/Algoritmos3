import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class PlanearTransbordos{


    public static String definirTipo(String archivo) throws IOException{                //Primero, determinamos cual es el tipo de grafo (Dirigido o noDirigido)

        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea = lector.readLine();
        lector.close();
        return linea;
    }

    public static void main(String[] args) throws IOException{

        String tipo = definirTipo(args[0]);
        
        if(tipo.equalsIgnoreCase("d")){                             //En caso de que el archivo contenga una d en su primera linea, creamos un grafo Dirigido.

            GrafoDirigido grafo = new GrafoDirigido();
            grafo.cargarGrafoMetro(args[0]);
        
            GrafoDirigido otro = grafo.cargarGrafoInducido(args[1]);
            BacktrackingD backtrack1 = new BacktrackingD(otro);
            BacktrackingD backtrack2 = new BacktrackingD(grafo);
        
            Ruta ruta1 = backtrack1.DFS(args[2], args[3]) ;
        
            if(ruta1 != null){
                ruta1.imprimirRutaD();
            }else{
                System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
            }
        
            System.out.println("\n");
        
            Ruta ruta2 = backtrack2.DFS(args[2], args[3]) ;
            System.out.println("Cuando todas las lineas estan operativas: ");
            ruta2.imprimirRutaD();

        }else if(tipo.equalsIgnoreCase("n")){                           //En caso de que el archivo contenga una n en su primera linea, creamos un grafo noDirigido.

            GrafoNoDirigido grafo = new GrafoNoDirigido();
            grafo.cargarGrafoMetro(args[0]);
        
            GrafoNoDirigido otro = grafo.cargarGrafoInducido(args[1]);
            BacktrackingND backtrack1 = new BacktrackingND(otro);
            BacktrackingND backtrack2 = new BacktrackingND(grafo);
        
            Ruta ruta1 = backtrack1.DFS(args[2], args[3]) ;
        
            if(ruta1 != null){
                ruta1.imprimirRutaND();
            }else{
                System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
            }
        
            System.out.println("\n");
        
            Ruta ruta2 = backtrack2.DFS(args[2], args[3]) ;
            System.out.println("Cuando todas las lineas estan operativas: ");
            ruta2.imprimirRutaND();
        }else{                                                                                                  //Caso en el que la primera linea del documento no contenga
            System.out.println("No es ninguno de los tipos posibles. Por favor, revise el documento.");         //ni una d ni una n
        }
        
    }
    
}
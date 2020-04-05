import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class PlanearTransbordos{

	// metodo getter static que retorna el tipo del grafo(mapa)
    public static String definirTipo(String archivo) throws IOException{                //Primero, determinamos cual es el tipo de grafo (Dirigido o noDirigido)

        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea = lector.readLine();
        lector.close();
        return linea;
    }

    public static void main(String[] args) throws IOException{

        String tipo = definirTipo(args[0]);                             // guarda en tipo el tipo del grafo(mapa)
        
        if(tipo.equalsIgnoreCase("d")){                                 //En caso de que el archivo contenga una d en su primera linea, creamos un grafo Dirigido.

            GrafoDirigido grafo = new GrafoDirigido();                  // crea el objeto de tipo grafoDirigido
            grafo.cargarGrafoMetro(args[0]);                            //carga todos los atributos del grafo(mapa)
        
            GrafoDirigido otro = grafo.cargarGrafoInducido(args[1]);    // genera el grafo inducido por las lineas(arcos) del grafo(mapa con lineas activas)
            BacktrackingD backtrack1 = new BacktrackingD(otro);         // realiza el backtracking a cada grafo por separado
            BacktrackingD backtrack2 = new BacktrackingD(grafo);
        
            Ruta ruta1 = backtrack1.DFS(args[2], args[3]) ;
        
            if(ruta1 != null){
                ruta1.imprimirRutaD();                                  // imprime la ruta genrada por le bactracking
            }else{
                System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
            }
        
            System.out.println("\n");
        
            Ruta ruta2 = backtrack2.DFS(args[2], args[3]) ;
            System.out.println("Cuando todas las lineas estan operativas: ");
            ruta2.imprimirRutaD();

        }else if(tipo.equalsIgnoreCase("n")){                               //En caso de que el archivo contenga una n en su primera linea, creamos un grafo noDirigido.

            GrafoNoDirigido grafo = new GrafoNoDirigido();                  // crea el objeto de tipo grafoNodirigido
            grafo.cargarGrafoMetro(args[0]);                                // carga todos los atributos del grafo(mapa)
            GrafoNoDirigido otro = grafo.cargarGrafoInducido(args[1]);      //genera el grafo inducido por las lineas del grafo(mapa con lineas activas)
            BacktrackingND backtrack1 = new BacktrackingND(otro);           //Realiza el backtracking a cada grafo por separado
            BacktrackingND backtrack2 = new BacktrackingND(grafo);
        
            Ruta ruta1 = backtrack1.DFS(args[2], args[3]) ;
        
            if(ruta1 != null){
                ruta1.imprimirRutaND();                                     // imprime la ruta generada por el bactracking
            }else{
                System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
            }
        
            System.out.println("\n");
        
            Ruta ruta2 = backtrack2.DFS(args[2], args[3]) ;
            System.out.println("Cuando todas las lineas estan operativas: ");
            ruta2.imprimirRutaND();                                            //imprime la ruta generada por el backtranking
        }else{                                                                                                  //Caso en el que la primera linea del documento no contenga
            System.out.println("No es ninguno de los tipos posibles. Por favor, revise el documento.");         //ni una d ni una n
        }
        
    }
    
}
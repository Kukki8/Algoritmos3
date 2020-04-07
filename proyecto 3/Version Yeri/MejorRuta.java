import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MejorRuta {
	
	// metodo getter static que retorna el tipo del grafo(mapa)
    public static String definirTipo(String archivo) throws IOException{                //Primero, determinamos cual es el tipo de grafo (Dirigido o noDirigido)

        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea = lector.readLine();
        lector.close();
        return linea;
    }
	

	public static void main(String[] args) throws IOException {
		String tipo = definirTipo(args[0]); 					// guarda en tipo el tipo del grafo(mapa)
		
		
		if(tipo.equalsIgnoreCase("n")) {						//En caso de que el archivo contenga una d en su primera linea, creamos un grafo Dirigido.

			GrafoNoDirigido otro = new GrafoNoDirigido();				//Crea el objeto de tipo grafoDirigido
			otro.cargarGrafoMetro(args[0]);
			GrafoNoDirigido grafo = otro.cargarGrafoInducido(args[1]);					//Genera el grafo inducido por las lineas(arcos) del grafo(mapa con lineas activas)
			AEstrella aEstrella = new AEstrella(grafo);									//Genera clase AEstrella a partir del grafo inducido
		
			LinkedList<Lado> camino = aEstrella.hacerAEstrella(args[2], args[3]);		//Realiza el recorrido de AEstrella
			aEstrella.imprimirCamino(camino, grafo.lineas);								//Imprime el camino obtenido

			System.out.println("\nMinimizando transbordos");
			BacktrackingND backtracking = new BacktrackingND(grafo);
			Ruta ruta = backtracking.DFS(args[2], args[3]);					//Realiza el backtracking al grafo inducido

			if(ruta != null){
                ruta.imprimirRutaND();                                     //Imprime la ruta generada por el bactracking
            }else{
                System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
            }
        

		}
		if(tipo.equalsIgnoreCase("d")) {									//En caso de que el archivo contenga una d en su primera linea, creamos un grafo Dirigido.
			GrafoDirigido otro = new GrafoDirigido();						//Crea el objeto de tipo grafoDirigido
			otro.cargarGrafoMetro(args[0]);
			GrafoDirigido grafo = otro.cargarGrafoInducido(args[1]);		//Genera el grafo inducido por las lineas(arcos) del grafo(mapa con lineas activas)
			AEstrella aEstrella = new AEstrella(grafo);						//Genera clase AEstrella a partir del grafo inducido

			LinkedList<Lado> camino = aEstrella.hacerAEstrella(args[2], args[3]);		//Realiza el recorrido de AEstrella
			aEstrella.imprimirCamino(camino, grafo.lineas);								//Imprime el camino obtenido

			System.out.println("\nMinimizando transbordos");
			BacktrackingD backtracking = new BacktrackingD(grafo);
			Ruta ruta = backtracking.DFS(args[2], args[3]);					//Realiza el backtracking al grafo inducido

			if(ruta != null){
				ruta.imprimirRutaD();                                     //Imprime la ruta generada por el bactracking
			}else{
				System.out.println("No hay recorrido que realizar, ya que el punto inicial y el final son el mismo");
			}

		}
  
	}

}






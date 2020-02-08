/*Estudiantes:
 * Yerimar Manzo: 14-10611
 * Jonathan Bautista: 16-10109

*/
import java.io.*;
/**<p>Programa para convertir archivos Lista de Adyacencia a archivos Matriz de
 * Adyacencia.</p>
 * 
 * <p>Un archivo Lista de Adyacencia representa un grafo, donde cada
 * l&iacute;nea tiene el formato:</p>
 * <blockquote>
 * <code>v<sub>i</sub>: v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>i</sub></code> es un n&uacute;mero de un v&eacute;rtice, y 
 * <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code> son los n&uacute;meros
 * de los v&eacute;rtices adyacentes a <code>v<sub>i</sub></code></p>
 * <p>mientras que un archivo Matriz de adyacencia representa un grafo
 * en el formato</p>
 * <blockquote>
 * <code> &nbsp; v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub><br>
 * v<sub>1</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * v<sub>2</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * &vellip;<br>
 * v<sub>n</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub></code> son
 * los n&uacute;meros que identifican a los v&eacute;rtices, y <code>a<sub>i</sub></code>
 * indica si existe un arco desde el v&eacute;rtice al inicio de esa fila, y el
 * v&eacute;rtice al que corresponde esa columna.</p>
 * <p>El programa {@link #main} lee un archivo, detecta (a trav&eacute;s de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a trav&eacute;s de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */
public class Cliente {
	/**Detecta el tipo de archivo basado en una muestra de una l&iacute;nea tomada del
	 * archivo.
	 * 
	 * @param  linea              La l&iacute;nea de muestra tomada del archivo
	 * @return <code>true</code>  si est&aacute; en el formato de un archivo lista de
	 *                            adyacencias;<br></br>
	 *         <code>false</code> si est&aacute; en el formato de un archivo Matriz de
	 *                            adyacencias.
	 * 
	 * @throws IllegalArgumentException si la l&iacute;nea no tiene ninguno de los dos
	 *                                  formatos
	 */
	static boolean esLista(String linea){
		String[]  lineasinespacio= linea.split(" ");               //Divide el String linea sin los " "                              
		if (lineasinespacio[0].equals("0:")){                      //Revisa si la linea es de la forma Lista de Adyacencia                              
			return true;
		}
		else if(lineasinespacio[0].equals("")){                    //Revisa si la linea tiene formato Matriz de Adyacencia                              
			return false;
		}
		else{
			throw new IllegalArgumentException("**El archivo no es una lista ni una matriz**");   //Si no es e ningun formato da error          
		}

	}
	/**Carga la <code>linea</code> de un archivo Lista de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarLista(String linea,TraductorDesdeLista Grafotraducido){
		
		String[] Lineasinespacio = linea.split(" ");            //Divide el String linea sin los " "                                      
		String[] vertice = Lineasinespacio[0].split(":");       // Divide El "Vi" del ":"                                       
		int verticeInicial = Integer.parseInt(vertice[0]);       //toma el "Vi" como inicial                          
		Grafotraducido.agregarVertices(verticeInicial);            //agrega a la lista el vertice tomado como inicial                             
		int verticeFinal;
		for(int i=1; i<Lineasinespacio.length; i++){
			verticeFinal = Integer.parseInt(Lineasinespacio[i]);
			Grafotraducido.agregarArco(verticeInicial, verticeFinal);
		}
	}
	/**Carga la <code>linea</code> de un archivo Matriz de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarMatriz(String linea, TraductorDesdeMatriz Grafotraducido)
	{
		String[] verticesinlinea;
		String[] lineasinespacio = linea.split(" ");
		if(lineasinespacio.length<=1){             // comprueba la linea donde son puras "---"                                        

		}
		else{
			if(lineasinespacio[0].equals("")){     // comprueba si es la priemra linea del archivo matriz adyacencia                                      

			}
			else{    
				verticesinlinea=lineasinespacio[0].split("|");
				
				int VerticeInicial = Integer.parseInt(verticesinlinea[0]); //convierte el "Vi" en un entero                
				for(int i=2;i<lineasinespacio.length;i++){
					if(lineasinespacio[i].equals("0")){                                   
						
					}
					else{
						int numerodeveces= Integer.parseInt(lineasinespacio[i]);     //Convierte la cantidad de arcos a  un entero entero             
						for(int j=0;j<numerodeveces;j++){                             //agrega la cantidad de arcos     
							Grafotraducido.agregarArco(VerticeInicial, i-2);           //Agrega el arco     
						}
						 
					}

				}

			}

		}


	}
	/**Detecta el n&uacute;mero de v&eacute;rtices en un archivo Matriz de Adyacencias 
	 * basado en una muestra de una l&iacute;nea tomada del archivo.
	 * 
	 * @param  linea  La l&iacute;nea del archivo que se desea cargar.
	 * @return        el n&uacute;mero de v&eacute;rtices detectado
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	public static int detectarVertices(String linea)throws IllegalArgumentException{
		String[] lineasinespacio = linea.split(" ");                                     
	
		if(lineasinespacio[0].equals("")){                                                    
			int Contador = 0;                                                  
			for(int i=3; i<lineasinespacio.length;i++){                                  
				Contador++;                                                               
			}
			return Contador;
		}
		else{
			throw new IllegalArgumentException("La linea no es de un archivo Matriz de Adyacencia");
		}
		
		
	}
	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link TraductorGrafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>TraductorGrafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	static TraductorGrafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		TraductorGrafo salida;
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		
		if(esLista(linea)){
			salida = new TraductorDesdeLista();
			do{
				cargarLista(linea, (TraductorDesdeLista)salida);
				linea=Lector.readLine();
			}while(linea != null);
		}else{
			salida = new TraductorDesdeMatriz(detectarVertices(linea));
			do{
				cargarMatriz(linea, (TraductorDesdeMatriz)salida);
				linea=Lector.readLine();
			}while(linea != null);
		}
		
		return salida;
	}
	/**Carga el grafo representado en el archivo dado y lo muestra en su
	 * representaci&oacute;n alternativa.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo como &uacute;nico elemento
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	public static void main(String[] args) throws IOException, IllegalArgumentException{
		if(args.length < 1){
			System.err.println("por favor ejecute: java Cliente <nombreArchivo>");
			return;
		}
		
		TraductorGrafo Grafo = cargarGrafo(args[0]);
		
		System.out.println(Grafo.imprimirGrafoTraducido());
	}
}
	
	
	
	
	
	
	
	


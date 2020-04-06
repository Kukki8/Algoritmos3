import java.io.IOException;
import java.util.*;

public interface Grafo {

	/**Carga en un grafo la informaci@n almacenada en el archivo de texto cuya direcci@n, incluyendo el
    * nombre del archivo, viene dada por archivo. El archivo dado tiene un formato determinado que se
    * indicar@ m@s abajo. Se retorna true si los datos del archivo son cargados satisfactoriamente en el
    * grafo, y false en caso contrario. Este m@todo debe manejar los casos en los que haya problemas al
    * abrir un archivo y el caso en el que el formato del archivo sea incorrecto.
	* @param Archivo.
	* @return True si el grafo se cargo correctamente false si no.
	* @throws IOException en caso de haber error con la lectura de larchivo
	*/
	public boolean cargarGrafo(String Archivo) throws IOException;
	/**
	 *Indica el n@mero de v@rtices que posee el grafo. 
	 * 
	 * @return numero de vertices
	 */
	public int numeroDeVertices();
	/**
	 * Indica el n@mero de Lados que posee el grafo
	 * @return numero de lados
	 */
	public int numeroDeLados();
	/**
	* Agrega el v@rtice v previamente creado al grafo g previamente creado. Si en el grafo no hay v@rtice
    * con el mismo identificador que el v@rtice v, entonces lo agrega al grafo y retorna true, de lo
    * contrario retorna false. 
	* @param v
	* @return True/false
	*/
	public boolean agregarVertice(Vertice v);
	/**
	 * crea un v@rtice con las caracter@sticas dadas y las agrega al grafo g previamente creado. Si en el grafo
	 * no hay v@rtice con el identificador id, entonces se crea un nuevo v@rtice y se agrega al grafo y se
	 * retorna true, de lo contrario retorna false.
	 * @param id
	 * @param nombre
	 * @param x
	 * @param y
	 * @param p
	 * @return true/false
	 */
	public boolean agregarVertice(int id, String nombre,double x, double y,double p);
	/**
	 * Retorna el v@rtice contenido en el grafo que posee el identificador id. En caso que en el grafo no
	 * contenga ning@n v@rtice con el identificador id, se lanza la excepci@n 
	 * @param id
	 * @return Vertice
	 */
	public Vertice obtenerVertice(int id);

	/**
	 * Retorna el v@rtice contenido en el grafo que posee el identificador id. En caso que en el grafo no
	 * contenga ning@n v@rtice con el identificador id, se lanza la excepci@n 
	 * @param nombre
	 * @return Vertice
	 */
	public Vertice obtenerVertice(String nombre);

	/**
	 * Se indica si un v@rtice con el identificador id, se encuentra o no en el grafo. Retorna true en caso de
	 * que el v@rtice pertenezca al grafo, false en caso contrario.
	 *
	 * @param id
	 * @return Vertice
	 */
	public boolean estaVertice(int id);
	/**
	 * Elimina el v@rtice del grafo g. Si existe un v@rtice identificado con id y @ste es eliminado exitosamente
	 *del grafo se retorna true, en caso contrario false.
	 * @param id
	 * @return true/false
	 */
	public boolean eliminarVertice(int id);
	/**
	 * Retorna una lista con los v@rtices del grafo g.
	 *
	 * @return Lista de interos
	 */
	public LinkedList<Integer> vertices();
	/**
	 * Retorna una lista con los lados del grafo g.
	 * @return Lista de Lado
	 */
	public LinkedList<Lado> lados();
	/**
	 * Calcula el grado del v@rtice identificado por id en el grafo g. En caso que en el grafo no contenga
	 * ning@n v@rtice con el identificador id, se lanza la excepci@n NoSuchElementException.
	 * @param id
	 * @return entero
	 */
	public int grado(int id);
	/**
	 * Obtiene los v@rtices adyacentes al v@rtice identicado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ning@n v@rtice con el identificador id, se lanza la excepci@n
	 * NoSuchElementException.
	 * @param id
	 * @return entero
	 */
	public LinkedList<Vertice> adyacentes(int id);
	/** Obtiene los lados incidentes al v@rtice identificado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ning@n v@rtice con el identificador id, se lanza la excepci@n
	 * NoSuchElementException. 
	 * @param id
	 * @return Lista de vertices
	 */
	public LinkedList<Lado> incidentes(int id);
	/**
	 * Retorna un nuevo grafo con la misma composici@n que el grafo de entrada.
	 * @return Grafo
	 */
	
	public Grafo clonar();
	/**
	 * Devuelve una representaci@n del contenido del grafo como una cadena de caracteres
	 * @return
	 */
	public String toString();
}

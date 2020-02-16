import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public interface Grafo {

	/**Carga en un grafo la información almacenada en el archivo de texto cuya dirección, incluyendo el
    * nombre del archivo, viene dada por archivo. El archivo dado tiene un formato determinado que se
    * indicará más abajo. Se retorna true si los datos del archivo son cargados satisfactoriamente en el
    * grafo, y false en caso contrario. Este método debe manejar los casos en los que haya problemas al
    * abrir un archivo y el caso en el que el formato del archivo sea incorrecto.
	* @param Archivo.
	* @return True si el grafo se cargo correctamente false si no.
	* @throws IOException en caso de haber error con la lectura de larchivo
	*/
	public boolean cargarGrafo(String Archivo) throws IOException;
	/**
	 *Indica el número de vértices que posee el grafo. 
	 * 
	 * @return numero de vertices
	 */
	public int numeroDeVertices();
	/**
	 * Indica el número de Lados que posee el grafo
	 * @return numero de lados
	 */
	public int numeroDeLados();
	/**
	* Agrega el vértice v previamente creado al grafo g previamente creado. Si en el grafo no hay vértice
    * con el mismo identificador que el vértice v, entonces lo agrega al grafo y retorna true, de lo
    * contrario retorna false. 
	* @param v
	* @return True/false
	*/
	public boolean agregarVertice(Vertice v);
	/**
	 * crea un vértice con las características dadas y las agrega al grafo g previamente creado. Si en el grafo
	 * no hay vértice con el identificador id, entonces se crea un nuevo vértice y se agrega al grafo y se
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
	 * Retorna el vértice contenido en el grafo que posee el identificador id. En caso que en el grafo no
	 * contenga ningún vértice con el identificador id, se lanza la excepción 
	 * @param id
	 * @return Vertice
	 */
	public Vertice obtenerVertice(int id);
	/**
	 * Se indica si un vértice con el identificador id, se encuentra o no en el grafo. Retorna true en caso de
	 * que el vértice pertenezca al grafo, false en caso contrario.
	 *
	 * @param id
	 * @return Vertice
	 */
	public boolean estaVertice(int id);
	/**
	 * Elimina el vértice del grafo g. Si existe un vértice identificado con id y éste es eliminado exitosamente
	 *del grafo se retorna true, en caso contrario false.
	 * @param id
	 * @return true/false
	 */
	public boolean eliminarVertice(int id);
	/**
	 * Retorna una lista con los vértices del grafo g.
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
	 * Calcula el grado del vértice identificado por id en el grafo g. En caso que en el grafo no contenga
	 * ningún vértice con el identificador id, se lanza la excepción NoSuchElementException.
	 * @param id
	 * @return entero
	 */
	public int grado(int id);
	/**
	 * Obtiene los vértices adyacentes al vértice identicado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
	 * NoSuchElementException.
	 * @param id
	 * @return entero
	 */
	public LinkedList<Vertice> adyacentes(int id);
	/** Obtiene los lados incidentes al vértice identificado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
	 * NoSuchElementException. 
	 * @param id
	 * @return Lista de vertices
	 */
	public LinkedList<Lado> incidentes(int id);
	/**
	 * Retorna un nuevo grafo con la misma composición que el grafo de entrada.
	 * @return Grafo
	 */
	
	public Grafo clonar();
	/**
	 * Devuelve una representación del contenido del grafo como una cadena de caracteres
	 * @return
	 */
	public String toString();
}

/*Estudiantes:
 * Yerimar Manzo: 14-10611
 * Jonathan Bautista: 16-10109

*/
import java.util.*;
/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeLista extends TraductorGrafo {
	/**Crea un grafo minimal*/
	LinkedList<LinkedList<Integer>> Primeralista;
	/**
	 * Constructor de la clase que crea una lista de lista.
	 * 
	 */
	public TraductorDesdeLista(){ //constructor e la clase inicializa la lista de lista
		Primeralista=new LinkedList<LinkedList<Integer>>();
		
	}
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	public void agregarVertices(int id) {
		for(LinkedList<Integer> e:Primeralista) {		
			if(e.get(0)==id) {
				return;
			}
		}
			LinkedList<Integer> Listadeadyacentes=new LinkedList<Integer>(); //crea una lista "coleccion" de enteros
			Listadeadyacentes.add(id); //añade el vertice a la lista
			Primeralista.add(Listadeadyacentes); //añade la lista a la principal
		
	}
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal) {
		for (LinkedList<Integer> e:Primeralista) {
			if(e.get(0)==verticeInicial) {
				e.add(verticeFinal);
			}
		}
		
	}

	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido() {
			String Traduccion="   ";
			boolean existe;
			int[] vertices=new int[Primeralista.size()];
			int j=0;
			for(LinkedList<Integer> e:Primeralista) {
				Traduccion=Traduccion+Integer.toString(e.get(0))+" ";
				vertices[j]=e.get(0);
				j++;
			}
			Traduccion=Traduccion+"\n";
			for(int i=0;i<Primeralista.size();i++) {
				Traduccion=Traduccion+"--";
			}
			Traduccion=Traduccion+"--\n";
			for (LinkedList<Integer> f:Primeralista) {
				Traduccion=Traduccion+Integer.toString(f.get(0))+ "| ";
				for(int x=0;x<vertices.length;x++) {
					existe=false;
					for (int z=1;z<f.size();z++) {
						if(f.get(z)==vertices[x]) {
							existe=true;
						}
					}
					if(existe==false) {
						Traduccion=Traduccion+"0 ";
					}
					else {
						Traduccion=Traduccion+"1 ";		
					}
					
				}
			Traduccion=Traduccion+"\n";
			
	
			}
			return Traduccion;	
	}
}

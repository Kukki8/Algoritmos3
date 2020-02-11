import java.util.LinkedList;

public class Grafo {
	private LinkedList<LinkedList<Integer>> listadyacencia;
	//constructor de la claseGrafo
	public Grafo() {
		 listadyacencia=new LinkedList<LinkedList<Integer>>();
	}
	
	public int Dametamaño() {
		 int p=listadyacencia.size();
		 return p;
	}
	public int Dametamañoadyacentes(int vertice) {
		int P;
		P=listadyacencia.get(vertice).size();
		
		return P;
	}
	public int DamePos(int vertice1,int vertice2) {
		int P;
		P=listadyacencia.get(vertice1).get(vertice2);
		return P;
	}
	public void agregarVertices(int id) {
		for(LinkedList<Integer> e:listadyacencia) {		
			if(e.get(0)==id) {
				return;
			}
		}
			LinkedList<Integer> Listadeadyacentes=new LinkedList<Integer>(); //crea una lista "coleccion" de enteros
			Listadeadyacentes.add(id); //añade el vertice a la lista
			listadyacencia.add(Listadeadyacentes); //añade la lista a la principal
		
	}
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal) {
		for (LinkedList<Integer> e:listadyacencia) {
			if(e.get(0)==verticeInicial) {
				e.add(verticeFinal);
			}
		}
		
	}
}

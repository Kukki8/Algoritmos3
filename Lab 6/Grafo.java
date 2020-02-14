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
	public void AgregarVertices() {
		LinkedList<Integer> Adyacentes=new LinkedList<Integer>();
		listadyacencia.add(Adyacentes);
	}
	
	public void AgregarLados(int v1,int v2) {
		listadyacencia.get(v1).add(v2);
	
	}
}
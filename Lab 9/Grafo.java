import java.util.LinkedList;

public class Grafo{
		LinkedList<Vertice> ListaVertices; //ListaVertice:lista de todos los jugadores con sus pesos y sus numeros 
	public Grafo() {
		this.ListaVertices=new LinkedList<Vertice>(); //lista de vertices
	}
	public int DameNumeroVertices() {
		return this.ListaVertices.size(); // retorna la cantidad de jugadores
	}
	
	public LinkedList<Vertice> DameLista(){ // retorna la lista de vertives
		return this.ListaVertices;
	}
	public void agregarVertice(Vertice vertice) { //agrega un nuevo jugador a la lista
		this.ListaVertices.add(vertice);
	}
}
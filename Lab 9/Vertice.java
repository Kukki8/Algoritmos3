
public class Vertice {
	private int peso; //atributos peso: peso del jugador
	private int id; // id: numero del jugador
	public Vertice(int id, int peso) { // metodo constructor de la clase vertice
		this.peso=peso;
		this.id=id;
	}
	public int DamePeso() { //retrna el peso del vertice
		return this.peso;
	}
	public int Dameid() { //retorna el id del vertice
		return this.id;
	}
}

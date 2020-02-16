
public abstract class Lado {
	/* Atributos de la clase lado:
	 * peso: peso del lado
	 * tipo: tipo del lado
	 * vi: vertice 1 que pertenece al lado
	 * vj: vertice 2  que pertenece al lado
	 */
	protected double peso;
	protected int tipo;
	 protected Vertice vi;
	protected Vertice vf;
	// constructor de la clase Lado
	public Lado(double peso,int tipo, Vertice v1, Vertice v2) {
		this.peso=peso;
		this.tipo=tipo;
		this.vi=v1;
		this.vf=v2;
		
	}
	/*Obtiene el peso del lado l.
	 *retorna el peso 
	 */
	public double obtenerPeso() {
		return this.peso;
	}
	//retorna si el lado incide en el vertice v
	public boolean incide(Vertice v) {
		boolean incide=true;
		if(v!=vi && v!=vf) {
			incide=false;
		}

		return incide;
	}
	// retorna el tipo de el lado
	public int obtenerTipo() {
		return this.tipo;
	}
	
	public abstract String toString();

}
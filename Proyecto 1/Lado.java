package proyecto1;
import java.util.*;
public abstract class Lado {
	/* Atributos de la clase lado:
	 * peso: peso del lado
	 * tipo: tipo del lado
	 * v1: vertice 1 que pertenece al lado
	 * v2: vertice 2  que pertenece al lado
	 */
	protected double peso;
	protected int tipo;
	 protected V�rtice vi;
	protected V�rtice vf;
	// constructor de la clase Lado
	public Lado(double peso,int tipo, V�rtice v1, V�rtice v2) {
		this.peso=peso;
		this.tipo=tipo;
		this.vi=v1;
		this.vf=v2;
		
	}
	//retorna el peso del lado
	public double getPeso() {
		return this.peso;
	}
	//retorna si el lado incide en el vertice v
	public boolean incide(V�rtice v) {
		boolean incide=true;
		if(v!=vi && v!=vf) {
			incide=false;
		}

		return incide;
	}
	// retorna el tipo de el lado
	public int getTipo() {
		return this.tipo;
	}
	
	public abstract String toString();

}

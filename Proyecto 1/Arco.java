
public class Arco extends Lado {
	 //constructor que llama al super constructor de la clase padre Lado
	public Arco(double peso, int tipo, Vertice v1, Vertice v2) {
		super(peso, tipo, v1, v2);
		// TODO Auto-generated constructor stub
	}
	// metodo que crea el arco
	public Arco CrearArco(double peso, int tipo, Vertice vi, Vertice vf) {
		Arco Arco=new Arco(peso, tipo, vi, vf);
		return Arco;
	}
	// retorna el vertice inicial
	public Vertice obtenerExtremoInicial() {
		return this.vi;
	}
	// retorna el vertice final
	public Vertice obtenerExtremoFinal() {
		return this.vf;
	}
	// si el vertice dado es inicial retorna true en otro caso false
	public boolean obtenerExtremoInicial(Vertice v) {
		boolean esExtremoInicial=true;
		if(v!=this.vi) {
			esExtremoInicial=false;
		}
		return esExtremoInicial;
	}
	// si el vertice dado es final retorna true en otro caso false
	public boolean esExtremoFinal(Vertice v) {
		boolean esExtremoFinal=true;
		if(v!=vf) {
			esExtremoFinal=false;
		}
		return esExtremoFinal;
	}
	// retorna un String con los datos de arco
	public String toString() {
		return "Extremo incial: " +vi+", Extrremo final: "+ vf+ ", Peso: "+ peso+
				", Tipo: "+ tipo;
	}
}
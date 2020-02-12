package proyecto1;

public class Arista extends Lado {

	public  Arista(double peso, int tipo, Vértice u, Vértice v) {
		super(peso, tipo, u, v);
		// TODO Auto-generated constructor stub
	}
	public Arista CrearArista(double peso, int tipo, Vértice u, Vértice v) {
		Arista Arista=new Arista(peso, tipo, u,v);
		return Arista;
	}
	public Vértice getExtremo1() {
		return this.vi;
	}
	public Vértice getExtremo2() {
		return this.vf;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Extremo 1: " +vi+", Extrremo 2: "+ vf+ ", Peso: "+ peso+
				", Tipo: "+ tipo;
	}

}

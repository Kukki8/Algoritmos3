package proyecto1;

public class Arista extends Lado {

	public  Arista(double peso, int tipo, V�rtice u, V�rtice v) {
		super(peso, tipo, u, v);
		// TODO Auto-generated constructor stub
	}
	public Arista CrearArista(double peso, int tipo, V�rtice u, V�rtice v) {
		Arista Arista=new Arista(peso, tipo, u,v);
		return Arista;
	}
	public V�rtice getExtremo1() {
		return this.vi;
	}
	public V�rtice getExtremo2() {
		return this.vf;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Extremo 1: " +vi+", Extrremo 2: "+ vf+ ", Peso: "+ peso+
				", Tipo: "+ tipo;
	}

}

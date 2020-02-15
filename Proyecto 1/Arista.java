public class Arista extends Lado {

	public  Arista(double peso, int tipo, Vertice u, Vertice v) {
		super(peso, tipo, u, v);
		// TODO Auto-generated constructor stub
	}
	public Arista CrearArista(double peso, int tipo, Vertice u, Vertice v) {
		return new Arista(peso, tipo, u,v);
		
	}
	public Vertice getExtremo1() {
		return this.vi;
	}
	public Vertice getExtremo2() {
		return this.vf;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Extremo 1: " +vi+", Extrremo 2: "+ vf+ ", Peso: "+ peso+
				", Tipo: "+ tipo;
	}
	
	
}


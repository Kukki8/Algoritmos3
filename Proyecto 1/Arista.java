
public class Arista extends Lado {

	public  Arista(double peso, int tipo, Vertice u, Vertice v) {
		super(peso, tipo, u, v);
		// TODO Auto-generated constructor stub
	}
	// crea la arista con los atributos pasados como parametro.
	public Arista CrearArista(double peso, int tipo, Vertice u, Vertice v) {
		return new Arista(peso, tipo, u,v);
		
	}
	//obtiene el extremo del primer vertice de esa arista
	public Vertice obtenerExtremo1() {
		return this.vi;
	}
	// ibtine el extremo del segundo vertice de esa arista
	public Vertice obtenerExtremo2() {
		return this.vf;
	}
	@Override
	public String aString() {
		// TODO Auto-generated method stub
		return "Extremo 1: " +vi.aString()+ "Extremo 2: "+ vf.aString()+ "Peso: "+ peso+
				"Tipo: "+ tipo;
	}
	
	
}



public class Grafos {
	Grafo grafo;
	Grafo grafoinv;
	public Grafos(Grafo grafo,Grafo grafoinv) {
		this.grafo=grafo;
		this.grafoinv=grafoinv;
	}
	public Grafo Damegrafo() {
		return grafo;
	}
	public Grafo Damegrafoinv() {
		return grafoinv;
	}
}

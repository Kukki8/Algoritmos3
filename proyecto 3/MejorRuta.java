import java.util.ArrayList;
import java.util.LinkedList;

public class MejorRuta {

	public static double funcionh(Vertice vi, Vertice vf) {
		double xi,yi,xf,yf;
		xi=vi.obtenerX();
		yi=vi.obtenerY();
		xf=vf.obtenerX();
		yf=vf.obtenerY();
		
		double distanciaX=xf-xi;
		double distanciaY=yf-yi;
		double distancia=Math.sqrt(Math.pow(distanciaX, 2)+Math.pow(distanciaY, 2));
		return distancia;
		
	}
	public static int DamePosdeVertice(int args, GrafoNoDirigido grafo) {
		int lugar = 0;
		for(int i=0;i<grafo.Grafo.size();i++) {
			if(grafo.Grafo.get(i).get(0).obtenerId()==args) {
				lugar=i;
			}
		}
		return lugar;
	}
	
	
	public static reconstruircamino(int[] vengodesde, Vertice actual) {
		
	}
	
	
	public static int[] A_start(GrafoNoDirigido grafo, String vi,  String  vf) {
		Vertice VerticeInicial = null, VerticeFinal = null;
		for(LinkedList<Vertice> e:grafo.Grafo) {
			if(e.get(0).obtenerNombre().equalsIgnoreCase(vi.trim())) {
				VerticeInicial=e.get(0);	
			}
			else if(e.get(0).obtenerNombre().equalsIgnoreCase(vf.trim())){
				VerticeFinal=e.get(0);
			}
		}
		double infinite = Double.POSITIVE_INFINITY ;
		LinkedList<Vertice> CaminosAbierto=new LinkedList<Vertice>();
		LinkedList<Vertice> CaminosCerrados=new LinkedList<Vertice>();
		int[] vengodesde=new int[grafo.numeroDeVertices()];
		double[] gscore= new double[grafo.numeroDeVertices()];
		double[] fscore=new double[grafo.numeroDeVertices()];
		for(int i=0;i<grafo.numeroDeVertices();i++) {
			gscore[i]=infinite;
		}
		for(int i=0;i<grafo.numeroDeVertices();i++) {
			fscore[i]=infinite;
		}
		int posVerticeInicial= DamePosdeVertice(VerticeInicial.obtenerId(), grafo);
		gscore[posVerticeInicial]=0;
		fscore[posVerticeInicial]=funcionh(VerticeInicial, VerticeFinal);
		CaminosAbierto.add(VerticeInicial);
		Vertice actual = null;
		double menor=fscore[0];
		int posmenor=0;
		while(CaminosAbierto.size()!=0){
			for(int i=0;i<fscore.length;i++) {
				if(fscore[i]<menor) {
					menor=fscore[i];
					posmenor=i;
				}
			}
			
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(DamePosdeVertice(grafo.Grafo.get(i).get(0).obtenerId(), grafo)==posmenor) {
					actual=grafo.Grafo.get(i).get(0);
					break;
				}
			}
			if(actual==VerticeFinal) {
				return reconstruircamino(vengodesde,actual);
			}
			CaminosCerrados.add(actual);
			CaminosAbierto.remove(actual);
			Arista ladoentreestaciones = null;
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(grafo.Grafo.get(i).get(0).equals(actual)) {
					for(int j=1;j<grafo.Grafo.get(i).size();j++) {
						Vertice vecino=grafo.Grafo.get(i).get(j);
						for(Arista e:grafo.Aristas) {
							if(e.obtenerExtremo1()==actual && e.obtenerExtremo2()==vecino) {
								ladoentreestaciones=e;
							}
						}
						int posdelvecino=DamePosdeVertice(grafo.Grafo.get(i).get(j).obtenerId(), grafo);
						int posdelactual=DamePosdeVertice(actual.obtenerId(), grafo);
						
						double gtentativa=gscore[posdelactual]+ladoentreestaciones.obtenerPeso();
						if(gtentativa<gscore[posdelvecino]) {
							vengodesde[posdelvecino]=actual.obtenerId();
							gscore[posdelvecino]=gtentativa;
							fscore[posdelvecino]=gscore[posdelvecino]+funcionh(vecino, VerticeFinal);
							if (!CaminosCerrados.contains(vecino)) {
								CaminosAbierto.add(vecino);
							}
						}
						
					}
				}
			}
			
			
			
		}
		return 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

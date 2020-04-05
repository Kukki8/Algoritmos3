import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
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
		double infinite = 99999.00;
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
		vengodesde[posVerticeInicial]=VerticeInicial.obtenerId();
		gscore[posVerticeInicial]=0;
		fscore[posVerticeInicial]=funcionh(VerticeInicial, VerticeFinal);
		CaminosAbierto.add(VerticeInicial);
		Vertice actual = null;
		int posmenor=0;
		while(CaminosAbierto.size()!=0){
			Hashtable<Integer, Integer> visitados = new Hashtable<Integer , Integer>();
			for(int i=0;i<grafo.Aristas.size();i++) {
				visitados.put(grafo.Aristas.get(i).obtenerTipo(), 0);
			}
			int[] nodosencaminosabiertos=new int[CaminosAbierto.size()];
			for(int i=0;i<nodosencaminosabiertos.length;i++) {
				nodosencaminosabiertos[i]=DamePosdeVertice(CaminosAbierto.get(i).obtenerId(), grafo);
			}
			double menor=999999.0;
			for(int i=0;i<nodosencaminosabiertos.length;i++) {
				if(fscore[nodosencaminosabiertos[i]]<menor) {
					menor=fscore[nodosencaminosabiertos[i]];
					posmenor=nodosencaminosabiertos[i];
				}
			}
			
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(DamePosdeVertice(grafo.Grafo.get(i).get(0).obtenerId(), grafo)==posmenor) {
					actual=grafo.Grafo.get(i).get(0);
					break;
				}
			}
			if(actual==VerticeFinal) {
				return vengodesde;
			}
			CaminosCerrados.add(actual);
			CaminosAbierto.remove(actual);
			Arista ladoentreestaciones = null;
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(grafo.Grafo.get(i).get(0).equals(actual)) {
			
					for(int j=1;j<grafo.Grafo.get(i).size();j++) {
						Vertice vecino=grafo.Grafo.get(i).get(j);
						for(Arista e:grafo.Aristas) {
							if(e.obtenerExtremo1()==actual && e.obtenerExtremo2()==vecino && visitados.get(e.obtenerTipo())==0  || e.obtenerExtremo1()==vecino && e.obtenerExtremo2()==actual && visitados.get(e.obtenerTipo())==0 ) {
								ladoentreestaciones=e;
								 visitados.replace(e.obtenerTipo(), 1);
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
		int[] vacio = null;
		return vacio;
	}
	

	public static void main(String[] args) throws IOException {

        GrafoNoDirigido otro = new GrafoNoDirigido();
        otro.cargarGrafoMetro(args[0]);
    
        GrafoNoDirigido grafo = otro.cargarGrafoInducido(args[1]);
        int[] camino;
        camino=A_start(grafo, args[2], args[3]);
       for(int i=0;i<camino.length;i++) {
        System.out.println(camino[i]);
       }
	}
}






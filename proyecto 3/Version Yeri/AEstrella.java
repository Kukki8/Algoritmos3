import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class AEstrella{

    public Grafo grafo;
    public double [] costos;
    public double [] estimado;

    public AEstrella(Grafo grafo){
        this.grafo = grafo;
        costos =  new double [grafo.numeroDeVertices()];
        estimado = new double [grafo.numeroDeVertices()];

        for(int i= 0 ; i < grafo.numeroDeVertices(); i++){
            costos[i] = Double.MAX_VALUE;
            estimado[i] = Double.MAX_VALUE;
        }
    }

    public int buscarIndice(int id){
        int i = 0;

        for(int v : grafo.vertices()) {
           if(v == id){
                return i;
           }
           i +=1;
        }
        return -1;
    }

	public double distanciaEuclidiana(Vertice vi, Vertice vf){

		double xi=vi.obtenerX();		
		double yi=vi.obtenerY(); 
		double xf=vf.obtenerX();
		double yf=vf.obtenerY();
		
		double distanciaX=xf-xi;
		double distanciaY=yf-yi; 
	
		return Math.sqrt(Math.pow(distanciaX, 2)+Math.pow(distanciaY, 2));

	}

    public LinkedList<Lado> hacerAEstrella(String nombreVi, String nombreVf) {
        Vertice vi = grafo.obtenerVertice(nombreVi);
        Vertice vf = grafo.obtenerVertice(nombreVf);
        int posVi = buscarIndice(vi.obtenerId());
        vi.asignarCosto(0);
        vi.asignarEstimado(distanciaEuclidiana(vi, vf));

        LinkedList<Lado> camino = new LinkedList<Lado>();
        Hashtable<Integer, Double> abiertos = new Hashtable<Integer,Double>();
        abiertos.put(vi.obtenerId(), vi.obtenerEstimado());
        
        Comparator<Vertice> comparador = new CompararVertice();

        PriorityQueue<Vertice> cola = new PriorityQueue<Vertice>(comparador);
        cola.add(vi);
        
        Hashtable<Integer, Double> cerrados = new  Hashtable<Integer, Double>();
		int[] preds = new int[grafo.numeroDeVertices()]; 
		Vertice actual =  new Vertice(0, "vDummy", 0,0,0);
        int vfID = vf.obtenerId();
		while(abiertos.size() > 0) {

            actual = cola.remove();
            int actualID = actual.obtenerId();
			if(actualID == vfID) {
				break;
			}

			abiertos.remove(actualID);
			cerrados.put(actualID, actual.obtenerCosto());

            LinkedList<Lado> conexiones = new LinkedList<Lado>();
            for(Lado l : grafo.lados()){
               if(l.vi.obtenerId() == actualID){
                    conexiones.add(l);
               } 
            }

			for(Lado l: conexiones) {

				Vertice nodoFinal = l.vf;
                int finalID = nodoFinal.obtenerId();
				if(cerrados.containsKey(finalID)) {
					continue;
				}

				double nodoFinalCostoTemt = actual.obtenerCosto() + l.obtenerPeso();
				
				if(!abiertos.containsKey(finalID)) {
					abiertos.put(finalID, nodoFinal.obtenerEstimado());
					cola.remove(nodoFinal);
				} else if (nodoFinalCostoTemt >= nodoFinal.obtenerCosto()) {
					continue;
                }
                int posVerFinal = buscarIndice(finalID);

				preds[posVerFinal] = buscarIndice(l.vi.obtenerId());
				nodoFinal.asignarCosto(nodoFinalCostoTemt);
				nodoFinal.asignarEstimado(nodoFinal.obtenerCosto() + distanciaEuclidiana(nodoFinal, vf));

			}

		}

		if(actual != vf) {
			System.out.println("No hay camino");
		} else {
			List<int> pathNum = new List<int>();
			int actualNode = actual.getNum();
			pathNum.Add(actualNode);
			path.addNode(actual.getPos());

			// Instanciar para ver el camino
			Instantiate(camino, actual.getPos() + new Vector3(0, 0.5f, 0), Quaternion.identity);
			///////////////////////////////////////////

			while(actualNode != vi.getNum()) {
				pathNum.Add(preds[actualNode]);
				path.addNode(g.nodos[preds[actualNode]].getPos());

				//Instanciar para ver el camino
				Instantiate(camino, g.nodos[preds[actualNode]].getPos() + new Vector3(0, 0.5f, 0), Quaternion.identity);
				/////////////////////////////////////////

				actualNode = preds[actualNode];
			}
			pathNum.Reverse();
			path.nodes.Reverse();
		}

		return path;
	}
}
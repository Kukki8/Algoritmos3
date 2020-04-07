import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Stack;

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

    public int buscarIndice(int id){			//Proceso que permite asignar un indice a un vertice en orden ascendiente
        int i = 0;

        for(int v : grafo.vertices()) {
           if(v == id){
                return i;
           }
           i +=1;
        }
        return -1;
    }

	public double distanciaEuclidiana(Vertice vi, Vertice vf){			//Funcion para obtener la distancia euclidiana

		double xi=vi.obtenerX();										//Buscamos las coordenadas X y Y de cada vertice
		double yi=vi.obtenerY(); 
		double xf=vf.obtenerX();
		double yf=vf.obtenerY();
		
		double distanciaX=xf-xi;										//Comenzamos formula
		double distanciaY=yf-yi; 
	
		return Math.sqrt(Math.pow(distanciaX, 2)+Math.pow(distanciaY, 2));		//Devolvemos resultado

	}

	public int obtenerLinea(Vertice actual, LinkedList<Lado> camino){		//Funcion que permite recuperar el tipo del lado, a partir de su vertice final
		int actualID = actual.obtenerId();									//Guardamos el ID del vertice evaluado
		for(Lado l: camino){

			if(l.vf.obtenerId() == actualID){								//Iteramos hasta conseguir un lado con vertice final igual al evaluado
				return l.obtenerTipo();										//Retornamos el tipo
			}
		}
		return -1;
	}


    public LinkedList<Lado> hacerAEstrella(String nombreVi, String nombreVf) {
        Vertice vi = grafo.obtenerVertice(nombreVi);						//Inicializamos todas las variables necesarias
        Vertice vf = grafo.obtenerVertice(nombreVf);
        int posVi = buscarIndice(vi.obtenerId());
        vi.asignarCosto(0);
        vi.asignarEstimado(distanciaEuclidiana(vi, vf));

        LinkedList<Lado> camino = new LinkedList<Lado>();
        Hashtable<Integer, Double> abiertos = new Hashtable<Integer,Double>();			//Tabla de hash que nos permitira saber los vertices que siguen abiertos
        abiertos.put(vi.obtenerId(), vi.obtenerEstimado());								//Colocamos el primer vertice a evaluar
        
        Comparator<Vertice> comparador = new CompararVertice();							//Clase utilizad para comparar vertices

        PriorityQueue<Vertice> cola = new PriorityQueue<Vertice>(comparador);			//Cola de prioridad que permitira elegir siempre el vertice de menor valor
        cola.add(vi);
        
        Hashtable<Integer, Double> cerrados = new  Hashtable<Integer, Double>();		//Tabla de hash que nos permitira saber que vertices ya fueron evaluados
		int[] preds = new int[grafo.numeroDeVertices()]; 								//Arreglo de predecesores
		preds[posVi] = -1;			
		Vertice actual =  new Vertice(0, "vDummy", 0,0,0);								//Vertice "dummy"
		int vfID = vf.obtenerId();
		int lineaActual = -1;

		while(abiertos.size() > 0) {							//Las iteraciones seguiran hasta que abiertos quede vacio.
			
			actual = cola.remove();								//Sacamos actual de la cola de prioridades
			lineaActual = obtenerLinea(actual, camino);			//Buscamos la line del nodo actual. Esto nos permitira tener una preferencia mas adelante
			
            int actualID = actual.obtenerId();
			if(actualID == vfID) {								//Verificamos si llegamos al nodo final
				break;
			}
			
			abiertos.remove(actualID);							//Sacamos el nodo evaluado de abiertos
			cerrados.put(actualID, actual.obtenerCosto());			//Agregamos el nodo evaluado a nodos cerrados

            LinkedList<Lado> conexiones = new LinkedList<Lado>();
            for(Lado l : grafo.lados()){							//Agregaos a la lista de conexiones, todos quellos lados cuyo nodo inicial sea el vertice evaluado
               if(l.vi.obtenerId() == actualID){
                    conexiones.add(l);
               } 
            }

			for(Lado l: conexiones) {							//Procedemos a evaluar todos los arcos en conexiones
				Vertice nodoFinal = l.vf;
                int finalID = nodoFinal.obtenerId();
				if(cerrados.containsKey(finalID)) {				//Si el nodo final del arco ya esta en cerrados, no seguimos evaluando, ya que ya pasamos por ahi.
					continue;
				}

				double nodoFinalCostoTemt = actual.obtenerCosto() + l.obtenerPeso();		//Calculamos el costo temporal, peso acumulado hasta el nodo actual + el costo si se parte por ahi
				
				if(!abiertos.containsKey(finalID)) {										//Si el vertice final no esta en abiertos, lo agregamos en la lista
					abiertos.put(finalID, nodoFinal.obtenerEstimado());						//y en la cola de prioridades, para futuras evaluaciones
					cola.add(nodoFinal);
				} else if (nodoFinalCostoTemt > nodoFinal.obtenerCosto()) {
					continue;																//Si el costo acumulado supera el costo de ir por el nodo final, no sigo evaluando
                }else if (nodoFinalCostoTemt == nodoFinal.obtenerCosto()) {
					boolean existe = false;													//Si consigo 2 nodos con costos igusles, procedo a elegir entre ambas lineas
					
					for(Lado lado: camino){

						if(lado.vf.obtenerId() == finalID){
							
							if(l.obtenerTipo() != lineaActual){								//Damos preferencia al nodo que conserve la linea de la misma ruta, de manera de que ahorramos tiempos de espera
								continue;
							}
							existe = true;
						}
					}
					if(!existe){
						continue;
					}
				}	
                int posVerFinal = buscarIndice(finalID);								//Buscamos el indice del nodo final

				
				preds[posVerFinal] = l.vi.obtenerId();									//Colocamos como predecesor del nodo escogido, al nodo evaluado
				nodoFinal.asignarCosto(nodoFinalCostoTemt);								//Asiganmos costos
				nodoFinal.asignarEstimado(nodoFinal.obtenerCosto() + distanciaEuclidiana(nodoFinal, vf));			//Asignamos costo estimado

				for(Lado a: camino){						//Metodo de reemplazo

					if(a.vi.obtenerId() == actualID && a.vf.obtenerId() == finalID){
						camino.remove(a);												//Cuando ya existia un arco que va al mismo vertice, se elimina el anterior
						break;
					}

				}
				camino.add(l);
			}
			
		}

		if(!actual.obtenerNombre().equals(nombreVf)){			//Caso en el que llegamos al final, luego evaluar todos los vertices y no se llego al final
			System.out.println("No se puede llegar al destino");
		}
		
		return reconstruirCamino(preds, vi, vf, camino);		//Llamada a reconstruccion de camino
	}

	//metodo que retorna una lista enlazada con el camino reconstruido entre dos vertices (estaciones) del mapa(grafo) para el caso No dirigido
	public LinkedList<Lado> reconstruirCamino( int[] predecesores, Vertice vi, Vertice vf, LinkedList<Lado> posiblesLados){
		LinkedList<Lado> caminoFinal = new LinkedList<Lado>();
		int idInicial = vf.obtenerId();
		int pos = buscarIndice(idInicial);
		int valor = predecesores[pos];
		Stack<Lado> camino = new Stack<Lado>();

		while( valor != -1) { 							//Recorrido en reverso del arreglo de predecesores
			for(Lado l : posiblesLados){				//Utilizamos una pila para guardar el camino
				if(l.vi.obtenerId() == valor  && l.vf.obtenerId() == idInicial){
					camino.push(l);		
					break;	
				}
			}
			idInicial = valor;
			pos = buscarIndice(valor);
			valor = predecesores[pos];
		}

		while(!camino.isEmpty()){
			caminoFinal.add(camino.pop()) ;				//Aprovechamos las propiedades de la pila, de manera de poder agregar los arcos a la lista en orden, solo con
		}												// agregar los sacados de la pila


		return caminoFinal;								//Retornamos el camino final
	}

	public void imprimirCamino(LinkedList<Lado> camino, Hashtable<String, Integer> tablita){		//Funcion de impresion del camino y su costo acumulado

		if(!camino.isEmpty()){
			double tiempo = 0;
			int lineaActual = -1;

			for(Lado a : camino){                               	
				int vInID = a.vi.obtenerId();
				int vFinID = a.vf.obtenerId();
				String vInNom = a.vi.obtenerNombre();
				String vFinNom = a.vf.obtenerNombre();
				int aColor = a.obtenerTipo();
				String aLlave = "" ;
				tiempo = tiempo + a.obtenerPeso();
				
				if(lineaActual != aColor){
					tiempo = tiempo + a.vi.obtenerPeso();
					lineaActual = aColor;
				}
				for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

					if(aColor == entrada.getValue()){
						aLlave = entrada.getKey();
						break;
					}
				}

				System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
			}
			System.out.println("Tiempo total: " + tiempo);
			
		}else{
			System.out.println("No hay recorrido que realizar.");     //Llegara a este caso en caso de que no consiga una ruta del punto inicial al
		}
	}
}
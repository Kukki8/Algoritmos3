import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class MejorRuta {
	
	//metodo getter que saca la distancia euclidiana entre dos vertices

	public static double funcionh(Vertice vi, Vertice vf) {
		double xi,yi,xf,yf;
		xi=vi.obtenerX();		
		yi=vi.obtenerY(); //obtiene la coordenada de cada vertice
		xf=vf.obtenerX();
		yf=vf.obtenerY();
		
		double distanciaX=xf-xi;
		double distanciaY=yf-yi; 
		double distancia=Math.sqrt(Math.pow(distanciaX, 2)+Math.pow(distanciaY, 2)); //formula de euclides
		return distancia;
		
	}
	//metodo que retorna la posicion del vertice dentro de la lista de adyacencia del grafo para el caso No Dirigido
	public static int DamePosdeVertice(int args, GrafoNoDirigido grafo) {
		int lugar = 0;
		for(int i=0;i<grafo.Grafo.size();i++) {
			if(grafo.Grafo.get(i).get(0).obtenerId()==args) { //chequea cada posicion hasta encontrar el vertice con el mismo id
				lugar=i;
			}
		}
		return lugar;
	}
	//metodo que retorna la posicion del vertice dentro de la lista de adyacencia del grafo para el caso No Dirigido
	public static int DamePosdeVerticeD(int args, GrafoDirigido grafo) {
		int lugar = 0;
		for(int i=0;i<grafo.listaVertices.size();i++) {
			if(grafo.listaVertices.get(i).get(0).obtenerId()==args) {//chequea cada posicion hasta encontrar el vertice con el mismo id
				lugar=i;
			}
		}
		return lugar;
	}
	
	//metodo que retorna una lista enlazada con el camino reconstruido entre dos vertices (estaciones) del mapa(grafo) para el caso No dirigido
	public static LinkedList<Integer> reconstruircamino( int[] predesesores, Vertice VerticeInicial, Vertice actual, GrafoNoDirigido grafo){
		LinkedList<Integer> camino=new LinkedList<Integer>();
		int pos;
		camino.addLast(actual.obtenerId()); // a�ade el primer id del ulltimo vertice la final de la lista
		int x=actual.obtenerId();
		while( x!=VerticeInicial.obtenerId()) { //chequea si x ya es el primer vertice
			pos=DamePosdeVertice(x,grafo); //obtiene la pos del id del veritce en el arreglo de predecesores 
			x=predesesores[pos]; //iguala x al vertice que esta antes del vertice en esa posicion
			camino.addFirst(x); // lo a�ade de primero en la lista
		}
		return camino;
	}
	//metodo que retorna una lista enlazada con el camino reconstruido entre dos vertices (estaciones) del mapa(grafo) para el caso Dirigido
	public static LinkedList<Integer> reconstruircaminoD( int[] predesesores, Vertice VerticeInicial, Vertice actual, GrafoDirigido grafo){
		LinkedList<Integer> camino=new LinkedList<Integer>();
		int pos;
		camino.addLast(actual.obtenerId());// a�ade el primer id del ulltimo vertice la final de la lista
		int x=actual.obtenerId();
		while( x!=VerticeInicial.obtenerId()) {//chequea si x ya es el primer vertice
			pos=DamePosdeVerticeD(x,grafo);//obtiene la pos del id del veritce en el arreglo de predecesores 
			x=predesesores[pos];//iguala x al vertice que esta antes del vertice en esa posicion
			camino.addFirst(x);// lo a�ade de primero en la lista
		}
		return camino;
	}
	//metodo A* que devuelve el camino entre dos vertices(inicial/final) para el caso De No Dirigido
	public static LinkedList<Integer> A_start(GrafoNoDirigido grafo, String vi,  String  vf) {
		Vertice VerticeInicial = null, VerticeFinal = null;
		for(LinkedList<Vertice> e:grafo.Grafo) {
			if(e.get(0).obtenerNombre().equalsIgnoreCase(vi.trim())) {
				VerticeInicial=e.get(0);	//cheque cual es el el vertice inicial dentro del grafo
			}
			else if(e.get(0).obtenerNombre().equalsIgnoreCase(vf.trim())){
				VerticeFinal=e.get(0); // chequea cuale es el vertice final dentro del grafo
			}
		}
		double infinite = 99999.00; //crea un posible valor muy alto
		LinkedList<Vertice> CaminosAbierto=new LinkedList<Vertice>(); //lista de caminos abiertos
		LinkedList<Vertice> CaminosCerrados=new LinkedList<Vertice>();// lista de caminos cerrados
		int[] vengodesde=new int[grafo.numeroDeVertices()]; //matriz de predecesores 
		double[] gscore= new double[grafo.numeroDeVertices()]; //arrglo donde se cuarda el resultado de la funcion g(valor de la arista) de cada vertice
		double[] fscore=new double[grafo.numeroDeVertices()]; //arreglo donde se guarda el resultado de la funcion f el cual es la suma de g+h de cada vertice
		for(int i=0;i<grafo.numeroDeVertices();i++) {
			gscore[i]=infinite; //inicializa todos los valores de cada vertice con un valor muy grande
		}
		for(int i=0;i<grafo.numeroDeVertices();i++) {
			fscore[i]=infinite; //inicializa todos los valores de cada vertice con un valor muy grande
		}
		int posVerticeInicial= DamePosdeVertice(VerticeInicial.obtenerId(), grafo); 
		vengodesde[posVerticeInicial]=VerticeInicial.obtenerId(); //coloca que el Vertice inciial llega desde el mismo para realizar mejor la reconstruccion del camino
		gscore[posVerticeInicial]=0;//puesto que no existe una arista que llegue en el vertice inicial su valor sera 0
		fscore[posVerticeInicial]=funcionh(VerticeInicial, VerticeFinal); //coloca el valor de f del vertice inicial el cual solo sera la funcion h
		CaminosAbierto.add(VerticeInicial); ///a�ade a camino abierto el vertice inicial
		Vertice actual = null;
		int posmenor=0;
		while(CaminosAbierto.size()!=0){
			Hashtable<Integer, Integer> visitados = new Hashtable<Integer , Integer>();
			for(int i=0;i<grafo.Aristas.size();i++) {
				visitados.put(grafo.Aristas.get(i).obtenerTipo(), 0);//crea una tabla dde hash con todos los tipos de lineas para mas adelante saber si fueron estas la visitadas, puesto que lo que se tiene es un multigrafo
			}
			int[] nodosencaminosabiertos=new int[CaminosAbierto.size()];
			for(int i=0;i<nodosencaminosabiertos.length;i++) {
				nodosencaminosabiertos[i]=DamePosdeVertice(CaminosAbierto.get(i).obtenerId(), grafo);
			}
			double menor=999999.0;
			for(int i=0;i<nodosencaminosabiertos.length;i++) {  //obtiene el nodo con menor valor f en caminos abiertos
				if(fscore[nodosencaminosabiertos[i]]<menor) {
					menor=fscore[nodosencaminosabiertos[i]];
					posmenor=nodosencaminosabiertos[i];
				}
			}
			
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(DamePosdeVertice(grafo.Grafo.get(i).get(0).obtenerId(), grafo)==posmenor) {
					actual=grafo.Grafo.get(i).get(0); //establece com oel actual al vertice con menor f
					break;
				}
			}
			if(actual==VerticeFinal) {
				return reconstruircamino(vengodesde, VerticeInicial, actual, grafo); //si actua les igual al final termianmos y reconstruimos el camino
				
			}
			CaminosCerrados.add(actual); //a�ade a caminos cerrdos el actual y quita lo quita de los abiertos
			CaminosAbierto.remove(actual);
			Arista ladoentreestaciones = null;
			for(int i=0;i<grafo.Grafo.size();i++) {
				if(grafo.Grafo.get(i).get(0).equals(actual)) { //se para en el vertice actual y chequeca cada vecino
			
					for(int j=1;j<grafo.Grafo.get(i).size();j++) {
						Vertice vecino=grafo.Grafo.get(i).get(j);
						for(Arista e:grafo.Aristas) {
							if(e.obtenerExtremo1()==actual && e.obtenerExtremo2()==vecino && visitados.get(e.obtenerTipo())==0  || e.obtenerExtremo1()==vecino && e.obtenerExtremo2()==actual && visitados.get(e.obtenerTipo())==0 ) {//como se dispone de un multigrafo es necesario saber si ya se paso por la arista de la misma linea por ende se procede a ver su visita en la tabla de hash
								ladoentreestaciones=e; //obtiene la arista
								 visitados.replace(e.obtenerTipo(), 1); //la coloca como vicitados
							}
						}
						int posdelvecino=DamePosdeVertice(grafo.Grafo.get(i).get(j).obtenerId(), grafo); //obtine la pos del vecino y del actual
						int posdelactual=DamePosdeVertice(actual.obtenerId(), grafo);
						double gtentativa=gscore[posdelactual]+ladoentreestaciones.obtenerPeso(); //suma la el valor de la funcion g + el tiempo de la nueva arista
						if(gtentativa<gscore[posdelvecino]) {
							vengodesde[posdelvecino]=actual.obtenerId();
							gscore[posdelvecino]=gtentativa; // reemplza cada valor repectivo
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
		LinkedList<Integer> caminovacio=null;
		return caminovacio;
	}
	//metodo A* que devuelve el camino entre dos vertices(inicial/final) para el caso De No Dirigido
		public static LinkedList<Integer> A_startD(GrafoDirigido grafo, String vi,  String  vf) {
			Vertice VerticeInicial = null, VerticeFinal = null;
			for(LinkedList<Vertice> e:grafo.listaVertices) {
				if(e.get(0).obtenerNombre().equalsIgnoreCase(vi.trim())) {
					VerticeInicial=e.get(0);	//cheque cual es el el vertice inicial dentro del grafo
				}
				else if(e.get(0).obtenerNombre().equalsIgnoreCase(vf.trim())){
					VerticeFinal=e.get(0); // chequea cuale es el vertice final dentro del grafo
				}
			}
			double infinite = 99999.00; //crea un posible valor muy alto
			LinkedList<Vertice> CaminosAbierto=new LinkedList<Vertice>(); //lista de caminos abiertos
			LinkedList<Vertice> CaminosCerrados=new LinkedList<Vertice>();// lista de caminos cerrados
			int[] vengodesde=new int[grafo.numeroDeVertices()]; //matriz de predecesores 
			double[] gscore= new double[grafo.numeroDeVertices()]; //arrglo donde se cuarda el resultado de la funcion g(valor de la arista) de cada vertice
			double[] fscore=new double[grafo.numeroDeVertices()]; //arreglo donde se guarda el resultado de la funcion f el cual es la suma de g+h de cada vertice
			for(int i=0;i<grafo.numeroDeVertices();i++) {
				gscore[i]=infinite; //inicializa todos los valores de cada vertice con un valor muy grande
			}
			for(int i=0;i<grafo.numeroDeVertices();i++) {
				fscore[i]=infinite; //inicializa todos los valores de cada vertice con un valor muy grande
			}
			int posVerticeInicial= DamePosdeVerticeD(VerticeInicial.obtenerId(), grafo); 
			vengodesde[posVerticeInicial]=VerticeInicial.obtenerId(); //coloca que el Vertice inciial llega desde el mismo para realizar mejor la reconstruccion del camino
			gscore[posVerticeInicial]=0;//puesto que no existe una arista que llegue en el vertice inicial su valor sera 0
			fscore[posVerticeInicial]=funcionh(VerticeInicial, VerticeFinal); //coloca el valor de f del vertice inicial el cual solo sera la funcion h
			CaminosAbierto.add(VerticeInicial); ///a�ade a camino abierto el vertice inicial
			Vertice actual = null;
			int posmenor=0;
			while(CaminosAbierto.size()!=0){
				Hashtable<Integer, Integer> visitados = new Hashtable<Integer , Integer>();
				for(int i=0;i<grafo.listaArcos.size();i++) {
					visitados.put(grafo.listaArcos.get(i).obtenerTipo(), 0);//crea una tabla dde hash con todos los tipos de lineas para mas adelante saber si fueron estas la visitadas, puesto que lo que se tiene es un multigrafo
				}
				int[] nodosencaminosabiertos=new int[CaminosAbierto.size()];
				for(int i=0;i<nodosencaminosabiertos.length;i++) {
					nodosencaminosabiertos[i]=DamePosdeVerticeD(CaminosAbierto.get(i).obtenerId(), grafo);
				}
				double menor=999999.0;
				for(int i=0;i<nodosencaminosabiertos.length;i++) {  //obtiene el nodo con menor valor f en caminos abiertos
					if(fscore[nodosencaminosabiertos[i]]<menor) {
						menor=fscore[nodosencaminosabiertos[i]];
						posmenor=nodosencaminosabiertos[i];
					}
				}
				
				for(int i=0;i<grafo.listaVertices.size();i++) {
					if(DamePosdeVerticeD(grafo.listaVertices.get(i).get(0).obtenerId(), grafo)==posmenor) {
						actual=grafo.listaVertices.get(i).get(0); //establece com oel actual al vertice con menor f
						break;
					}
				}
				if(actual==VerticeFinal) {
					return reconstruircaminoD(vengodesde, VerticeInicial, actual, grafo); //si actua les igual al final termianmos y reconstruimos el camino
				}
				CaminosCerrados.add(actual); //a�ade a caminos cerrdos el actual y quita lo quita de los abiertos
				CaminosAbierto.remove(actual);
				Arco ladoentreestaciones = null;
				for(int i=0;i<grafo.listaVertices.size();i++) {
					if(grafo.listaVertices.get(i).get(0).equals(actual)) { //se para en el vertice actual y chequeca cada vecino
				
						for(int j=1;j<grafo.listaVertices.get(i).size();j++) {
							Vertice vecino=grafo.listaVertices.get(i).get(j);
							for(Arco e:grafo.listaArcos) {
								if(e.obtenerExtremoInicial()==actual && e.obtenerExtremoFinal()==vecino && visitados.get(e.obtenerTipo())==0  || e.obtenerExtremoInicial()==vecino && e.obtenerExtremoFinal()==actual && visitados.get(e.obtenerTipo())==0 ) {//como se dispone de un multigrafo es necesario saber si ya se paso por la arista de la misma linea por ende se procede a ver su visita en la tabla de hash
									ladoentreestaciones=e; //obtiene la arista
									 visitados.replace(e.obtenerTipo(), 1); //la coloca como vicitados
								}
							}
							int posdelvecino=DamePosdeVerticeD(grafo.listaVertices.get(i).get(j).obtenerId(), grafo); //obtine la pos del vecino y del actual
							int posdelactual=DamePosdeVerticeD(actual.obtenerId(), grafo);
							double gtentativa=gscore[posdelactual]+ladoentreestaciones.obtenerPeso(); //suma la el valor de la funcion g + el tiempo de la nueva arista
							if(gtentativa<gscore[posdelvecino]) {
								vengodesde[posdelvecino]=actual.obtenerId();
								gscore[posdelvecino]=gtentativa; // reemplza cada valor repectivo
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
			LinkedList<Integer> caminovacio=null;
			return caminovacio;
		}
		//metodo que imprime la ruta con el tiempo(comapara cual de las aristas sirven y cuales no) para el caso de No dirigido
	public static void Ruta( GrafoNoDirigido grafo, LinkedList<Integer> camino)throws IOException {
		if(camino==null) {
			System.out.println("No existe la ruta");
		}else {
		double contador=0;
		int v1Id=0,v2Id=0;
		String v1nombre,v2nombre; //crea todas las variables que se usaran para la impresion
		String salida="";
		Vertice v1=null,v2=null;
		LinkedList<Arista> todaslasarista=new LinkedList<Arista>();
		Hashtable<Integer, Integer> visitados = new Hashtable<Integer , Integer>();
		
		for(int i=0;i<grafo.Aristas.size();i++) {
			visitados.put(grafo.Aristas.get(i).obtenerTipo(), 0);
		}
		for(Arista e:grafo.Aristas) {
			for(int i=0;i<camino.size()-1;i++) {
				if(camino.get(i)==e.obtenerExtremo1().obtenerId() && camino.get(i+1)==e.obtenerExtremo2().obtenerId()) {
					todaslasarista.add(e);
				}
			}  
		}
		LinkedList<Arista> aristaaeliminar=new LinkedList<Arista>(); //va haciendo comparaciones buscando si la arista posee menor tiempo de todas puesto que se dispone de un multigrafo
		for(int i=0;i<todaslasarista.size();i++) {
			Arista e=todaslasarista.get(i);
			for(int j=i+1;j<todaslasarista.size();j++) {
				if(e.obtenerExtremo1()==todaslasarista.get(j).obtenerExtremo1() && e.obtenerExtremo2()==todaslasarista.get(j).obtenerExtremo2() ) {
					aristaaeliminar.add(todaslasarista.get(j));
					aristaaeliminar.add(e);
				}
				
			}
		}
		int tipo=todaslasarista.get(0).obtenerTipo();
		for(int i=0;i<aristaaeliminar.size();i++) {
			if(aristaaeliminar.get(i).obtenerTipo()==tipo) {
				aristaaeliminar.remove(aristaaeliminar.get(i));
			}
		}
	for(int j=0;j<aristaaeliminar.size();j++) {
		for(int i=0;i<todaslasarista.size();i++) {
			if(todaslasarista.get(i)==aristaaeliminar.get(j)) {  //finalmente se eliminan las aristas que no se usaran par ala impresion del trayecto
				todaslasarista.remove(aristaaeliminar.get(j));
			}
		}
	}
		Hashtable<String, Integer> tablita=grafo.lineas;
		contador=todaslasarista.get(0).obtenerPeso();
		for(int i=0;i<todaslasarista.size();i++){
            if(i==0) {
			Arista a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
            int vInID = a.obtenerExtremo1().obtenerId();
            int vFinID = a.obtenerExtremo2().obtenerId();
            String vInNom = a.obtenerExtremo1().obtenerNombre();
            String vFinNom = a.obtenerExtremo2().obtenerNombre();
            contador=contador+todaslasarista.get(i).obtenerPeso();  //dependiendo e l caso sumamos el tiempo si requiere un transbordo se suma el timp odle transbordo
            int aColor = a.obtenerTipo();
            String aLlave = "" ;

            for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                if(aColor == entrada.getValue()){
                    aLlave = entrada.getKey();
                    break;
                }
            }

            System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
            }
            if(i!=0) {
            	if(todaslasarista.get(i).obtenerTipo()!=todaslasarista.get(i-1).obtenerTipo()){
            		Arista a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
                    int vInID = a.obtenerExtremo1().obtenerId();
                    int vFinID = a.obtenerExtremo2().obtenerId();
                    String vInNom = a.obtenerExtremo1().obtenerNombre();
                    String vFinNom = a.obtenerExtremo2().obtenerNombre();
                    contador=contador+todaslasarista.get(i).obtenerPeso()+todaslasarista.get(i).obtenerExtremo1().obtenerPeso();
         
                    int aColor = a.obtenerTipo();
                    String aLlave = "" ;

                    for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                        if(aColor == entrada.getValue()){
                            aLlave = entrada.getKey();
                            break;
                        }
                    }

                    System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
                    }
            		if(todaslasarista.get(i).obtenerTipo()==todaslasarista.get(i-1).obtenerTipo()) {
            			Arista a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
                        int vInID = a.obtenerExtremo1().obtenerId();
                        int vFinID = a.obtenerExtremo2().obtenerId();
                        String vInNom = a.obtenerExtremo1().obtenerNombre();
                        String vFinNom = a.obtenerExtremo2().obtenerNombre();
                        contador=contador+todaslasarista.get(i).obtenerPeso();
             
                        int aColor = a.obtenerTipo();
                        String aLlave = "" ;

                        for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                            if(aColor == entrada.getValue()){
                                aLlave = entrada.getKey();
                                break;
                            }
                        }

                        System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
                        
            	}
            }
        }	
		int tiempo;
		System.out.println("Tiempo toal: "+ (int)contador+ " minutos "); //imprime el tiempo que le toma hacer todo el recorrido
	
		}
	}
	//metodo que imprime la ruta con el tiempo(comapara cual de las aristas sirven y cuales no) para el caso de No dirigido
public static void RutaD( GrafoDirigido grafo, LinkedList<Integer> camino) throws IOException{
	if(camino==null) {
		System.out.println("No existe una ruta");
	}else {
	double contador=0;
	int v1Id=0,v2Id=0;
	String v1nombre,v2nombre; //crea todas las variables que se usaran para la impresion
	String salida="";
	Vertice v1=null,v2=null;
	LinkedList<Arco> todaslasarista=new LinkedList<Arco>();
	Hashtable<Integer, Integer> visitados = new Hashtable<Integer , Integer>();
	for(int i=0;i<grafo.listaArcos.size();i++) {
		visitados.put(grafo.listaArcos.get(i).obtenerTipo(), 0);
	}
	for(Arco e:grafo.listaArcos) {
		for(int i=0;i<camino.size()-1;i++) {
			if(camino.get(i)==e.obtenerExtremoInicial().obtenerId() && camino.get(i+1)==e.obtenerExtremoFinal().obtenerId()) {
				todaslasarista.add(e);
			}
		}  
	}
	LinkedList<Arco> aristaaeliminar=new LinkedList<Arco>(); //va haciendo comparaciones buscando si la arista posee menor tiempo de todas puesto que se dispone de un multigrafo
	for(int i=0;i<todaslasarista.size();i++) {
		Arco e=todaslasarista.get(i);
		for(int j=i+1;j<todaslasarista.size();j++) {
			if(e.obtenerExtremoInicial()==todaslasarista.get(j).obtenerExtremoInicial() && e.obtenerExtremoFinal()==todaslasarista.get(j).obtenerExtremoFinal() ) {
				aristaaeliminar.add(todaslasarista.get(j));
				aristaaeliminar.add(e);
			}
			
		}
	}
	int tipo=todaslasarista.get(0).obtenerTipo();
	for(int i=0;i<aristaaeliminar.size();i++) {
		if(aristaaeliminar.get(i).obtenerTipo()==tipo) {
			aristaaeliminar.remove(aristaaeliminar.get(i));
		}
	}
for(int j=0;j<aristaaeliminar.size();j++) {
	for(int i=0;i<todaslasarista.size();i++) {
		if(todaslasarista.get(i)==aristaaeliminar.get(j)) {  //finalmente se eliminan las aristas que no se usaran par ala impresion del trayecto
			todaslasarista.remove(aristaaeliminar.get(j));
		}
	}
}
	Hashtable<String, Integer> tablita=grafo.lineas;
	contador=todaslasarista.get(0).obtenerPeso();
	for(int i=0;i<todaslasarista.size();i++){
        if(i==0) {
		Arco a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
        int vInID = a.obtenerExtremoInicial().obtenerId();
        int vFinID = a.obtenerExtremoFinal().obtenerId();
        String vInNom = a.obtenerExtremoInicial().obtenerNombre();
        String vFinNom = a.obtenerExtremoFinal().obtenerNombre();
        contador=contador+todaslasarista.get(i).obtenerPeso();  //dependiendo e l caso sumamos el tiempo si requiere un transbordo se suma el timp odle transbordo
        int aColor = a.obtenerTipo();
        String aLlave = "" ;

        for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

            if(aColor == entrada.getValue()){
                aLlave = entrada.getKey();
                break;
            }
        }

        System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
        }
        if(i!=0) {
        	if(todaslasarista.get(i).obtenerTipo()!=todaslasarista.get(i-1).obtenerTipo()){
        		Arco a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
                int vInID = a.obtenerExtremoInicial().obtenerId();
                int vFinID = a.obtenerExtremoFinal().obtenerId();
                String vInNom = a.obtenerExtremoInicial().obtenerNombre();
                String vFinNom = a.obtenerExtremoFinal().obtenerNombre();
                contador=contador+todaslasarista.get(i).obtenerPeso()+todaslasarista.get(i).obtenerExtremoInicial().obtenerPeso();
     
                int aColor = a.obtenerTipo();
                String aLlave = "" ;

                for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                    if(aColor == entrada.getValue()){
                        aLlave = entrada.getKey();
                        break;
                    }
                }

                System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
                }
        		if(todaslasarista.get(i).obtenerTipo()==todaslasarista.get(i-1).obtenerTipo()) {
        			Arco a = todaslasarista.get(i);                                 //Realizamos un casting para trabajar con atributos de GrafoDirigido
                    int vInID = a.obtenerExtremoInicial().obtenerId();
                    int vFinID = a.obtenerExtremoFinal().obtenerId();
                    String vInNom = a.obtenerExtremoInicial().obtenerNombre();
                    String vFinNom = a.obtenerExtremoFinal().obtenerNombre();
                    contador=contador+todaslasarista.get(i).obtenerPeso();
         
                    int aColor = a.obtenerTipo();
                    String aLlave = "" ;

                    for(Map.Entry<String,Integer> entrada: tablita.entrySet()){

                        if(aColor == entrada.getValue()){
                            aLlave = entrada.getKey();
                            break;
                        }
                    }

                    System.out.println("Tome la linea " + aLlave+ " desde " + vInID + " " + vInNom + " hasta " + vFinID + " " + vFinNom);
                    
        	}
        }
    }	
	int tiempo;
	System.out.println("Tiempo toal: "+ (int)contador+ " minutos "); //imprime el tiempo que le toma hacer todo el recorrido
	
	
	
	}

	
}
	// metodo getter static que retorna el tipo del grafo(mapa)
    public static String definirTipo(String archivo) throws IOException{                //Primero, determinamos cual es el tipo de grafo (Dirigido o noDirigido)

        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea = lector.readLine();
        lector.close();
        return linea;
    }
	

	public static void main(String[] args) throws IOException {
		String tipo = definirTipo(args[0]); // guarda en tipo el tipo del grafo(mapa)
		
		
		if(tipo.equalsIgnoreCase("n")) {
			try {
			GrafoNoDirigido otro = new GrafoNoDirigido();
			otro.cargarGrafoMetro(args[0]);
    
			GrafoNoDirigido grafo = otro.cargarGrafoInducido(args[1]);
			LinkedList<Integer> camino;
			LinkedList<Arista> aristas;
			int[] caminito;
			camino=A_start(grafo, args[2], args[3]);
       
			Ruta(grafo, camino);
		}catch(NoSuchElementException  err){
			System.out.println("Noexiste una ruta");
		}
		}
		if(tipo.equalsIgnoreCase("d")) {
			try {
			GrafoDirigido otro = new GrafoDirigido();
			otro.cargarGrafoMetro(args[0]);
    
			GrafoDirigido grafo = otro.cargarGrafoInducido(args[1]);
			LinkedList<Integer> camino;
			int[] caminito;
			camino=A_startD(grafo, args[2], args[3]);
			System.out.println(camino);
			RutaD(grafo, camino);
			}catch(NoSuchElementException  err){
				System.out.println("Noexiste una ruta");
			}
			
		}
  
	}

}






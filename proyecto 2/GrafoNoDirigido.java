import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GrafoNoDirigido{
	/*Atributos de la clase GrafoNoDirigido
	 * Grafo: Lista de lista de vertices que es la representacion de la lista de adyacencia del grafo
	 * Arista: lista de arista que es la lista de todas las arista que posee el grafo
	 */
	LinkedList<LinkedList<Vertice>> Mapa;
	LinkedList<Arista> Tramos;
	
	public GrafoNoDirigido() {
		this.Mapa=new LinkedList<LinkedList<Vertice>>();
		this.Tramos=new LinkedList<Arista>();
	}

	/*
	 * Si no existe un arco del tipo tipo entre u y v, crea una nueva aristay la agrega en el grafo. Retorna true
	 *en caso en que la inserci@n se lleva a cabo, false en contrario.
	
	 */
	public boolean agregarArista(int u, int v, String Linea, int Tiempo) {
	
		if(!estaArista(u,v,Linea,Tiempo)) {
			if(estaVertice(u)&& estaVertice(v)) {
				Vertice nuevov1 = obtenerVertice(u);
            	Vertice nuevov2 = obtenerVertice(v);
				Arista nuevaArista=new Arista(nuevov1,nuevov2,Linea, Tiempo);
			Tramos.add(nuevaArista);
			for(int i=0;i<Mapa.size();i++) {
				if(Mapa.get(i).get(0)==nuevov1) {
					Mapa.get(i).add(nuevov2);
				}
				if(Mapa.get(i).get(0)==nuevov2) {
					Mapa.get(i).add(nuevov1);
				}
			}
			return true;
		}
		}
		return false;
	}
		 public Vertice obtenerVertice(String Nombre){

		        if(estaVertice(Nombre)){
		            for(LinkedList<Vertice> v : Mapa){
		                if(v.get(0).ObtenerNombre().equals(Nombre) ){
		                    return v.get(0);
		                }
		            }
		        }else{
		            System.out.println("No existe un vertice en el grafo llamado " + Nombre);
		        }
		        return null;

		    }
	/*Elimina la arista que es pasada como parametro. Se retorna true en caso que se haya
	eliminado la arista del grafo y false en caso de que no exista una arista con ese identificador en el
	grafo
	*/
	public boolean eliminarArista(Arista a) {
		boolean Existe=false;
		Vertice v1=a.ObtenerPrimeraParada();
		Vertice v2=a.ObtenerSegundaParada();
		LinkedList<Arista> listadeeliminacion=new LinkedList<Arista>();
		for(Arista e:Tramos) {
			if(e.ObtenerPrimeraParada().ObtenerId()==a.ObtenerPrimeraParada().ObtenerId() && e.ObtenerSegundaParada().ObtenerId()==a.ObtenerSegundaParada().ObtenerId()&& e.ObtenerLinea().equalsIgnoreCase(a.ObtenerLinea()) && a.ObtenerTiempo()==e.ObtenerTiempo() ) {
				Existe=true;
				listadeeliminacion.add(e);
			}
		}
		for(Arista e:listadeeliminacion) {
			Tramos.remove(e);
		}
		
		if(Existe==true) {
			for(int i=0;i<Mapa.size();i++) {
				if(Mapa.get(i).get(0).ObtenerId()==v1.ObtenerId()) {
					for(int j=0;j<Mapa.get(i).size();j++) {
						if(Mapa.get(i).get(j).ObtenerId()==v2.ObtenerId()) {
							Mapa.get(i).remove(j);
						}
					}
				}
				if(Mapa.get(i).get(0).ObtenerId()==v2.ObtenerId()) {
					for(int j=0;j<Mapa.get(i).size();j++) {
						if(Mapa.get(i).get(j).ObtenerId()==v1.ObtenerId()) {
							Mapa.get(i).remove(j);
						}
					}
				}
			}
		}
		return Existe;
	}
	/*Devuelve la arista que es pasada como parametro. En caso de que no exista ninguna arista , se lanza la excepci@n NoSuchElementException.
	*/
	public Arista obtenerArista(Arista a) {
		for(Arista e:Tramos) {
			if(e.ObtenerPrimeraParada().ObtenerId()==a.ObtenerPrimeraParada().ObtenerId()&& e.ObtenerSegundaParada().ObtenerId()==a.ObtenerSegundaParada().ObtenerId()&& e.ObtenerLinea()==a.ObtenerLinea()&& e.ObtenerTiempo()==a.ObtenerTiempo()) {
				return e;
			}
		}
		throw new NoSuchElementException("Esta Arsita no existe en el grafo");
	}
	/*
	 * Determina si una Arista pertenece a un grafo. La entrada son los identificadores de los v@rtices que son
	*los extremos del lado y el tipo de ese Arista.
	 */
	public boolean estaArista(int u, int v, String Linea, int Tiempo) {
		 if(estaVertice(u) && estaVertice(v)) {
			 for(Arista e:Tramos) {
				 if(e.ObtenerPrimeraParada().ObtenerId()==u && e.ObtenerSegundaParada().ObtenerId()==v && e.ObtenerTiempo()==Tiempo && e.ObtenerLinea().equalsIgnoreCase(Linea)) {
					 return true;
				 }
				 
			 }
		 }
		 else {
			 return false;
		 }
		 return false;
	        
	    }
	
	
	public boolean estaArista(Arista a) {
		for(Arista e:Tramos) {
			if(e.equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * determina se el vertice pertenece al grafo o no en caso de pertenercer retorna True en otro caso false.
	 */
	  public boolean estaVertice(String nombre){
	        for(LinkedList<Vertice> v : Mapa){
	            if(v.get(0).ObtenerNombre().equals(nombre) ){
	                return true;
	            }
	        }
	        System.out.println("No existe un vertice en el grafo llamado " +nombre);
	        return false;
	    }
	
	//#############################################################################
	public boolean cargarGrafo(String Archivo) throws IOException {
		try {
		BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
		int n,m,Id,Tiempo;
		boolean Exito=false;
		int u;
		int v;
		double Latitud,Longitud;
		String Nombre,Linea;
		String[] lineasinespacio;
		String linea=Lector.readLine(); //linea 1
		linea=Lector.readLine(); //linea2
		n=Integer.parseInt(linea);
		linea=Lector.readLine();//linea3
		m=Integer.parseInt(linea);
		
		for(int i=0;i<n;i++) {
			linea=Lector.readLine();//linea 4,5....n
			lineasinespacio=linea.split(" ");
			
			if(lineasinespacio.length==5) {	
				Id=Integer.parseInt(lineasinespacio[0]);
				Nombre=lineasinespacio[1];
				Longitud=Double.parseDouble(lineasinespacio[2]);
				Latitud=Double.parseDouble(lineasinespacio[3]);
				Tiempo=Integer.parseInt(lineasinespacio[4]);
				agregarVertice(Id,Nombre,Longitud,Latitud,Tiempo);
			}
			else {
				throw new IllegalArgumentException(" No es una linea de vertices valida");
			}
		}
		for(int j=0;j<m;j++) {
			linea=Lector.readLine(); //empieza las lineas de las aristas
			lineasinespacio=linea.split(" ");
			if(lineasinespacio.length==4) {	
				u=Integer.parseInt(lineasinespacio[0]);
				v=Integer.parseInt(lineasinespacio[1]);
				Linea=lineasinespacio[2];
				Tiempo=Integer.parseInt(lineasinespacio[3]);
				agregarArista(u,v,Linea,Tiempo);
				
			}
			else {
				throw new IllegalArgumentException("No es una linea de Aristas valida"); 
			}
			
		}
		return true;
		}catch(IOException e) {
					return false;
			}
		
	
		
	}

	
	public int numeroDeParadas() {
		
		return Mapa.size();
	}


	public int numeroDeTramos() {
	
		return Tramos.size();
	}

	public boolean agregarVertice(Vertice v) {
		for(LinkedList<Vertice> e:Mapa) {
			if(e.get(0).ObtenerId()==v.ObtenerId()) {
				return false;
			}
		
		}
		LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
		nuevovertice.add(v);
		Mapa.add(nuevovertice);
		return true;
	}

	public boolean agregarVertice(int id, String nombre, double Longitud , double Latitud, int Tiempo) {
		Vertice v=new Vertice(id,nombre,Longitud,Latitud,Tiempo);
		for(LinkedList<Vertice> e:Mapa) {
			if(e.get(0).ObtenerId()==v.ObtenerId()) {
				return false;
			}
		
		}
		LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
		nuevovertice.add(v);
		Mapa.add(nuevovertice);
		return true;
	}

	

	public Vertice obtenerVertice(int id) {
		for(LinkedList<Vertice> e:Mapa) {
			if(e.get(0).ObtenerId()==id) {
				return e.get(0);
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	public boolean estaVertice(int id) {
		for(LinkedList<Vertice> e:Mapa) {
			if(e.get(0).ObtenerId()==id) {
				return true;
			}
		}
		return false;
	}

	public boolean eliminarVertice(int id) {
		boolean existe=estaVertice(id);
		if(existe==true) {
			for(int i=0;i<Mapa.size();i++) {
				if(Mapa.get(i).get(0).ObtenerId()==id) {
					Mapa.remove(i);
				}
			}
			for(LinkedList<Vertice> e:Mapa) {
				for(int j=1;j<e.size();j++) {
					if(e.get(j).ObtenerId()==id) {
						e.remove(j);
					}
				}
			}
			for(int i=0;i<Tramos.size();i++) {
				if(Tramos.get(i).ObtenerPrimeraParada().ObtenerId()==id || Tramos.get(i).ObtenerSegundaParada().ObtenerId()==id) {
					Tramos.remove(i);
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	
	public LinkedList<Integer> vertices() {
		LinkedList<Integer> listadevertices=new LinkedList<Integer>();
		for(LinkedList<Vertice> e:Mapa) {
			listadevertices.add(e.get(0).ObtenerId());
		}
		return listadevertices;
	}

	
	public LinkedList<Arista> lados() {
		LinkedList<Arista> Tramos=new LinkedList<Arista>();
		for (Arista e:Tramos) {
			Tramos.add(e);
		}
		return Tramos;
	}


	public LinkedList<Vertice> adyacentes(int id) {
		LinkedList<Vertice> adyaceentes=new LinkedList<Vertice>();
		Vertice v=null;
		for(LinkedList<Vertice> e:Mapa) {
			if(e.get(0).ObtenerId()==id) {
				v=e.get(0);
				break;
			}
		}
		for(int i=0;i<Mapa.size();i++) {
		
			if(Mapa.get(i).get(0).ObtenerId()==v.ObtenerId()) {
				for(int j=1;j<Mapa.get(i).size();j++) {
					adyaceentes.add(Mapa.get(i).get(j));
					
				}
				return adyaceentes;
			}
		}
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}


	
	
	public String toString() {
		   String info = "";

	        for(int i=0;i<Mapa.size();i++){
	            	info = info+Mapa.get(i).get(0).ObtenerId() + " -----> ";

	                for(int j=1;j<Mapa.get(i).size();j++){
	                    info=info+Mapa.get(i).get(j).ObtenerId() + " ";
	                }
	                info=info+"\n";
	               
	        }
	        info=info+"\n";
	        
	       
	        	info=info+"Arcos:\n";
	        	info=info+"\n";
	                info += "Info de las Arista: " +"\n";
	                for(int j=0;j<Tramos.size();j++) {
	                info+="Arista "+ j +":"+"\n"+Tramos.get(j).toString()+"\n";
	                info=info+"\n";
	                }
	            
	        
	        return info;
	}
}


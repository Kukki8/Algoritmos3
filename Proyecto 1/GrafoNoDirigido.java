package proyecto1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GrafoNoDirigido implements Grafo {
	
	LinkedList<LinkedList<V�rtice>> Grafo;
	LinkedList<Lado> Aristas;
	
	public GrafoNoDirigido() {
		Grafo=new LinkedList<LinkedList<V�rtice>>();
		Aristas=new LinkedList<Lado>();
	}
	
	public Grafo crearGrafoNoDirigido(){
		return new GrafoNoDirigido();
	}
	// Agrega un Arco a la lista de arcos si el arco no se encuentra y devuelve true,
	//En otro caso devuelve false.
	public boolean agregarArista(Grafo g,Arista a) {
		for(Lado e:Aristas) {
			if(e.getTipo()==a.getTipo()) {
				return false;
			}
		}	
		Aristas.add(a);
		boolean Existe1=false;
		boolean Existe2=false;
		V�rtice vertice1=a.getExtremo1();
		V�rtice vertice2=a.getExtremo2();
		for(int i=0;i<Grafo.size();i++) {
			if(Grafo.get(i).get(0)==vertice1) {
				Grafo.get(i).add(vertice2);
				Existe1=true;
			}
		
		}
		for(int j=0;j<Grafo.size();j++) {
			if(Grafo.get(j).get(0)==vertice2) {
				Grafo.get(j).add(vertice1);
				Existe2=true;
			}
		}
		if(Existe1==false) {
			LinkedList<V�rtice> nuevovertice=new LinkedList<V�rtice>();
			nuevovertice.add(vertice1);
			Grafo.add(nuevovertice);
			Grafo.get(Grafo.size()-1).add(vertice2);
			
		}
		if(Existe2==false) {
			LinkedList<V�rtice> nuevovertice=new LinkedList<V�rtice>();
			nuevovertice.add(vertice2);
			Grafo.add(nuevovertice);
			Grafo.get(Grafo.size()-1).add(vertice1);	
		}
		return true;
	}
	public boolean agregarArista(String u, String v, int tipo, double peso) {
		
	}
	
	
	
	//#############################################################################
	public boolean cargarGrafo(String Archivo) throws IOException {
		BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
		int n,m,id;
		double x,y,peso;
		String nombre;
		String[] lineasinespacio;
		String linea=Lector.readLine(); //linea 1
		linea=Lector.readLine(); //linea2
		n=Integer.parseInt(linea);
		linea=Lector.readLine();//linea3
		m=Integer.parseInt(linea);
		
		for(int i=0;i<n;i++) {
			linea=Lector.readLine();//linea 4,5....
			lineasinespacio=linea.split(" ");
			id=Integer.parseInt(lineasinespacio[0]);
			nombre=lineasinespacio[1];
			x=Double.parseDouble(lineasinespacio[2]);
			y=Double.parseDouble(lineasinespacio[3]);
			peso=Double.parseDouble(lineasinespacio[4]);
			agregarVertice(id,nombre,x,y,peso);
			
		}
		for(int j=0;j<m;j++) {
			linea=Lector.readLine(); //empieza las lineas de lso arcos
			lineasinespacio=linea.split(" ");
			
		}
		return false;
	
		
	}

	@Override
	public int numeroDeVertices() {
		
		return Grafo.size();
	}

	@Override
	public int numeroDeLados() {
	
		return Aristas.size();
	}

	@Override
	public boolean agregarVertice(V�rtice v) {
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==v.getId()) {
				return false;
			}
		
		}
		LinkedList<V�rtice> nuevovertice=new LinkedList<V�rtice>();
		nuevovertice.add(v);
		Grafo.add(nuevovertice);
		return true;
	}

	@Override
	public boolean agregarVertice(int id, String nombre, double x, double y, double p) {
		V�rtice vertice=new V�rtice(id,nombre,x,y,p);
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==vertice.getId()) {
				return false;
			}
		
		}
		LinkedList<V�rtice> nuevovertice=new LinkedList<V�rtice>();
		nuevovertice.add(vertice);
		Grafo.add(nuevovertice);
		return true;
	}

	

	@Override
	public V�rtice obtenerVertice(int id) {
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.get(0);
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public boolean estaVertice(int id) {
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean eliminarVertice(int id) {
		boolean existe=estaVertice(id);
		if(existe==true) {
			for(int i=0;i<Grafo.size();i++) {
				if(Grafo.get(i).get(0).getId()==id) {
					Grafo.remove(i);
				}
			}
			for(LinkedList<V�rtice> e:Grafo) {
				for(int j=0;j<e.size();j++) {
					if(e.get(j).getId()==id) {
						e.remove(j);
					}
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public LinkedList<Integer> vertices() {
		LinkedList<Integer> listadevertices=new LinkedList<Integer>();
		for(LinkedList<V�rtice> e:Grafo) {
			listadevertices.add(e.get(0).getId());
		}
		return listadevertices;
	}

	@Override
	public LinkedList<Lado> lados() {
		return Aristas;
	}

	@Override
	public int grado(int id) {
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.size()-1;
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public LinkedList<Integer> adyacentes(int id) {
		LinkedList<Integer> adyaceentes=new LinkedList<Integer>();
		for(LinkedList<V�rtice> e:Grafo) {
			if(e.get(0).getId()==id) {
				for(int i=1;i<e.size();i++) {
					adyaceentes.add(e.get(i).getId());
					return adyaceentes;
				}
			}
		}
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public LinkedList<Lado> incidentes(int id) {
		LinkedList<Lado> ladosincidentes=new LinkedList<Lado>();
		V�rtice vertice1;
		boolean Existe=estaVertice(id);
		if(Existe==true) {
			for(LinkedList<V�rtice> j:Grafo) {
				if(j.get(0).getId()==id) {
					vertice1=j.get(0);
					for(Lado e:Aristas) {
						if(e.incide(vertice1)) {
							ladosincidentes.add(e);
			
						}
					}
				
		
				}
			}
		}
		else {
			throw new NoSuchElementException("Este Vertice no existe en el grafo");
		}
		return ladosincidentes;
		
	
	}

	@Override
	public Clonacion clone() {
		Clonacion grafoclonado=new Clonacion(Grafo,Aristas);
		return grafoclonado;
	}
	
}

class Clonacion{
	LinkedList<LinkedList<V�rtice>> grafo;
	LinkedList<Lado> Aristas;
	public Clonacion(LinkedList<LinkedList<V�rtice>> Grafo, LinkedList<Lado> Aristas) {
		this.grafo=Grafo;
		this.Aristas=Aristas;
	}
}
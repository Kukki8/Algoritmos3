package proyecto1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GrafoNoDirigido implements Grafo {
	
	LinkedList<LinkedList<Vértice>> Grafo;
	LinkedList<Lado> Aristas;
	
	public GrafoNoDirigido() {
		Grafo=new LinkedList<LinkedList<Vértice>>();
		Aristas=new LinkedList<Lado>();
	}
	
	public Grafo crearGrafoNoDirigido(){
		return new GrafoNoDirigido();
	}
	// Agrega un Arco a la lista de arcos si el arco no se encuentra y devuelve true,
	//En otro caso devuelve false.
	public boolean agregarArista(Grafo g,Arista a) {
		for(Lado e:Aristas) {
			if(e.vi.getId()==a.getExtremo1().getId()&&e.vf.getId()==a.getExtremo2().getId( )) {
				return false;
			}
		}	
		Aristas.add(a);
		boolean Existe1=false;
		boolean Existe2=false;
		Vértice vertice1=a.getExtremo1();
		Vértice vertice2=a.getExtremo2();
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
			LinkedList<Vértice> nuevovertice=new LinkedList<Vértice>();
			nuevovertice.add(vertice1);
			Grafo.add(nuevovertice);
			Grafo.get(Grafo.size()-1).add(vertice2);
			
		}
		if(Existe2==false) {
			LinkedList<Vértice> nuevovertice=new LinkedList<Vértice>();
			nuevovertice.add(vertice2);
			Grafo.add(nuevovertice);
			Grafo.get(Grafo.size()-1).add(vertice1);	
		}
		return true;
	}
	public boolean agregarArista(String u, String v, int tipo, double peso) {
		int id1,id2;
		id1= Integer.parseInt(u);
		id2=Integer.parseInt(v);
		Vértice vertice1 = null,vertice2 = null;
		for(Lado e:Aristas) {
			if(e.vi.getId()==id1&&e.vf.getId()==id2) {
				return false;
			}
		
		}
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==id1) {
				vertice1=e.get(0);
			}
			if(e.get(0).getId()==id2) {
				vertice2=e.get(0);
			}
		}
		Arista nuevaArista=new Arista(peso,tipo,vertice1,vertice2);
		Aristas.add(nuevaArista);
		for(int i=0;i<Grafo.size();i++) {
			if(Grafo.get(i).get(0)==vertice1) {
				Grafo.get(i).add(vertice2);
			}
			if(Grafo.get(i).get(0)==vertice2) {
				Grafo.get(i).add(vertice1);
			}
		}
		return true;
	}
	public boolean eliminarArista(Arista a) {
		boolean Existe=false;
		Vértice v1=a.getExtremo1();
		Vértice v2=a.getExtremo2();
		for(int i=0;i<Aristas.size();i++) {
			if(Aristas.get(i)==a) {
				Aristas.remove(i);
				Existe=true;
			}
		}
		if(Existe==true) {
			for(int i=0;i<Grafo.size();i++) {
				if(Grafo.get(i).get(0).getId()==v1.getId()) {
					for(int j=0;j<Grafo.get(i).size();j++) {
						if(Grafo.get(i).get(j).getId()==v2.getId()) {
							Grafo.get(i).remove(j);
						}
					}
				}
				if(Grafo.get(i).get(0).getId()==v2.getId()) {
					for(int j=0;j<Grafo.get(i).size();j++) {
						if(Grafo.get(i).get(j).getId()==v1.getId()) {
							Grafo.get(i).remove(j);
						}
					}
				}
			}
		}
		return Existe;
	}
	public Lado obtenerArista(Arista a) {
		for(Lado e:Aristas) {
			if(e==a) {
				return e;
			}
		}
		throw new NoSuchElementException("Esta Arsita no existe en el grafo");
	}
	public boolean estaArista(String u, String v, int tipo) {
		int id1,id2;
		id1=Integer.parseInt(u);
		id2=Integer.parseInt(v);
		for(Lado e:Aristas){
			if(e.vi.getId()==id1 && e.vf.getId()==id2) {
				return true;
			}
			else {
			
			}
		}
		return false;	
	}
	
	
	
	//#############################################################################
	public boolean cargarGrafo(String Archivo) throws IOException {
		try {
		BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
		int n,m,id,tipo;
		boolean Exito=false;
		String u,v;
		double x,y,peso;
		String nombre;
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
				id=Integer.parseInt(lineasinespacio[0]);
				nombre=lineasinespacio[1];
				x=Double.parseDouble(lineasinespacio[2]);
				y=Double.parseDouble(lineasinespacio[3]);
				peso=Double.parseDouble(lineasinespacio[4]);
				agregarVertice(id,nombre,x,y,peso);
			}
			else {
				throw new IllegalArgumentException(" No es una linea de vertices valida");
			}
		}
		for(int j=0;j<m;j++) {
			linea=Lector.readLine(); //empieza las lineas de las aristas
			lineasinespacio=linea.split(" ");
			if(lineasinespacio.length==4) {	
				u=lineasinespacio[0];
				v=lineasinespacio[1];
				tipo=Integer.parseInt(lineasinespacio[2]);
				peso=Double.parseDouble(lineasinespacio[3]);
				agregarArista(u,v,tipo,peso);
				
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

	@Override
	public int numeroDeVertices() {
		
		return Grafo.size();
	}

	@Override
	public int numeroDeLados() {
	
		return Aristas.size();
	}

	@Override
	public boolean agregarVertice(Vértice v) {
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==v.getId()) {
				return false;
			}
		
		}
		LinkedList<Vértice> nuevovertice=new LinkedList<Vértice>();
		nuevovertice.add(v);
		Grafo.add(nuevovertice);
		return true;
	}

	@Override
	public boolean agregarVertice(int id, String nombre, double x, double y, double p) {
		Vértice vertice=new Vértice(id,nombre,x,y,p);
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==vertice.getId()) {
				return false;
			}
		
		}
		LinkedList<Vértice> nuevovertice=new LinkedList<Vértice>();
		nuevovertice.add(vertice);
		Grafo.add(nuevovertice);
		return true;
	}

	

	@Override
	public Vértice obtenerVertice(int id) {
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.get(0);
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public boolean estaVertice(int id) {
		for(LinkedList<Vértice> e:Grafo) {
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
			for(LinkedList<Vértice> e:Grafo) {
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
		for(LinkedList<Vértice> e:Grafo) {
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
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.size()-1;
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public LinkedList<Integer> adyacentes(int id) {
		LinkedList<Integer> adyaceentes=new LinkedList<Integer>();
		for(LinkedList<Vértice> e:Grafo) {
			if(e.get(0).getId()==id) {
				for(int i=0;i<e.size();i++) {
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
		Vértice vertice1;
		boolean Existe=estaVertice(id);
		if(Existe==true) {
			for(LinkedList<Vértice> j:Grafo) {
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
	LinkedList<LinkedList<Vértice>> grafo;
	LinkedList<Lado> Aristas;
	public Clonacion(LinkedList<LinkedList<Vértice>> Grafo, LinkedList<Lado> Aristas) {
		this.grafo=Grafo;
		this.Aristas=Aristas;
	}
}
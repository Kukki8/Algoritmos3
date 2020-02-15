import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GrafoNoDirigido implements Grafo {
	
	LinkedList<LinkedList<Vertice>> Grafo;
	LinkedList<Arista> Aristas;
	
	public void CrearGrafoNoDirigido() {
		Grafo=new LinkedList<LinkedList<Vertice>>();
		Aristas=new LinkedList<Arista>();
	}
	
	public Grafo crearGrafoNoDirigido(){
		return new GrafoNoDirigido();
	}
	// Agrega un Arco a la lista de arcos si el arco no se encuentra y devuelve true,
	//En otro caso devuelve false.
	public boolean agregarArista(Arista a) {
		for(Arista e:Aristas) {
			if(e.getExtremo1().getId()==a.getExtremo1().getId()&&e.getExtremo2().getId()==a.getExtremo2().getId( )) {
				return false;
			}
		}	
		Aristas.add(a);
		boolean Existe1=false;
		boolean Existe2=false;
		Vertice vertice1=a.getExtremo1();
		Vertice vertice2=a.getExtremo2();
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
			LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
			nuevovertice.add(vertice1);
			Grafo.add(nuevovertice);
			Grafo.get(Grafo.size()-1).add(vertice2);
			
		}
		if(Existe2==false) {
			LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
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
		Vertice vertice1 = null,vertice2 = null;
		for(Lado e:Aristas) {
			if(e.vi.getId()==id1&&e.vf.getId()==id2) {
				return false;
			}
		
		}
		for(LinkedList<Vertice> e:Grafo) {
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
		Vertice v1=a.getExtremo1();
		Vertice v2=a.getExtremo2();
		for(int i=0;i<Aristas.size();i++) {
			if(Aristas.get(i).getExtremo1()==a.getExtremo1() && Aristas.get(i).getExtremo2()==a.getExtremo2()) {
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
		for(Arista e:Aristas){
			if(e.getExtremo1().getId()==id1 && e.getExtremo2().getId()==id2) {
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
	public boolean agregarVertice(Vertice v) {
		for(LinkedList<Vertice> e:Grafo) {
			if(e.get(0).getId()==v.getId()) {
				return false;
			}
		
		}
		LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
		nuevovertice.add(v);
		Grafo.add(nuevovertice);
		return true;
	}

	@Override
	public boolean agregarVertice(int id, String nombre, double x, double y, double p) {
		Vertice v=new Vertice(id,nombre,x,y,p);
		for(LinkedList<Vertice> e:Grafo) {
			if(e.get(0).getId()==v.getId()) {
				return false;
			}
		
		}
		LinkedList<Vertice> nuevovertice=new LinkedList<Vertice>();
		nuevovertice.add(v);
		Grafo.add(nuevovertice);
		return true;
	}

	

	@Override
	public Vertice obtenerVertice(int id) {
		for(LinkedList<Vertice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.get(0);
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public boolean estaVertice(int id) {
		for(LinkedList<Vertice> e:Grafo) {
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
			for(LinkedList<Vertice> e:Grafo) {
				for(int j=1;j<e.size();j++) {
					if(e.get(j).getId()==id) {
						e.remove(j);
					}
				}
			}
			for(int i=0;i<Aristas.size();i++) {
				if(Aristas.get(i).getExtremo1().getId()==id || Aristas.get(i).getExtremo2().getId()==id) {
					Aristas.remove(i);
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
		for(LinkedList<Vertice> e:Grafo) {
			listadevertices.add(e.get(0).getId());
		}
		return listadevertices;
	}

	@Override
	public LinkedList<Lado> lados() {
		LinkedList<Lado> Lados=new LinkedList<Lado>();
		for (Arista e:Aristas) {
			Lados.add(e);
		}
		return Lados;
	}

	@Override
	public int grado(int id) {
		for(LinkedList<Vertice> e:Grafo) {
			if(e.get(0).getId()==id) {
				return e.size()-1;
			}
		}
		
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public LinkedList<Vertice> adyacentes(int id) {

		if(estaVertice(id)){
			LinkedList<Vertice> adyacentes = new LinkedList<Vertice>();
			for(Arista A: Aristas){
				if(A.getExtremo1().getId() == id){
					adyacentes.add(A.getExtremo1());

				}else if(A.getExtremo2().getId() == id){
					adyacentes.add(A.getExtremo2());
				}
			}
			return adyacentes;
		}
		throw new NoSuchElementException("Este Vertice no existe en el grafo");
	}

	@Override
	public LinkedList<Lado> incidentes(int id) {
		LinkedList<Lado> ladosincidentes=new LinkedList<Lado>();
		Vertice vertice1=null;
		boolean Existe=estaVertice(id);
		if(Existe==true) {
			for(LinkedList<Vertice> j:Grafo) {
				if(j.get(0).getId()==id) {
					vertice1=j.get(0);
					break;
				}
			}
			for(Arista e:Aristas) {
				if(e.incide(vertice1)) {
					ladosincidentes.add(e);
			
				}
			}
	
		}
		else {
			throw new NoSuchElementException("Este Vertice no existe en el grafo");
		}
		return ladosincidentes;
		

	}

	@Override
	public Grafo clone() {
		Grafo grafoclonado=new Clonacion(Grafo,Aristas);
		return grafoclonado;
	}
	public String toString() {
		String vertices=" ";
		String aristas=" ";
		String grafo;
		int v1,v2;
		for(LinkedList<Vertice> e:Grafo) {
			vertices=vertices+","+Integer.toString(e.get(0).getId());
		}
		for(Arista e:Aristas) {
			v1=e.getExtremo1().getId();
			v2=e.getExtremo2().getId();
			aristas=aristas+Integer.toString(v1)+"-"+Integer.toString(v2)+"\n";
		}
		grafo="los vertices son: "+vertices+"\n"+
			"las aristas son: "+ aristas;
		return grafo;
	}
}

class Clonacion{
	LinkedList<LinkedList<Vertice>> grafo;
	LinkedList<Arista> Aristas;
	public Clonacion(LinkedList<LinkedList<Vertice>> Grafo, LinkedList<Arista> Aristas) {
		this.grafo=Grafo;
		this.Aristas=Aristas;
	}
	public LinkedList<LinkedList<Vertice>> Damegrafo(){
		return grafo;
	}
	public LinkedList<Arista> DameAristas(){
		return Aristas;
	}
	
	
}
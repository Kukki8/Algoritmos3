package proyecto1;

import java.util.*;

public interface Grafo {
	//carga el grafo en la clase que impleta Grafo
	public boolean cargarGrafo(String archivo);
	
	public int numeroDeVertices();
	
	public int numeroDeLados();
	
	public boolean agregarVertice(V�rtice v);
	
	public boolean agregarVertice(int id, String nombre,double x, double y,double p);
	
	public V�rtice obtenerVertice(int id);
	
	public boolean estaVertice(int id);
	
	public boolean eliminarVertice(int id);
	
	public LinkedList<V�rtice> vertices();
	
	public LinkedList<Lado> lados();
	
	public int grado(int id);
	
	public LinkedList<V�rtice> adyacentes(int id);
	
	public LinkedList<Lado> incidentes(int id);
	
	public Grafo clone(Grafo grafo);
	
	public String toString();
}

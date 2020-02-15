import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public interface Grafo {
	//carga el grafo en la clase que impleta Grafo
	public boolean cargarGrafo(String Archivo) throws IOException;
	
	public int numeroDeVertices();
	
	public int numeroDeLados();
	
	public boolean agregarVertice(Vertice v);
	
	public boolean agregarVertice(int id, String nombre,double x, double y,double p);
	
	public Vertice obtenerVertice(int id);
	
	public boolean estaVertice(int id);
	
	public boolean eliminarVertice(int id);
	
	public LinkedList<Integer> vertices();
	
	public LinkedList<Lado> lados();
	
	public int grado(int id);
	
	public LinkedList<Vertice> adyacentes(int id);
	
	public LinkedList<Lado> incidentes(int id);
	
	public Grafo clone();
	
	public String toString();
}

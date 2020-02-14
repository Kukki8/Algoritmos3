public class Vertice {
	 /*Atributos de la clase Vertice:
	    * id: Identificador del vertice
	    * nombre: nombre del vertice
	    * coordenadas: coordenadas del  vertice
	    * peso: peso de el Vertice
	    * ListaAdyacencia: LinkedList con las Aristas que inciden en el vertice (utilizado en Grafos no Dirigidos)
	    * ListaSucesores: LinkedList con los Arco que inciden en el vertice (utilizado en Grafos Dirigidos)
	    */
	    private int id;
	    private String nombre;
	    private double coordenadax;
	    private double coordenaday;
	    private double peso;
	                  //Si el vertice esta en un grafo no dirigido
	                    //Si el vertice esta en un grafo dirigido
	    
	    //constructor de la clase vertice//
	    public void CrearVertice(int identificador, String nombre, double x, double y,double peso) {
	    	this.id=identificador;
	    	this.nombre=nombre;
	    	this.coordenadax=x;
	    	this.coordenaday=y;
	    	this.peso=peso;

	    	
	    }
	    // retorna el Vertice creado
	  //  public static Vértice CrearVértice(int identificador, String nombre, double x, double y,double peso ) {
	    //	return new Vértice(identificador, nombre, x ,y , peso);
	
	    
	    // retorna el peso del vertice
	    public double getPeso() {
	    	return peso;
	    }
	    // retorna el identificador del vertice
	    public int getId() {
	    	return id;
	    }
	    //retorna el nombre del vertice
	    public String getNombre() {
	    	return this.nombre;
	    }
	    //retorna la coordenada "x" del vertice
	    public double getX() {
	    	return this.coordenadax;
	    }
	    //retorna la coordenada "y" del vertice
	    public double getY() {
	    	return this.coordenaday;
	    	
	    }
	    // retorna la informacion del vertice
	    public String toString() {
	    	return "Vertice: "+ id +", Nombre: "+ nombre +", Peso: "+ peso +
	    			", Coordenadas: "+ "x: "+ this.coordenadax+ "y: "+this.coordenaday;
	    }
}




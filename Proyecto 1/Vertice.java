public class Vertice {
	 /*Atributos de la clase Vertice:
	    * id: Identificador del vertice
	    * nombre: nombre del vertice
	    * coordenadas: coordenadas del  vertice
	    * peso: peso de el Vertice
	    */
	    private int id;
	    private String nombre;
	    private double coordenadax;
	    private double coordenaday;
	    private double peso;
	    
	    //constructor de la clase vertice//
	    public Vertice(int identificador, String nombre, double x, double y,double peso) {
	    	this.id=identificador;
	    	this.nombre=nombre;
	    	this.coordenadax=x;
	    	this.coordenaday=y;
	    	this.peso=peso;

	    	
	    }
	    /*
	     Crea un nuevo v@rtice con un identificador id, llamado nombre, con coordenadas (x,y) y un peso p.
	     retorna el Vertice creado
		*/
	  public static Vertice CrearVertice(int identificador, String nombre, double x, double y,double peso ) {
	   	return new Vertice(identificador, nombre, x ,y , peso);
	  }
	
	    /*Obtiene el peso del v@rtice v.
	     retorna el peso del vertice
	    */
	  public double obtenerPeso() {
	    	return peso;
	    }
	  
	    /*Obtiene el identificador del v@rtice v.
 		retorna el identificador del vertice
	  	*/
	    public int obtenerId() {
	    	return id;
	    }
	    
	    /*Obtiene el dato contenido en el v@rtice v
	     * retorna el nombre del vertice
	     */
	    public String obtenerNombre() {
	    	return this.nombre;
	    }
	    /*Obtiene la coordenada x del v@rtice v
	     * retorna la coordenada "x" del vertice.
	     */
	    
	    public double obtenerX() {
	    	return this.coordenadax;
	    }
	    /*Obtiene la coordenada y del v@rtice v
	     * retorna la coordenada "y" del vertice.
	     */
	    public double obtenerY() {
	    	return this.coordenaday;
	    	
	    }
	    /*Proporciona una representaci@n del v@rtice v como una cadena de caracteres.

	     *retorna la informacion del vertice. 
	     */
	    public String toString() {
	    	return "Vertice: "+ id +"\n"+"Nombre: "+ nombre +"\n"+"Peso: "+ peso +"\n"+
	    			"Coordenadas: "+"\n"+"x: "+ this.coordenadax+ "\n"+"y: "+this.coordenaday+"\n";
	    }
}




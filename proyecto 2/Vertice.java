
public class Vertice {
	/* Atributus de la clase Vertice:
	 * Id: Identificador del Vertice(parada)
	 * Nombre: Nombre del vertice(Parada)
	 * Longitud: Posicion del Vertice (Longitud de la parada)
	 * Latitud: Posicion del vertice (Latitud de la parada)
	 * Tiempo: Tiempo que se espera al intentar abordar en el vertice (Parada)
	 */
	private int Id; 
	private String Nombre;
	private double Longitud;
	private double Latitud;
	private int Tiempo;
	/**
	 * Constructor de la clase Vertice.
	 * @param Id
	 * @param Nombre
	 * @param Longitud
	 * @param Latitud
	 * @param Tiempo
	 */
	public Vertice(int Id, String Nombre, double Longitud, double Latitud, int Tiempo) {
		this.Id=Id;
		this.Nombre=Nombre;
		this.Longitud=Longitud;
		this.Latitud=Latitud;
		this.Tiempo=Tiempo;
	}
	/**
	 * Metodo Getter que retorna el Id del vertice
	 * @return Id: retorna el Id del vertice
	 */
	public int ObtenerId() {
		return this.Id;
	}
	/**
	 * Metodo Getter que retorna el Nombre del vertice
	 * @return Nombre: retorna el Nombre del vertice
	 */
	public String ObtenerNombre() {
		return this.Nombre;
	}
	/**
	 * Metodo Getter que retorna la Latitud del Vertice
	 * @return Latitud: Retorna la Latitud del Vertice(Parada)
	 */
	public double ObtnerLatitud() {
		return this.Latitud;
	}
	/**
	 * Metodo Geter que retorna la Longitd del Vertice
	 * @return Longitud: Retorna la Longitud del vertice(Parada)
	 */
	public double ObtnerLongitud() {
		return this.Longitud;
	}
	/**
	 * Metodo Getter que retorna el Tiempo del Vertice.
	 * @return Tiempo: Retorna el Tiempo del Vertice(Tiempo estimado en abordar la Parada)
	 */
	public int ObtenerTiempo() {
		return this.Tiempo;
	}
}

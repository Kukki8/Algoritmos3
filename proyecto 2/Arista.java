
public class Arista {
	/*Atributus de la Clase Arista
	 * PrimeraParada: Numero (Id) de la Primera Parada.
	 * SegundaParada: Nimero(Id) de la Segunda Prada.
	 * Linea: Las linea que atraviesa el Tramo.
	 * Tiempo: Tiempo estimado En recorrer el Tramo.
	 */
	private Vertice PrimeraParada;
	private Vertice SegundaParada;
	private String Linea;
	private int Tiempo;
	/**
	 * 
	 * @param PP Numero (Id) de la Primera Parada.
	 * @param SP Nimero(Id) de la Segunda Prad
	 * @param Linea Las linea que atraviesa el Tramo.
	 * @param Tiempo Tiempo estimado En recorrer el Tramo.
	 */
	public Arista(Vertice PP,Vertice SP,String Linea, int Tiempo) {
		this.PrimeraParada=PP;
		this.SegundaParada=SP;
		this.Linea=Linea;
		this.Tiempo=Tiempo;
	}
	/**
	 * Metodo Getter que retorna el Numero(Id) de la Primera parada del tramo
	 * @return PrimeraParada: Priemra parada del tramo
	 */
	public Vertice ObtenerPrimeraParada() {
		return this.PrimeraParada;
	}
	/**
	 * Metodo Getter que retorna el Numero(Id) de la SegundaParada del tramo
	 * @return SegundaParada: Segunda Parada del tramo
	 */
	public Vertice ObtenerSegundaParada() {
		return this.SegundaParada;
	}
	/**
	 * Metodo getter que retorna El tipo de la linea que pertenece El tramo
	 * @return Linea: Tipo de Linea del tramo
	 */
	public String ObtenerLinea() {
	 return this.Linea;	
	}
	/**Metodo Getter que retorna el Tiempo estimado de recorrer el tramo.
	 * 	@return Tiempo: Tiempo estimado de recorrer el tramo
	 */
	public int ObtenerTiempo() {
		return this.Tiempo;
	}
}

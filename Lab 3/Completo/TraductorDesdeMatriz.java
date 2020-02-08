/*Estudiantes:
 * Yerimar Manzo: 14-10611
 * Jonathan Bautista: 16-10109

 */
/**Almacena un grafo representado en matriz de adyacencia el cual sale
 * de leer un grafo en su forma de lista de adyacencia desde un archivo .txt
 * proporcionado en dicha forma
 */
public class TraductorDesdeMatriz extends TraductorGrafo {	
	//inicializa la matriz de adyacencia
	private int[][] MatrizdeAdyacencia;
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	TraductorDesdeMatriz(int Vertices){
		MatrizdeAdyacencia=new int[Vertices][Vertices];
		
	}
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal) {
		MatrizdeAdyacencia[verticeInicial][verticeFinal]=MatrizdeAdyacencia[verticeInicial][verticeFinal]+1;
	}
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido() {
		String Traduccion="";
		for(int i=0; i<MatrizdeAdyacencia.length;i++) {
			Traduccion=Traduccion+"V"+i+":";
			for(int j=0;j<MatrizdeAdyacencia[i].length;j++) {
				if(MatrizdeAdyacencia[i][j]!=0) {
					Traduccion=Traduccion+" V"+j;
				}
				else {
				}				
			}
			Traduccion=Traduccion+"\n";
		}
		return Traduccion;
	}
}

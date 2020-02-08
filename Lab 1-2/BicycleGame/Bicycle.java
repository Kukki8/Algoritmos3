/**La clase <code>Bicycle</code> representa una bicicleta del mundo real.
 * Mantiene su {@link #rapidez}, {@link #velocidad} y {@link #cadencia}
 */
public class Bicycle implements LandVehicle{
	
	/**Almacena la cadencia de los pedales en revoluciones por minuto.
	 * Se puede obtener con {@link #getCadence()} y fijar con
	 * {@link #changeCadence(int)}.
	 */
    int cadencia = 0;
    
    /**Almacena la rapidez traslacional en millas por hora.
     * Se puede obtener con {@link #getSpeed()} y fijar con
     * {@link #speedUp(int)}.
     */
    int rapidez = 0;
    
    /**Almacena el engranaje actual del cassette posterior de la
     * bicicleta (primera, segunda, tercera, etc).
     */
    int velocidad = 1;
    
    /**Fija la cadencia al n&uacute;mero de revoluciones indicado
     * 
     * @param nuevaCadencia
     * 		N&uacute;mero de revoluciones por minuto
     *      al cual se fijar&aacute; la cadencia.
     */

     int numRuedas = 2;

    public void changeCadence(int nuevaCadencia) {
         this.cadencia = nuevaCadencia;
    }

    /**Fija el engranaje actual al dado por la
     * <code>nuevaVelocidad</code>-&eacute;sima velocidad
     * @param nuevaVelocidad
     * 		Ordinal de la velocidad que se fijar&aacute;.
     */
    public void changeGear(int nuevaVelocidad) {
         this.velocidad = nuevaVelocidad;
    }

    /**Suma a {@link rapidez} el aumento indicado en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param aumento
     * 		Cantidad de millas en las que se 
     *      aumentar&aacute; la rapidez.
     */
    public void speedUp(int aumento) {
        this.rapidez = this.rapidez + aumento;   
    }

    /**Resta a {@link rapidez} la disminuci&oacute;n indicada
     * en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param disminucion
     * 		Cantidad de millas en las que 
     * 		disminuir&aacute; la rapidez.
     */
    public void applyBrakes(int disminucion) {
         this.rapidez = this.rapidez - disminucion;
    }

    /**Imprime los valores actuales de {@link #cadencia},
     * {@link #rapidez} y {@link #velocidad} por c&oacute;nsola.
     */
    public void printStates() {
        System.out.println(
        		" cadencia:"  + this.cadencia +
        		" rapidez:"   + this.rapidez  +
        		" velocidad:" + this.velocidad
        		);
   }
   
    /**Obtiene la cadencia actual.
     * 
     * @return el valor actual de {@link #cadencia}.
     */
   public int getCadence(){
	   return this.cadencia;
   }
   
   /**Obtiene la rapidez actual.
    * 
    * @return el valor actual de {@link #rapidez}.
    */
   public int getSpeed(){
	   return this.rapidez;
   }
   
   /**Obtiene la velocidad actual.
    * 
    * @return el valor actual de {@link #velocidad}.
    */
   public int getGear(){
	   return this.velocidad;
   }

   public int contarRuedas(){
        return this.numRuedas;
   }
   
   /**M&eacute;todo que envuelve a {@link #changeCadence(int)}
    * 
    * @param nuevaCadencia
    * 		N&uacute;mero de revoluciones por minuto
    *      al cual se fijar&aacute; la cadencia.
    */
   public void changeRpm(int newValue){
       changeCadence(newValue);
   }
}

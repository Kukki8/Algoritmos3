/**Clase principal del juego de la bicicleta tandem. El juego es de dos jugadores.
 */
public class Main{
	/**Fija la cadencia que est&aacute; intentando lograr el ciclista delantero
	 * de la bicicleta t&aacute;ndem <code>t</code> en <code>p</code> rpm.
	 * 
	 * @param t Bicicleta t&aacute;ndem donde est&aacute; el ciclista
	 * @param p cadencia de pedal que intenta lograr el ciclista en rpm
	 */
	static void pedalearDelante(Tandem t, int p){
		int actual = t.getCadence();
		t.changeCadence(p, actual);
	}
	
	/**Fija la cadencia que est&aacute; intentando lograr el ciclista
	 * de atr&aacute;s de la bicicleta t&aacute;ndem <code>t</code>
	 * en <code>p</code> rpm.
	 * 
	 * @param t Bicicleta t&aacute;ndem donde est&aacute; el ciclista
	 * @param p cadencia de pedal que intenta lograr el ciclista en rpm
	 */
	static void pedalearDetras(Tandem t, int p){
		int actual = t.getCadence();
		t.changeCadence(actual, p);
	}
	
	/**Contiene el juego como tal.*/
	public static void main(String[] args) throws java.io.IOException{
		Tandem t = new Tandem();
		int ganaDelante = 0;
		int ganaDetras  = 0;
		
		System.out.println(
				"Presione 'a' (seguido de \"Enter\") para pedalear en los pedales delanteros\n"+
				"Presione 'l' (seguido de \"Enter\") para pedalear en los pedales de atras\n"+
				"Presione 'n' (seguido de \"Enter\") para salir");
		
		while(true){
			char c = (char)System.in.read();
			if(c=='a'){
				pedalearDelante(t, t.getCadence()+2);
				t.printStates();
				if(t.esfuerzoDelante < t.esfuerzoDetras){
					ganaDetras++;
				}else{
					ganaDelante++;
				}
			}
			if(c=='l'){
				pedalearDetras(t, t.getCadence()+2);
				t.printStates();
				if(t.esfuerzoDelante < t.esfuerzoDetras){
					ganaDetras++;
				}else{
					ganaDelante++;
				}
			}
			if(c=='n'){
				if(ganaDelante < ganaDetras){
					System.out.println("El ciclista de atras hizo "+
							"la mayor parte del esfuerzo en "+
							ganaDetras+" de "+(ganaDelante+ganaDetras)+
							" pedaleadas. Felicidades, ciclista de atras!");
				}else{
					System.out.println("El ciclista delantero hizo "+
							"la mayor parte del esfuerzo en "+
							ganaDelante+" de "+(ganaDelante+ganaDetras)+
							" pedaleadas. Felicidades, ciclista delantero!");
				}
				return;
			}
		}
	}
}

public class Tandem extends Bicicleta{
	double esfuerzoDelante = 0.5;
	double esfuerzoDetras = 0.5;

	public void printStates(){
		super.printStates();
		System.out.println("esfuerzoDelante: " + this.esfuerzoDelante + "esfuerzoDetras: " + this.esfuerzoDetras);


	}
	
	 public void changeCadence(int pedalDelante, int pedalDetras){

	 	this.cadencia = (int)(pedalDelante*this.esfuerzoDelante + pedalDetras * this.esfuerzoDetras);

		this.esfuerzoDelante =
				(double)pedalDelante / (double)this.cadencia / 2.0;
		this.esfuerzoDetras =
				 (double)pedalDetras / (double)this.cadencia / 2.0;

	 }

}



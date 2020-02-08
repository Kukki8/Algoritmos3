public class Bicicleta implements LandVehicle{

	int cadencia = 0;
	int rapidez = 0;
	int velocidad = 1;
	protected int gears = 7;


	public void changeCadence(int nuevaCadencia){
		this.cadencia = nuevaCadencia;
	}


	public void changeGear(int nuevaVelocidad) throws IllegalArgumentException{

		try{

			this.velocidad = nuevaVelocidad;

		}catch(IllegalArgumentException){
			throw newException("La velocidad" + this.nuevaVelocidad + "no existe");

		}

	}


	public void speedUp(int aumento){
		this.rapidez = this.rapidez + aumento;
	}

	public void applyBreaks(int disminucion){
		if (this.rapidez <= 0) {
			return;
		}
		else{
			if (this.rapidez - disminucion < 0) {
				this.rapidez = 0;
			}
			else {
				this.rapidez -= disminucion;
			}
		}
	}

	public void printStates(){
		System.out.println("cadencia: " + this.cadencia + "rapidez: " + this.rapidez + "velocidad: " + this.velocidad);
	}

	public void changeRpm(int newValue){
		changeCadence(newValue);
	}


}
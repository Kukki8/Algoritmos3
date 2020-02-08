class BicicletaDemo{

	public static void main(String[] args){

	//Crear dos objestos Bicycle distintos
	Bicicleta bike1 = new Bicicleta();
	Bicicleta bike2 = new Tandem();

	//Invocar metodos sobre esos objetos
	bike1.changeCadence(50);
	bike1.speedUp(10);
	bike1.changeGear(2);
	bike1.printStates();

	bike2.changeCadence(50);
	bike2.speedUp(10);
	bike2.changeGear(2);
	bike2.changeCadence(40);
	bike2.speedUp(10);
	bike2.changeGear(3);
	bike2.printStates();

	}
}

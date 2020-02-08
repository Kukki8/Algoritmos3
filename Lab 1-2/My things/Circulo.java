public class Circulo{
	private double radio;
	private String color;

	public void asigRadio(double newRadius)
	{
		this.radio = newRadius;
	}
	public double getRadio()
	{
		return this.radio;
	}

	public double Area(){
		return radio*radio*Math.PI;
	}

	public static void main(String args[])
	{
		Circulo cl;
		double rl;
		cl = new Circulo();
		System.out.println(cl.getRadio());
	}

}


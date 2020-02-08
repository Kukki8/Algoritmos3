public class A{

	private int x;

	public void metodo(int y){
		int x = 2;
		y = 3*x + y - x ;
		System.out.println(y);
	}

	public void setX(int a){
		x = a;
	}

	public int getX(){
		return this.x;
	}

public static void main (String[] args){
	int arg = 4;
	A obj = new A();
	obj.setX(1);
	obj.metodo(arg);
	System.out.println(arg);
	System.out.println(obj.getX());
	}

}


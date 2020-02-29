public class Mesa{
    private int coordX;
    private int coordY;
    private int id;

    Mesa(int x, int y, int id){
        this.coordX = x;
        this.coordY = y;
        this.id = id;
    }

    public int obtenerX(){
        return this.coordX;
    }

    public int obtenerY(){
        return this.coordY;
    }

    public int obtenerID(){
        return this.id;
    }
}
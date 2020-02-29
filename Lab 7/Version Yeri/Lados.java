public class Lados{

    public Mesa mesa1 ;
    public Mesa mesa2 ;

    Lados(Mesa mesaIn, Mesa mesaFi){
        this.mesa1 = mesaIn;
        this.mesa2 = mesaFi;
    }

    public double obtenerCosto(Mesa mesaIn, Mesa mesaFin){
        int x1 = mesaIn.obtenerX();
        int x2 = mesaFin.obtenerX();
        int y1 = mesaIn.obtenerY();
        int y2 = mesaFin.obtenerY();

        double mpx =  Math.pow(x2 - x1, 2);
        double mpy = Math.pow(y2 - y1, 2);

        return Math.sqrt(mpx + mpy);
    }

}
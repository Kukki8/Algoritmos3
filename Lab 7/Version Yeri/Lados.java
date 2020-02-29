public class Lados{

    //private int costo ;
    Mesa mesa1 ;
    Mesa mesa2 ;

    Lados(Mesa mesaIn, Mesa mesaFi){
        this.mesa1 = mesaIn;
        this.mesa2 = mesaFi;
    }

    public double obtenerCosto(Mesa mesaIn, Mesa mesaFin){
        return Math.sqrt(Math.pow(mesaFin.obtenerX() - mesaIn.obtenerX(), 2) + Math.pow(mesaFin.obtenerY() - mesaIn.obtenerY(), 2));
    }

}
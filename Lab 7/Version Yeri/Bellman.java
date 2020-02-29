import java.util.LinkedList;

public class Bellman{

    public int[] costo;
    public int[] predecesor;
    public Mesa mesa;
    public GrafoND grafo;

    Bellman(GrafoND grafo){
        this.costo = new int[grafo.lados.size()];
        this.predecesor = new int[grafo.lados.size()];
    }

    public void hacerBellman(Mesa m){
        costo[m.obtenerID()] = 0;
        int i = 1;
        boolean cambio = true;

        while(i < grafo.lados.size() && cambio){
            cambio = false;
            for(Lados l : grafo.lados){
                if(costo[l.mesa2.obtenerID()] > costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2)){
                    costo[l.mesa2.obtenerID()] = costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2);
                    predecesor[l.mesa2.obtenerID()] = l.mesa1.obtenerID();
                    cambio = true;
            }
            i+=1;
        }
    }

        for(Lados l : grafo.lados){
            if(costo[l.mesa2.obtenerID()] > costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2)){
                System.out.println("Error, hay un circuito de costo negativo");
                return;
            }
        }


}
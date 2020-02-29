import java.util.Stack;
import java.text.DecimalFormat;

public class Bellman{

    public double[] costo;
    public int[] predecesor;
    public Mesa mesa;
    public GrafoND grafo;

    Bellman(GrafoND grafo){
        this.costo = new double[grafo.mesas.size()];
        this.predecesor = new int[grafo.mesas.size()];
        this.grafo = grafo;

        for(int i = 0; i < grafo.mesas.size(); i++){
            predecesor[i] = -1;
            costo[i] = Integer.MAX_VALUE;
        }

    }

    public void hacerBellman(int id){
        Mesa m = grafo.obtenerMesa(id);
        costo[m.obtenerID()] = 0;
        int i = 1;
        boolean cambio = true;

        while(i < grafo.mesas.size() && cambio){
            cambio = false;
            for(Lados l : grafo.lados){

                if(costo[l.mesa2.obtenerID()] > costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2)){
                    costo[l.mesa2.obtenerID()] = costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2);
                    predecesor[l.mesa2.obtenerID()] = l.mesa1.obtenerID();
                    cambio = true;
                }
            }
            i+=i;
        }

        for(Lados l : grafo.lados){
            if(costo[l.mesa2.obtenerID()] > costo[l.mesa1.obtenerID()] + l.obtenerCosto(l.mesa1,l.mesa2)){
                System.out.println("Error, hay un circuito de costo negativo");
                return;
            }
        }

        imprimirBellman(predecesor, costo);
    }

    public void imprimirBellman(int[] predecesores, double[] costo){

        Stack<Integer> camino = new Stack<Integer>();
        DecimalFormat df = new DecimalFormat("0.0#");

        for(int k = 0; k < grafo.mesas.size() ; k++){

            double c = costo[k];
            int l = 0;
            System.out.print("Nodo " + k +":");
            if(predecesores[k] == -1){
                System.out.println( k + "\t" + l + " lados costo(" + df.format(c) + " )" );

            }else{
                int p = predecesores[k];
                camino.push(k);
                while(p != -1){
                    camino.push(p);
                    l += 1;
                    p = predecesores[p];
                }
                while(camino.size()>1){
                    System.out.print(camino.pop() + "->");
                }

                System.out.println(camino.pop() + "\t" + l + " lados costo(" + df.format(c) + ")");             
            }
        }

    }

}
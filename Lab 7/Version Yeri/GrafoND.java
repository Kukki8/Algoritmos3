import java.util.LinkedList;

public class GrafoND{
    public LinkedList<Lados> lados ;
    public LinkedList<Mesa> mesas ;

    GrafoND(){
        this.lados = new LinkedList<Lados>();
    }


    public void agregarMesa(int x, int y, int id){
        Mesa nuevaMesa = new Mesa(x,y, id);
        mesas.add(nuevaMesa);

    }

    public void agregarLado(Mesa In , Mesa Fi){
        Lados nuevoLado = new Lados(In,Fi);
        lados.add(nuevoLado);
    }

}
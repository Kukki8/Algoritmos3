import java.util.LinkedList;

public class GrafoND{
    public LinkedList<Lados> lados ;
    public LinkedList<Mesa> mesas ;

    GrafoND(){
        this.lados = new LinkedList<Lados>();
        this.mesas = new LinkedList<Mesa>();
    }


    public void agregarMesa(int x, int y, int id){
        Mesa nuevaMesa = new Mesa(x,y, id);
        mesas.add(nuevaMesa);

    }

    public void agregarLado(Mesa In , Mesa Fi){
        Lados nuevoLado1 = new Lados(In,Fi);
        lados.add(nuevoLado1);
        Lados nuevoLado2 = new Lados(Fi,In);
        lados.add(nuevoLado2);
    }

    public Mesa obtenerMesa(int id){

        for(Mesa m : mesas){
            if(m.obtenerID() == id){
                return m;
            }
        }
        return null;
    }

}
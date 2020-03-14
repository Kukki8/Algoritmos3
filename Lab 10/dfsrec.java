public class dfsrec{

    public int[] p;
    public Grafo grafito;
    public Vertices v;

    dfsrec(Grafo grafito){

        this.grafito = grafito;
        this.p = new int[grafito.vertices.size()];

    }

    public void hacerDFSRec(Vertices v){
        String vn = v.obtenerNombre();
        int vid = v.obtenerId();
        for(Lados l : grafito.grafo){
            String lnIn= l.verIni.obtenerNombre();

            if(lnIn.equals(vn)){
                int lidFin = l.verFin.obtenerId();
                if(p[lidFin] == 0){
                    p[lidFin] = vid;
                    hacerDFSRec(l.verFin);

                }

            }
        }
    }
}
public class OrdenTop{
    public int c ;
    public boolean [] visitados;
    public int [] f;
    public Grafo grafito; 

    OrdenTop(Grafo grafito){

        this.grafito = grafito;
        this.c = grafito.vertices.size() + 1;
        this.visitados = new boolean[grafito.vertices.size()];
        this.f = new int[grafito.vertices.size()];

    }

    public void hacerdfsREC(){

        for(Vertices w : grafito.vertices){
            if(visitados[w.obtenerId()] == false){
                dfsREC(w);
            }
        }

        for(int k = 0; k < grafito.vertices.size() + 1; k++){
            int j = 0;
            while(j < grafito.vertices.size()){
                if(f[j] == k){
                    for(Vertices v : grafito.vertices){
                        if(v.obtenerId() == j){
                            System.out.print(v.obtenerNombre() + " ");
                            break;
                        }
                    }
                }
                j+= 1;
            }
        }
    }

    public void dfsREC(Vertices v){
        int vid = v.obtenerId();
        String vn = v.obtenerNombre();
        visitados[vid] = true;
        
        for(Lados l : grafito.grafo){
            String lnIn = l.verIni.obtenerNombre();

            if(lnIn.equals(vn)){
                int lidFin = l.verFin.obtenerId();
                    if(visitados[lidFin] == false){
                        dfsREC(l.verFin);
                    }
                }
        }
        c = c - 1;
        f[v.obtenerId()] = c;
    }
}
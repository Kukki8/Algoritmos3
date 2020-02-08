public class GrafoD{

    public int [][] matrizAdyacencia;
    public int vertice;
     
        GrafoD(int vertice){
            this.vertice = vertice;
            this.matrizAdyacencia = new int [this.vertice][this.vertice];
        }
    
        public void add(int n, int m){
            matrizAdyacencia[n][m] = 1;
    
        }
    
        public void delete(int n, int m){
            matrizAdyacencia[n][m] = 0;     
        }
    
    }
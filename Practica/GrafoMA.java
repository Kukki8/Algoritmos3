public class GrafoMA{
    public int [][] matriz;
    public int m;
    public int n;


    public GrafoMA(int n, int m){
        this.n = n;
        this.m = m;
        this.matriz = new int[this.n][this.m];
        
    }

    public void Add(int i, int j){
        matriz[i][j] = 1;
        matriz[j][i] = 1;
    }

    public void Delete(int i,int j){
        if(matriz[i][j] > 0){
            matriz[i][j] = 0;
            matriz[j][i] = 0;
        }
    }

}
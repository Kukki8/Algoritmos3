public class GrafoMI{

    public int [][] matriz;
    public int lado;
    public int vertice;

    public void crearGrafoMI(int lado, int vertice){
        this.lado = lado;
        this.vertice = vertice;
        matriz = new int[this.vertice][this.lado];

    }



}
public class Test{

    public static void main(String [] args){

        GrafoMA test = new GrafoMA(5,5);

        for(int i=0; i < test.n ; i++){

            for(int j=0; j < test.m ; j++){

                System.out.print(test.matriz[i][j] + " ");

            }
            System.out.println( );
        }
    }
}
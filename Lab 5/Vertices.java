public class Vertices{
    int id;
    int arco;
    int [] todo;
    int cantidad;



    vertice(int cantidad){
        this.cantidad = cantidad;

        for(i=0; i<cantidad; i++){
            todo= new int[cantidad];
        }
        
    }

    public int dameId(int n, int m){
        //while(todo[i])
        for(int i=0; i<cantidad; i++){
            if(todo[i]=n){
                return i;
            }

        }

    }

    public int dameArco(){

    }
}
public class Vertices{

    public String nombre;
    public int id;

    Vertices(String nombre , int id){
        this.nombre = nombre;
        this.id = id;
    }

    public String obtenerNombre(){
        return this.nombre;
    }

    public void imprimirNombre(Vertices v){
        System.out.println(v.obtenerNombre());
   }

   public int obtenerId(){
       return this.id;
   }

}
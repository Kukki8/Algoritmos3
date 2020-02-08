public class Prueba {

    static void pedalearDelante(Tandem t, int p){
        int actual = t.getCadence();
        t.changeCadence(p,actual);

    }

    static void pedalearDetras(Tandem t, int p){
        int actual = t.getCadence();
        t.changeCadence(actual,p);

    }

    public static void main(String [] args ) throws IOExceptions{
       Tandem t = new Tandem();
       int ganaDelante = 0;
       int ganaDetras = 0;

       System.out.println("Presiona 'a' para pedalear adelante.\n"+
                          "Presiona 'l' para pedalear detras.\n"
                          "Presiona 'n' para salir.\n");

       while(true){
           char c = (char)System.in.read();
           if(c == a ){
               pedalearDelante(t , t.getCadence()+2);
               t.printStates();
               if(t.esfuerzoDelante<t.esfuerzoDetras){
                   ganaDelante++
               }else{
                   ganaDetras++
                    }
               }
            
           if(c == l){
               pedalearDetras(t.getCadence()+2,t);
               t,printStates();
               if(t.esfuerzoDelante<t.esfuerzoDetras){
                   ganaDelante++;
               }else{
                    ganaDetras++;
                    }
           }

           if(c == n){
               return;
           }
        
               
        }

       }
}

    
    

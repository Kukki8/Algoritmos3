import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cliente {
		public static void main(String[] args) throws IOException, IllegalArgumentException{
			if(args.length < 1){
				System.err.println("por favor ejecute: java Cliente <nombreArchivo>");
				return;
			}
			try {
				
				String tipo_grafo;
				BufferedReader Lector=new BufferedReader(new FileReader(args[0]));
				String linea=Lector.readLine(); //primera linea
				tipo_grafo=linea.trim();
				 // creamos un grafo Dirigido o un grafo no Dirigido dependiendo de tipo_grafo
				if(tipo_grafo.equals("N")){
					Scanner entrada=new Scanner(System.in);
					GrafoNoDirigido grafo=null;
					grafo=new GrafoNoDirigido();
					grafo.CrearGrafoNoDirigido();
					grafo.cargarGrafo(args[0]);
					 while(true){
		                    System.out.println(
		                        "Menu de opciones sobre el grafo NoDirigido: \n" +
		                        "Presione a (seguido de \"Enter\") para saber el numero de Vertices del grafo \n" +
		                        "Presione b (seguido de \"Enter\") para saber el numero de Aristas del grafo \n" +
		                        "Presione c (seguido de \"Enter\") para agregar un nuevo Vertice al grafo  \n" +
		                        "Presione d (seguido de \"Enter\") para agregar una nueva Arista al grafo  \n" +
		                        "Presione e (seguido de \"Enter\") para obtener un Vertice del grafo \n" +
		                        "Presione f (seguido de \"Enter\") para obtener una Arista del grafo \n" +
		                        "Presione g (seguido de \"Enter\") para saber si un Vertice esta en el grafo \n" +
		                        "Presione h (seguido de \"Enter\") para saber si un Arista esta en el grafo \n" +
		                        "Presione i (seguido de \"Enter\") para eliminar un Vertice del grafo \n" +
		                        "Presione j (seguido de \"Enter\") para eliminar una Arista del grafo \n" +
		                        "Presione k (seguido de \"Enter\") para imprimir una lista de los Vertices del grafo \n" +
		                        "Presione l (seguido de \"Enter\") para imprimir la lista de Aristas del grafo \n" +
		                        "Presione m (seguido de \"Enter\") para imprimir el grado de un Vertice del grafo \n" +
		                        "Presione n (seguido de \"Enter\") para obtener los Vertices adyacentes a un Vertice dado \n" +
		                        "Presione o (seguido de \"Enter\") para obtener la lista de Arcos incidentes a un Vertice dado \n" +
		                        "Presione p (seguido de \"Enter\") para clonar el grafo a un grafo nuevo \n" +
		                        "Presione q (seguido de \"Enter\") para imprimir una representacion del grafo \n" +
		                        "Presione r (seguido de \"Enter\") para salir");
					
		                    String tecla=entrada.next();
		                    if(tecla.equalsIgnoreCase("a")){
		                    	 System.out.println("El numero de Vertices es: " + grafo.numeroDeVertices()+"\n");
		                    }
		                    if(tecla.equalsIgnoreCase("b")) {
		                    	System.out.println("El numero de Aristas es: " + grafo.numeroDeLados()+"\n");
		                    }
		                    if(tecla.equalsIgnoreCase("c")) {
		                    	boolean Funciona=false;
		                    	int id;
		                    	String nombre;
		                    	double x,y,peso;
		                    	System.out.println("Ingrese el identificador de lvertice");
		                    	id=entrada.nextInt();
		                    	System.out.println("Ingrese el nombre del vertice");
		                    	nombre=entrada.next();
		                    	System.out.println("Ingrese las coodenadas <X/Y> del vetice");
		                    	x=entrada.nextDouble();
		                    	y=entrada.nextDouble();
		                    	System.out.println("Ingrese el peso del vertice");
		                    	peso=entrada.nextDouble();
		                    
		                    		Vertice vertice= new Vertice(id, nombre, x, y, peso);
		                    		Funciona=grafo.agregarVertice(vertice);
		                    		if(Funciona) {
		                    			System.out.println("Se agrego el vertice al grafo\n");
		                    		}
		                    		else{
		                                System.out.println("El Vertice ya existia en el grafo, no se agrego nada.");
		                            }
		                    	}
		                    if(tecla.equalsIgnoreCase("d")) {
		                    	   boolean Funciona = false;
		                    	   double peso;
		                    	   int tipo,id1,id2;             
		                           System.out.println("Escriba el peso de la nueva Arista: ");
		                           peso = entrada.nextDouble();
		                           System.out.println("Escriba el tipo de la nueva Arista: ");
		                           tipo= entrada.nextInt();
		                           System.out.println("Escriba el Identificador del vertice 1 de la Arista: ");
		                           id1 = entrada.nextInt();
		                           System.out.println("Escriba el Identificador del vertice 2 de la Arista: ");
		                           id2 = entrada.nextInt();
		                           try {
		                        	   Arista aristanueva=new Arista(peso, tipo,grafo.obtenerVertice(id1),grafo.obtenerVertice(id2));
		                        	   Funciona=grafo.agregarArista(aristanueva);
		                        	   if(Funciona){
		                                   System.out.println("Se agrego La Arista al grafo");
		                               }
		                               else{
		                                   System.out.println("No se agrego la arista al grafo porque ya existia");
		                               }

		                           }catch(NoSuchElementException err1){
		                               System.out.println("Salio algo mal agregando la Arista, revisa si los vertices extremos existen en el Grafo");
		                           }
		                    }
		                    if(tecla.equalsIgnoreCase("e")) {
		                    	int id;
		                    	System.out.println("Ingrese e lidentificador del vetice");
		                    	id=entrada.nextInt();
		                    	try {
		                    		System.out.println(grafo.obtenerVertice(id).toString());
		                    	}catch(NoSuchElementException err) {
		                    		System.out.println("El Vertice Ingresado no existe en el Grafo");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("f")) {
		   
		                    	int id1,id2,tipo;
		                    	double peso;
		                    	Vertice v1,v2;
		                    	System.out.println("Ingrese el lidentificador del vetice1");
		                    	id1=entrada.nextInt();
		                    	System.out.println("Ingrese el lidentificador del vetice2");
		                    	id2=entrada.nextInt();
		                    	System.out.println("Ingrese el tipo de la Arista");
		                    	tipo=entrada.nextInt();
		                    	System.out.println("Ingrese el peso de la arista ");
		                    	peso=entrada.nextDouble();
		                    	try {
		                    	v1=grafo.obtenerVertice(id1);
		                    	v2=grafo.obtenerVertice(id2);
		                    	Arista nuevaarista=new Arista(peso,tipo,v1,v2);
		                    	System.out.println(grafo.obtenerArista(nuevaarista).toString());
		                    	}catch(NoSuchElementException err){
		                    		System.out.println("La arista/o los vertices  ingresados no existe en el grafo");
		                    		
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("g")) {
		                    	int id;
		                    	System.out.println("Ingrese el identificador del vertice");
		                    	id=entrada.nextInt();
		                    	if(grafo.estaVertice(id)) {
		                    		System.out.println("El vertice esta en el grafo");
		                    	}
		                    	else {
		                    		System.out.println("El vertice no esta en el grafo");
		                    	}
		                    	
		                    }
		                    if(tecla.equalsIgnoreCase("h")) {
		                    	String id1,id2;
		                    	int tipo;
		                    	System.out.println("Ingrese el identificador del vertice 1");
		                    	id1=entrada.next();
		                    	System.out.println("Ingrese el identificador del vertice 2");
		                    	id2=entrada.next();
		                    	System.out.println("Ingrese el tipo de la arista ");
		                    	tipo=entrada.nextInt();
		                    	if(grafo.estaArista(id1, id2, tipo)) {
		                    	   	System.out.println("La arista esta en el Grafo ");
		                    	}
		                    	else {
		                    	   	System.out.println("La arista no esta en el grafo ");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("i")) {
		                    	int id;
		                    	System.out.println("Ingrese el identificador del vertice");
		                    	id=entrada.nextInt();
		                    	if(grafo.eliminarVertice(id)) {
		                    		System.out.println("El vertice fue elimiando");
		                    	}
		                    	else {
		                    		System.out.println("El vertice no se encuentra en el grafo asegurece de que este");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("j")) {
		                    	int id1,id2, tipo;
		                    	double peso;
		                    	System.out.println("Ingrese el identificador del vertice1");
		                    	id1=entrada.nextInt();
		                    	System.out.println("Ingrese el identificador del vertice2");
		                    	id2=entrada.nextInt();
		                    	System.out.println("Ingrese el tipo de la arista");
		                    	tipo=entrada.nextInt();
		                    	System.out.println("Ingrese el peso de la Arista");
		                    	peso=entrada.nextDouble();
		                    	try {
		                    	Arista nuevaarista=new Arista(peso,tipo,grafo.obtenerVertice(id1),grafo.obtenerVertice(id2));
		                    	if(grafo.eliminarArista(nuevaarista)) {
		                    		System.out.println("La arista fue eliminada del grafo");
		                    	
		                    	}
		                    	else {
		                    		System.out.println("La arista no esta en el grafo grafo asegurece de que este");
		                    	}
		                    	}catch(NoSuchElementException err){
		                    		System.out.println("Los vertices no pertenecen al grafo");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("k")) {
		                    	System.out.println("la lista de vertices del grafo es:\n "+grafo.vertices().toString());
		                    }
		                    if(tecla.equalsIgnoreCase("l")) {
		                    	System.out.println("la lista de Arista del grafo es:\n "+grafo.lados().toString());
		                    }
		                    if(tecla.equalsIgnoreCase("m")) {
		                    	int id;
		                    	System.out.println("Ingrese el identificador del vertice");
		                    	id=entrada.nextInt();
		                    	try {
		                    		System.out.println("El grado del vertice es:\n "+ grafo.grado(id) );
		                    	}catch(NoSuchElementException err){
		                    		System.out.println("Este vertice no existe en el grafo asegurece de que si");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("n")) {
		                    	int id;
		                    	System.out.println("Ingrese el identificador del vertice");
		                    	id=entrada.nextInt();
		                    	try {
		                    		System.out.println("Los adyacendes del vertice son: \n"+grafo.adyacentes(id).toString());
		                    	}catch(NoSuchElementException err){
		                    		System.out.println("Este vertice no existe en el grafo asegurece de que si");
		                    	}
		                    	
		                    }
		                    if(tecla.equalsIgnoreCase("o")) {
		                    	int id;
		                    	System.out.println("Ingrese el identificador del vertice");
		                    	id=entrada.nextInt();
		                    	try {
		                    		System.out.println("Las Aristas incidentes al vertice son: \n"+grafo.incidentes(id).toString());
		                    	}catch(NoSuchElementException err){
		                    		System.out.println("Este vertice no existe en el grafo asegurece de que si");
		                    	}
		                    }
		                    if(tecla.equalsIgnoreCase("p")) {
		                    	Clonacion grafoclon;
		                    	grafoclon=grafo.clone();
		                    	LinkedList<LinkedList<Vertice>> GrafoClonado=new  LinkedList<LinkedList<Vertice>>();
		                    	GrafoClonado=grafoclon.Damegrafo();
		                    	LinkedList<Arista> Aristasclonadas=new LinkedList<Arista>();
		                    	Aristasclonadas=grafoclon.DameAristas();
		                    }
		                    if(tecla.equalsIgnoreCase("q")) {
		                    	System.out.println("Grafo:\n"+grafo.toString());
		                    }
		                    if(tecla.equalsIgnoreCase("r")) {
		                    	break;
		                    }
					 }      
		                    
				}
			}catch(IOException err) {
				System.out.println("Excepci�n: Hay problemas para cargar grafo.txt");
			}
		}
	}	



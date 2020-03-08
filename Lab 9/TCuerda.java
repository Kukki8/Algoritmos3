import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class TCuerda {
	public static Grafo CargarGrafo(String Archivo)throws IOException {
		int n; // numero de profesores
		BufferedReader Lector = new BufferedReader(new FileReader(Archivo)); // lector del archivo
		String linea=Lector.readLine(); //linea 1
		Grafo grafo=new Grafo(); 
		n=Integer.parseInt(linea);
		for(int i=0;i<n;i++) {  // carga cada peso de cada profesor en los vertices del grafo
			linea=Lector.readLine();
			Vertice vertice=new Vertice(i,Integer.parseInt(linea)); // crea vertice con el peso
			grafo.agregarVertice(vertice); //agrega el vertice
		}
		return grafo;
	}
	
	public static int BackTracking (Grafo grafo) {
		LinkedList<Vertice> Jugadores=grafo.DameLista(); // todos los jugadores de la competicon
		LinkedList<Vertice> SolucionActual =new LinkedList<Vertice>(); // posible solucion contiene un numero de pesos
		LinkedList<Vertice> MejorSolucion=Jugadores; // mejor solucion del momento
		int meta=0; // inicializa variable meta
		int jugadorActual; //inicializa jugadorActual que sera el jugador del momento
		int ultimo; // inicializa el ultimo jugador
		for(Vertice e:Jugadores) {
			meta=meta+e.DamePeso(); //suma todos los peos de todos los jugadores
		}
		meta=meta/2; // puesto que son dos equipo se divide entre dos
		int MejorPuntuacion=meta*2; // la mejor puntuacion sera la inicial
		int ultimojugador=Jugadores.getLast().Dameid(); //obtine el ultimo jugador
		int PuntuacionActual=Jugadores.get(0).DamePeso(); //se agrega el primer jugador
		SolucionActual.add(Jugadores.get(0));//se agrega el peso
		int z,k;
		while(SolucionActual.size()!=0) {
			z=Math.abs(meta-PuntuacionActual);// chequea si PuntuacionActual esta mas cerca de la meta Que mejor puntuacion
			k=Math.abs(meta-MejorPuntuacion);
			if(PuntuacionActual==meta) {
				return PuntuacionActual;
			}
			
			else if(z<k) {
				MejorSolucion=SolucionActual;// actualiza los valores
				MejorPuntuacion=PuntuacionActual;
			}
			jugadorActual=SolucionActual.getLast().Dameid();// el jugador actual sera el ultimo de La solucionActual
			if(jugadorActual!=ultimojugador) {
				Vertice jugador=Jugadores.get(SolucionActual.size()); // agrega a la solucion actual un nuevo jugador esto seria ir bajando en el arbo lde busqueda
				SolucionActual.add(jugador);
			}
			else {
				ultimo=ultimojugador; //ahora se empieza a evaluar los posibles casos desde atras hacia adelante "Bactracking"
				while(ultimo==ultimojugador && SolucionActual.size()>1) {
					PuntuacionActual=PuntuacionActual-SolucionActual.getLast().DamePeso();
					SolucionActual.removeLast(); //se actualizan los valores
					ultimo=SolucionActual.getLast().Dameid();
				}
				PuntuacionActual=PuntuacionActual-Jugadores.get(ultimo).DamePeso();
				SolucionActual.removeLast();
				if(SolucionActual.size()==0) { // cuando no existan jugadores en la solucion Actual termina se vieron todas las posibles soluciones
					break;
				}
				SolucionActual.add(Jugadores.get(ultimo+1));
			}
			PuntuacionActual=PuntuacionActual+SolucionActual.getLast().DamePeso(); // se actualiza la PuntuacionActual
		}
		return MejorPuntuacion; // se retorna la mejor puntuacion o la que esta mas proxima a la meta
	}
	
	
	
	public static void main(String[] args) throws IOException {
		if(args.length < 1){
			System.err.println("por favor ejecute: java TCuerda <nombreArchivo>");
			return;
		}
		else {
			int PesoTotal=0;
			int PesoEquipo1 = 0;// inicializa los pesos Totales y de cada equipo
			int PesoEquipo2=0;
			Grafo grafo=new Grafo(); //carga el grafo
			grafo =CargarGrafo(args[0]);
			PesoEquipo1=BackTracking(grafo); // el backtracking carga el peso del equipo 1
			for(Vertice e:grafo.DameLista()) {
				PesoTotal=PesoTotal+e.DamePeso(); 
			}
			PesoEquipo2=PesoTotal-PesoEquipo1; // se le resta el equipo 1 al peso total
			if(PesoEquipo1<PesoEquipo2) {
				System.out.println(PesoEquipo1+" "+PesoEquipo2);
			}
			else {
				System.out.println(PesoEquipo2+" "+PesoEquipo1);
			}
		
		}
	}
}

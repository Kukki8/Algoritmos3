import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
public class MejorRuta {
	public static GrafoNoDirigido GrafoInducidoLineas(GrafoNoDirigido grafo, String ArchivoLineas) throws IOException {
		BufferedReader Lector = new BufferedReader(new FileReader(ArchivoLineas));
		GrafoNoDirigido GrafoClon=null;
		GrafoClon=grafo;
		LinkedList<String> LineasDisponibles=new LinkedList<String>();
		LinkedList<String> TodasLasLineas=new LinkedList<String>();
		LinkedList<String> LineaApagadas=new LinkedList<String>();
		String linea;
		linea=Lector.readLine();
		while(linea!=null) {
			LineasDisponibles.add(linea);
			linea=Lector.readLine();
		}
		
		
		for(Arista e:grafo.Tramos) {
			TodasLasLineas.add(e.ObtenerLinea());
		}
		LinkedList<Arista> salvararista=new LinkedList<Arista>();
		for (String e:LineasDisponibles) {
			e.trim();
			for(Arista f:grafo.Tramos) {
				if(f.ObtenerLinea().equalsIgnoreCase(e)) {
					salvararista.add(f);
				}
			}
		}
		LinkedList<Arista> TodaslasAristas=new LinkedList<Arista>();
		for(Arista e:GrafoClon.Tramos) {
			TodaslasAristas.add(e);
		}
		for(Arista e:TodaslasAristas) {
			GrafoClon.eliminarArista(e);
		}
		for(Arista e:salvararista) {
			int v1=e.ObtenerPrimeraParada().ObtenerId();
			int v2=e.ObtenerSegundaParada().ObtenerId();
			String Linea=e.ObtenerLinea();
			Linea.trim();
			int Tiempo=e.ObtenerTiempo();
			GrafoClon.agregarArista(v1, v2, Linea, Tiempo);
		}
	
		return GrafoClon;
	}
	public static int[]  RecorridoCamino(GrafoNoDirigido grafo, int v1, int v2, int[] recorrido) {
		for(int x=0; x<recorrido.length;x++) {
			recorrido[x]=-1;
		}
		recorrido[v1]=v1;
		recursion(v1,grafo, recorrido);
		return recorrido;
	}
	
	public static void recursion(int vertice,GrafoNoDirigido grafo,int[] recorrido) {
		int pos=0;
		for(int i=0;i<grafo.Mapa.get(vertice).size();i++) {
			pos=DamePosdeVertice(grafo.Mapa.get(vertice).get(i).ObtenerId(), grafo);
			if(recorrido[pos]==-1) {
				recorrido[pos]=vertice;
				recursion(pos,grafo, recorrido);
			}
		}
	
		
	}
	public static int DamePosdeVertice(int args, GrafoNoDirigido grafo) {
		int lugar = 0;
		for(int i=0;i<grafo.Mapa.size();i++) {
			if(grafo.Mapa.get(i).get(0).ObtenerId()==args) {
				lugar=i;
			}
		}
		return lugar;
	}
	public static void main(String[] args) throws IOException {
		if(args.length < 1){
			System.err.println("por favor ejecute: java PlanearTrassbordos <mapa> <Lineas> <pIni> <pFin>");  //Error en caso de no colocar el nombre del archivo
			return;
		}
	
			String tipo_grafo;
			BufferedReader Lector=new BufferedReader(new FileReader(args[0]));
			String linea=Lector.readLine(); //primera linea
			tipo_grafo=linea.trim();
			 // creamos un grafo Dirigido o un grafo no Dirigido dependiendo de tipo_grafo
			if(tipo_grafo.equals("n")){                                   //Inicio del cado NoDirigido
				GrafoNoDirigido grafo=null;
				grafo=new GrafoNoDirigido();
				grafo.cargarGrafo(args[0]);
				boolean hola;
				GrafoNoDirigido GrafoClon;
				LinkedList<Arista> lineasdisponibles;
				GrafoClon=GrafoInducidoLineas(grafo, args[1]);
				int[] recorrido= new int[GrafoClon.Mapa.size()];
				int verticeIn;
				int verticeFn;
				verticeIn=DamePosdeVertice(Integer.parseInt(args[2]), grafo);
				verticeFn=DamePosdeVertice(Integer.parseInt(args[3]), GrafoClon);
				RecorridoCamino(GrafoClon,verticeIn, verticeFn, recorrido);
				System.out.print(GrafoClon.toString());
				for(int i=0; i<recorrido.length;i++) {
					System.out.println(recorrido[i]);
				}
				/*for(Arista e:lineasdisponibles) {
					System.out.println(e.ObtenerPrimeraParada().ObtenerId()+" "+ e.ObtenerSegundaParada().ObtenerId()+" "+e.ObtenerLinea());
				}
				*/
			}
		
	}
}


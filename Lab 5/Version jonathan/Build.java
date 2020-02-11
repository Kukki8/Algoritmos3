import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Build {
	public static Grafos CargarGrafos(String Archivo) throws IOException {
		int N,M,contador=0;
		Grafo grafo=new Grafo();
		Grafo grafoinv=new Grafo();
		BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
		String linea=Lector.readLine();
		N=Integer.parseInt(linea);
		linea=Lector.readLine();//segunda linea
		M=Integer.parseInt(linea);
		for (int i=0;i<N;i++) {
			grafo.agregarVertices(i);
			grafoinv.agregarVertices(i);
		}
		linea=Lector.readLine();//tercera linea
		while(linea!=null) {
			Cargarlados(linea,grafo);
			Cargarladosinversos(linea,grafoinv);
			linea=Lector.readLine();
			contador++;
		}
		if(contador==M) {
			Grafos Grafos=new Grafos(grafo,grafoinv);
			return Grafos;
		}
		else {
			throw new IllegalArgumentException("    **Error el numero de arcos no corresponde**");
		}
	}
	
	
	public static void Cargarlados(String linea, Grafo grafo) {
		String[] lineasinespacio;
		int Vertice1,Vertice2;
		lineasinespacio=linea.split(" ");
		Vertice1=Integer.parseInt(lineasinespacio[0]);
		Vertice2=Integer.parseInt(lineasinespacio[1]);
		grafo.agregarArco(Vertice1, Vertice2);
	}

	public static void Cargarladosinversos(String linea, Grafo grafo) {
		String[] lineasinespacio;
		int Vertice1,Vertice2;
		lineasinespacio=linea.split(" ");
		Vertice1=Integer.parseInt(lineasinespacio[1]);
		Vertice2=Integer.parseInt(lineasinespacio[0]);
		grafo.agregarArco(Vertice1, Vertice2);
	}
	
	public static int Conexas(Grafo grafo, Grafo grafoinv) {
		int edificios = 0;
		boolean [] visitados1=new boolean[grafo.Dametamaño()];
		for (int j=0;j<visitados1.length;j++) {
			visitados1[j]=false;
		}
		boolean [] visitados2=new boolean[grafoinv.Dametamaño()];
		for (int x=0;x<visitados2.length;x++) {
			visitados2[x]=false;
		}
		LinkedList<Integer> recorrido1;
		LinkedList<Integer> recorrido2=new LinkedList<Integer>();
		DFS dfs=new DFS();
		DFS dfs2=new DFS();
		recorrido1=dfs.CrearDFS(0, grafo,visitados1);
		for(int i=0;i<recorrido1.size();i++) {
			recorrido2=dfs2.CrearDFS(recorrido1.get(i), grafoinv, visitados2);
			if(recorrido2.size()>=2) {
				for(int z=0;z<recorrido2.size();z++) {
					visitados2[recorrido2.get(z)]=true;
				}
				edificios++;
				recorrido2.clear();
			}
			else {
				
			}
			
		}
		
		return edificios;
	}
	
	
	
	
	public static void main(String[] args)throws IOException, IllegalArgumentException {
		if(args.length<1) {
			System.err.println("** uso java Build <nombre archivo>**");
		}
		else {
			int edificios;
			Grafo grafo;
			Grafo grafoinv;
			Grafos grafos;
			grafos=CargarGrafos(args[0]);
			grafo=grafos.Damegrafo();
			grafoinv=grafos.Damegrafoinv();
			edificios=Conexas(grafo,grafoinv);
			System.out.println(edificios);
			
		}
	}
}

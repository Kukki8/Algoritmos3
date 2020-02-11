import java.util.LinkedList;
import java.util.Stack;

public class DFS {
	private boolean[] recorrido;
	LinkedList<Integer> Cola=new LinkedList<Integer>();
	public LinkedList<Integer> CrearDFS(int vertice, Grafo grafo, boolean[] visitados) {
		recorrido=visitados;
		if(recorrido[vertice]==true) {
			return Cola;
		}
		else {
			recorrido[vertice]=true;
			recursion(vertice,grafo);
			return Cola;	
		}
		
	}
	public void recursion(int vertice,Grafo grafo) {
		Cola.add(vertice);
		for(int i=0;i<grafo.Dametamañoadyacentes(vertice);i++) {
			if(recorrido[grafo.DamePos(vertice, i)]==false) {
				recorrido[grafo.DamePos(vertice, i)]=true;
				recursion(grafo.DamePos(vertice, i),grafo);
			}
		}
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
			if(recorrido2.size()>0) {
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
	
}
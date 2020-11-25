
import java.util.Arrays;


public class Bellman_Ford {
	public static int[][] re = new int[5][5];
	
	public static void main(String[]args) {
		Grafo g = inicGrafo();
		int[] r = bellmanFord(g,0);
		for(int i = 0; i < g.V; i++) {
			int[] filaR = bellmanFord(g,i);
			insertFila(filaR,i,re);
		}
		System.out.println(Arrays.deepToString(re));

	}

	public static int[] bellmanFord(Grafo g,int fuente) {

		int V = g.V;
		int A = g.A;
		int a[] = new int[V];
		int distanciasMinimas[] = inicializarEnMax(a);
		distanciasMinimas[fuente] = 0;
		for(int i = 1; i < V; i++ ) {
			for(int j = 0; j < A;j++) {
				int u = g.arcos[j].indiceFuente; 
				int v = g.arcos[j].indiceDestino; 
				int peso = g.arcos[j].peso; 

				if (distanciasMinimas[u] != -1 && distanciasMinimas[u] != Integer.MAX_VALUE && distanciasMinimas[u] + peso < distanciasMinimas[v]) { 
					distanciasMinimas[v] = distanciasMinimas[u] + peso; 
				}
			} 
		}

		for (int j = 0; j < A; ++j) { 
			int u = g.arcos[j].indiceFuente; 
			int v = g.arcos[j].indiceDestino; 
			int peso = g.arcos[j].peso; 
			if (distanciasMinimas[u] != -1 && distanciasMinimas[u] != Integer.MAX_VALUE && distanciasMinimas[u] + peso < distanciasMinimas[v]) { 
				System.out.println("El grafo contiene un ciclo negativo"); 
				return null; 
			} 
		} 
		return distanciasMinimas;
	}



	public static int[] inicializarEnMax(int a[]) {
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i++) {
			a[i] = max;
		}

		return a;
	}
	
	public static int[][] crearGrafo() {
		int [][] grafo = new int[5][5];


		grafo[0][0] = 0;
		grafo[0][1] = 90;
		grafo[0][2] = 80;
		grafo[0][3] = -1;
		grafo[0][4] = -1;
		grafo[1][0] = 15;
		grafo[1][1] = 0;
		grafo[1][2] = 69;
		grafo[1][3] = 48;
		grafo[1][4] = -1;
		grafo[2][0] = 91;
		grafo[2][1] = -1;
		grafo[2][2] = 0;
		grafo[2][3] = 12;
		grafo[2][4] = 39;
		grafo[3][0] = 78;
		grafo[3][1] = -1;
		grafo[3][2] = -1;
		grafo[3][3] = 0;
		grafo[3][4] = 36;
		grafo[4][0] = 26;
		grafo[4][1] = 12;
		grafo[4][2] = 39;
		grafo[4][3] = 33;
		grafo[4][4] = 0;

		return grafo;	
	}
	
	public static Grafo inicGrafo() {
		
		int[][] g = crearGrafo();
		int a = contarArcos(g);
		int v = g.length;
		Grafo grafo = new Grafo(v,a);
		int count  = 0;
		
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < v; j++) {
				if(g[i][j] != -1 && g[i][j]!= 0) {
					grafo.arcos[count].indiceFuente = i;
					grafo.arcos[count].indiceDestino = j;
					grafo.arcos[count].peso = g[i][j];	
					count++;	
				}
			}
		}
		return grafo;
	}
	
	public static int contarArcos(int a[][]) {
		int count = 0;
		for(int i = 0; i < a.length;i++) {
			for(int j = 0; j < a.length; j++) {
				if(a[i][j] != -1 || a[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void insertFila(int[] m,int numeroFila,int [][]g){

		for(int i = 0; i < g.length; i++) {
			for(int j = 0; j < g.length;j++) {
				if(i == numeroFila) {
					g[i][j] = m[j];
				}
			}
		} 
	}


}

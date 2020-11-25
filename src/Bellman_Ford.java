
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Bellman_Ford {
	public static ArrayList < ArrayList <Integer> > grafo = new ArrayList< ArrayList <Integer> >();
	
	//public static void main(String[]args) {
		//Grafo g = inicGrafo();
		//int[] r = bellmanFord(g,0);
		//for(int i = 0; i < g.V; i++) {
			//int[] filaR = bellmanFord(g,i);
			//insertFila(filaR,i,re);
		//}
		//System.out.println(Arrays.deepToString(re));

	//}
	
	public static void main(String[] args) throws Exception 
	{
		//lectura de datos adaptada del taller de ordenamiento del curso Diseño y análisis de algoritmos
		String file_name = args[0];	
		//String file_name = "distances.txt";
		grafo = new ArrayList< ArrayList <Integer> >();

		try (FileReader reader = new FileReader(file_name);
				BufferedReader in = new BufferedReader(reader)) 
		{ 
			String line = in.readLine();
			while(line != null) 
			{
				try 
				{
					ArrayList <Integer> adyacencias = new ArrayList <Integer>();
					String[] array = line.split("	");
					for(int j = 0; j < array.length; j++)
					{
						array[j] = array[j].trim();
						int a = Integer.parseInt(array[j]);
						adyacencias.add( a );
					}
					grafo.add(adyacencias);
				} 
				catch (Exception e) 
				{
					System.err.println("Can not read number from content: "+line);
					e.printStackTrace();
				}
				line = in.readLine();
			}
		}
		System.out.print("---- Este es el grafo seleccionado ----");
		System.out.print("\n");
		for(int i = 0; i < grafo.size(); i++)
		{
			for(int j = 0; j < grafo.get(i).size(); j++) 
				System.out.print(grafo.get(i).get(j) + " - ");
			System.out.print("\n");
		}
		int numVertices = grafo.size();
		long startTime = System.nanoTime();
		int[][] r = new int[grafo.size()][grafo.get(0).size()];
		
		// Pasamos el grafo a tipo Grafo
		Grafo s = inicGrafo(grafo);
		
		for(int f = 0; f < numVertices; f++) {
			int[] filaR = bellmanFord(s,f);
			insertFila(filaR,f,r);
		}
		long endTime = System.nanoTime();
		long seDemoro = endTime - startTime;
	
		System.out.print("---- Este es la matriz de distancias minima usando Bellman Ford ----");
		System.out.print("\n");
		for(int i = 0; i < r.length; i++)
		{
			for(int j = 0; j < r[i].length; j++) 
				System.out.print(r[i][j] + " - ");
			System.out.print("\n");
		}
		System.out.print("Se demoro: "+ seDemoro + " nanosegundos" );
		System.out.print("\n");
		System.out.print("Se demoro: "+ seDemoro/1000000 + " milisegundos" );
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
	
	public static Grafo inicGrafo(ArrayList < ArrayList <Integer> > g) {
		
		
		int a = contarArcos(g);
		int v = g.size();
		Grafo grafo = new Grafo(v,a);
		int count  = 0;
		
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < v; j++) {
				if(g.get(i).get(j) != -1 && g.get(i).get(j)!= 0) {
					grafo.arcos[count].indiceFuente = i;
					grafo.arcos[count].indiceDestino = j;
					grafo.arcos[count].peso = g.get(i).get(j);	
					count++;	
				}
			}
		}
		return grafo;
	}
	
	public static int contarArcos(ArrayList < ArrayList <Integer> > a) {
		int count = 0;
		for(int i = 0; i < a.size();i++) {
			for(int j = 0; j < a.size(); j++) {
				if(a.get(i).get(j) != -1 || a.get(i).get(j) != 0) {
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

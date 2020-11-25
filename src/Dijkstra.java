
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Dijkstra {
	
	public static ArrayList < ArrayList <Integer> > grafo = new ArrayList< ArrayList <Integer> >();
	//public static void main(String[]args) {
	//int[][] grafo = crearGrafo();
	//int numeroVertices = grafo.length;
	//for(int i = 0; i < numeroVertices; i++) {
	//int[] filaR = dijkstra(grafo,i);
	//insertFila(filaR,i,r);
	//}

	//System.out.println(Arrays.deepToString(r));

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
		
		for(int f = 0; f < numVertices; f++) {
			int[] filaR = dijkstra(grafo,f);
			insertFila(filaR,f,r);
		}
		long endTime = System.nanoTime();
		long seDemoro = endTime - startTime;
	
		System.out.print("---- Este es la matriz de distancias minima usando Dijkstra ----");
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


	public static int[] dijkstra(ArrayList < ArrayList <Integer> > grafo,int indiceInicio) {
		int numeroVertices = grafo.size() ;
		int max = Integer.MAX_VALUE; 
		int[] a = new int[numeroVertices];
		boolean[] b = new boolean[numeroVertices];
		int[] distanciasMinimas = llenarDeMax(a);
		boolean [] seleccionados = ponerEnFalse(b);
		int[] parent = new int[numeroVertices];

		parent[indiceInicio] = -1;
		distanciasMinimas[indiceInicio] = 0;

		for(int i = 0; i < numeroVertices - 1; i++) {
			int indiceMin = encontrarMinimoFila(distanciasMinimas,seleccionados);
			seleccionados[indiceMin] = true;

			for(int j = 0; j < numeroVertices; j++) {
				if(grafo.get(indiceMin).get(j) != 0 && grafo.get(indiceMin).get(j) != -1 && seleccionados[j] == false && distanciasMinimas[indiceMin] != max && (distanciasMinimas[indiceMin] + grafo.get(indiceMin).get(j) < distanciasMinimas[j])) {
					distanciasMinimas[j] = distanciasMinimas[indiceMin] + grafo.get(indiceMin).get(j);
					parent[j] = indiceMin;
				}
			}
		}





		//for (int j = 0; j < numeroVertices - 1; j++) {

		//int indiceMin = encontrarMinimoFila(fila);				
		//grafoRespuesta.put(indiceMin,fila);


		//}



		//int[]distanciasMinimas = new int[numeroVertices];


		//return null;
		return distanciasMinimas;

	}

	public static int encontrarMinimoFila(int[] a , boolean[] d) {
		int min = Integer.MAX_VALUE;
		int indice = Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i++) {
			if(a[i] < min && d[i] == false && a[i]!=-1) {
				min = a[i];
				indice = i;			
			}
		}
		return indice;
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
	public static int[][] crearGrafo(){
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

	public static int[] llenarDeMax(int a[]) {
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i++ ) {
			a[i] = max;
		}
		return a;
	}

	public static boolean[] ponerEnFalse(boolean a[]) {
		boolean b = false;
		for(int i = 0; i < a.length; i++ ) {
			a[i] = b;
		}
		return a;
	}


}


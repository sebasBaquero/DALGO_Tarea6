
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Floyd_Warshall {

	//public static void main(String[]args) {
	//int[][] grafo = crearGrafo();
	//int[][] grafoRespuesta = floyd_warshall(grafo);
	//System.out.println(Arrays.deepToString(grafoRespuesta));

	//}
	public static ArrayList < ArrayList <Integer> > grafo = new ArrayList< ArrayList <Integer> >();

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
		
		long startTime = System.nanoTime();
		

		
		 int[][] r = floyd_warshall(grafo);
			
		
		long endTime = System.nanoTime();
		long seDemoro = endTime - startTime;

		System.out.print("---- Este es la matriz de distancias minima usando Floyd Warshall ----");
		System.out.print("\n");
		for(int i = 0; i < r.length; i++)
		{
			for(int j = 0; j < r[i].length; j++) 
				System.out.print(r[i][j] + " - ");
			System.out.print("\n");
		}
		System.out.print("Se demoro: "+ seDemoro + " nanosegundos" );
	}


	public static int[][] floyd_warshall(ArrayList < ArrayList <Integer> > grafo){
		int numeroV = grafo.size();
		int [][]grafoSolucion = new int[numeroV][numeroV];
		for(int g = 0; g < numeroV; g++) {
			for(int h = 0; h < numeroV; h++) {
				grafoSolucion[g][h] = grafo.get(g).get(h);
			}
		}

		for(int k = 0; k < numeroV ; k++) {
			for(int i = 0; i < numeroV; i++) {
				for(int j = 0; j < numeroV; j++) {
					if(grafoSolucion[i][j]== -1) {
						grafoSolucion[i][j] = grafoSolucion[i][k] + grafoSolucion[k][j];
					}
					if(grafoSolucion[i][k] == -1 || grafoSolucion[k][j] == -1 ) {
						break;
					}else if(grafoSolucion[i][k] + grafoSolucion[k][j] < grafoSolucion[i][j]) {
						grafoSolucion[i][j] = grafoSolucion[i][k] + grafoSolucion[k][j];	
					}
				}
			}	
		}
		return grafoSolucion;
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


}


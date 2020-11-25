
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class BFS
{
	
	public static void main(String[] args) throws Exception 
	{
		//lectura de datos adaptada del taller de ordenamiento del curso Diseño y análisis de algoritmos
		String file_name = args[0];	
		//String file_name = "distances.txt";
		ArrayList < ArrayList <Integer> > grafo = new ArrayList< ArrayList <Integer> >();
		
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
		for(int i = 0; i < grafo.size(); i++)
		{
			for(int j = 0; j < grafo.get(i).size(); j++) 
				System.out.print(grafo.get(i).get(j) + " - ");
			System.out.print("\n");
		}
	}
	
	
	public static void BFS()
	{
		
	}
	
	
}
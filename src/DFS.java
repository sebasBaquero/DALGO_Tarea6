
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class DFS
{
	public static boolean es = false;
	
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
		
		//conseguir e imprimir resultados
		
		if( ! hayCiclo(grafo) ) System.out.println("No hay ciclos");
		else System.out.println("Sí hay ciclos");
			//ORDEN TOPOLÓGICO
	}
	
	
	public static boolean hayCiclo(ArrayList < ArrayList <Integer> > grafo)
	{
		for(int i = 0; i < grafo.size(); i++)
		{
			ArrayList <Integer> visitados = new ArrayList<Integer>();
			DFS(i, grafo, visitados);
			if( es ) return true;
		}
		return false;
	}
	
	public static void DFS(int actual, ArrayList < ArrayList <Integer> > grafo, ArrayList<Integer> visitados)
	{
		if(visitados.contains(actual)) { es = true; return;}
		visitados.add(actual);
		
		for(int i = 0; i < grafo.get(actual).size(); i++)
	    {
	        if(grafo.get(actual).get(i) > 0)            
	        {
	            DFS(i, grafo, visitados);
	        }
	    }
		
	}
	
	
}
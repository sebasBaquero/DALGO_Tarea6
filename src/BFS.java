
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
		
		//conseguir e imprimir resultados
		ArrayList < ArrayList <Integer> > respuesta = BFS(grafo);
		
		System.out.print("\n{ ");
		for(int i = 0; i < respuesta.size(); i++)
		{	
			System.out.print("{ ");
			for(int j = 0; j < respuesta.get(i).size(); j++) System.out.print(respuesta.get(i).get(j) + " ");
			System.out.print("} ");
		}
		System.out.print("}\n");
	}
	
	
	public static ArrayList < ArrayList <Integer> > BFS(ArrayList < ArrayList <Integer> > grafo)
	{
		ArrayList < ArrayList <Integer> > respuesta = new ArrayList< ArrayList <Integer> >();
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> unvisited = new HashSet<Integer>(); 
		for(int k = 0; k < grafo.size(); k++)
		{
			unvisited.add(k);
		}
		int n = grafo.size();
		
		
		for(int k = 0; k < n; k++)
		{
			if(unvisited.contains(k))
			{
				ArrayList <Integer> v = new ArrayList<Integer>();
				q.add(k);
				unvisited.remove(k);
				//System.out.println("añado" + k);
				while(! q.isEmpty() )
				{
					//System.out.println(unvisited.size() );
					int i = q.remove();
					//System.out.println("borro" + i);
					v.add(i);
					for(int j = 0; j < n; j++)
					{
						if( grafo.get(i).get(j) > 0)
						{
							if(unvisited.contains(j))
							{
								//System.out.println("añado" + j);
								q.add(j);
								unvisited.remove(j);
							}
						}
					}
				}
				respuesta.add(v);
			}
		}
		return respuesta;
	}
	
	
}
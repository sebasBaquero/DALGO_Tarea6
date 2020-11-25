
public class Grafo {
	
	class Arco{
		int indiceFuente;
		int indiceDestino;
		int peso;
		
		public Arco() {
			indiceFuente = 0;
			indiceDestino = 0;
			peso = 0;
		}	
	};
	
	int V,A;
	Arco arcos[];
	
	public Grafo(int v, int a) {
		V = v; 
        A = a; 
        arcos = new Arco[a]; 
        for (int i = 0; i < a; ++i) 
            arcos[i] = new Arco(); 
		
	}	
}


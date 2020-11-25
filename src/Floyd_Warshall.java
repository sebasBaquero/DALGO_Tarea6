
import java.util.Arrays;

public class Floyd_Warshall {

	public static void main(String[]args) {
		int[][] grafo = crearGrafo();
		int[][] grafoRespuesta = floyd_warshall(grafo);
		System.out.println(Arrays.deepToString(grafoRespuesta));

	}

	public static int[][] floyd_warshall(int [][] grafo){
		int numeroV = grafo.length;
		int [][]grafoSolucion = new int[numeroV][numeroV];
		for(int g = 0; g < numeroV; g++) {
			for(int h = 0; h < numeroV; h++) {
				grafoSolucion[g][h] = grafo[g][h];
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


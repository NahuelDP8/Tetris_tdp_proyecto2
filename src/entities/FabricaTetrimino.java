package entities;
import java.util.Random;

public class FabricaTetrimino {
	private Celda matriz[][];
	
	public FabricaTetrimino(Celda[][] matriz) {
		this.matriz = matriz;
	}
	
	public Tetrimino crearTetrimino() {
		Tetrimino devolverT=null;
		int valor = NumRandom(); 
		switch (valor) {
				case 1:  devolverT = new PiezaI(0,matriz[1][3], matriz[1][4], matriz[1][5], matriz[1][6]); break;
				case 2:  devolverT = new PiezaJ(0,matriz[1][3], matriz[2][3], matriz[2][4], matriz[2][5]); break;
				case 3:  devolverT = new PiezaL(0,matriz[1][3], matriz[1][4], matriz[1][5], matriz[0][5]); break;
				case 4:  devolverT = new PiezaO(0,matriz[1][4], matriz[1][5], matriz[2][4], matriz[2][5]); break;
				case 5:  devolverT = new PiezaZ(0,matriz[1][4], matriz[1][5], matriz[2][5], matriz[2][6]); break;
				case 6:  devolverT = new PiezaT(0,matriz[2][4], matriz[1][4], matriz[2][3], matriz[2][5]); break;
				case 7:  devolverT = new PiezaS(0,matriz[1][5], matriz[1][4], matriz[2][4], matriz[2][3]); break;
			}	
		return devolverT;
	}
	
	private int NumRandom() {
		int min = 1;
		int max = 8;
		Random random = new Random();
		//Nos devuelve un número aleatorio del 1 al 7
		int valor = random.nextInt(max - min) + min;
		return valor;
	}
}

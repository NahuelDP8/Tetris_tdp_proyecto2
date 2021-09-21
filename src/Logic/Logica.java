package Logic;
import entities.*;

import java.util.Random;
import java.util.ArrayList;

import GUI.*;

public class Logica {
	protected int puntos;
	protected Celda matrizCeldas[][] = new Celda[25][10] ;
	protected Tetrimino tetriminoActual;
	protected GUI miGui;
	protected Reloj miReloj;
	protected ImagenesEscaladas imagenes;
	
	public Logica(GUI miGui) {

		this.puntos = 0;

		imagenes=new ImagenesEscaladas();
		puntos = 0;
		this.miGui = miGui;
		for(int i = 0; i<=24; i++) {
			for(int j  = 0; j<=9; j++) {
				matrizCeldas [i][j] = new Celda(j,i, false, imagenes.getGrisVacio());  
			}
		}
		this.miReloj = new Reloj(this); 
		this.crearTetrimino();
	}
	
	private boolean verificarPerdidaJuego() {
		boolean hayDerrota = false;
		for(int j =0 ; j<=9 && !hayDerrota; j++) {
			if(matrizCeldas[3][j].getOcupado() == true)
				hayDerrota=true;
		}
		return hayDerrota;
	}
	
	private void crearTetrimino() { 
		//Antes de crear un tetrimino debemos verificar que no hayamos perdido 
		boolean perdimos = this.verificarPerdidaJuego();
		if(perdimos) {
			this.gameOver();
		}else { 
			int min = 1;
			int max = 7;
			Random random = new Random();
			//Nos devuelve un número aleatorio del 1 al 7
			int valor = random.nextInt(max + min) + min;	
			switch (valor) {
				case 1:  tetriminoActual = new PiezaI(0,matrizCeldas[1][3], matrizCeldas[2][4], matrizCeldas[2][5], matrizCeldas[2][6]);
				case 2:  tetriminoActual = new PiezaJ(0,matrizCeldas[1][3], matrizCeldas[2][3], matrizCeldas[2][4], matrizCeldas[2][5]);
				case 3:  tetriminoActual = new PiezaL(0,matrizCeldas[1][3], matrizCeldas[1][4], matrizCeldas[1][5], matrizCeldas[0][5]);
				case 4:  tetriminoActual = new PiezaO(0,matrizCeldas[1][4], matrizCeldas[1][5], matrizCeldas[2][4], matrizCeldas[2][5]);
				case 5:  tetriminoActual = new PiezaZ(0,matrizCeldas[1][4], matrizCeldas[1][5], matrizCeldas[2][5], matrizCeldas[2][6]);
				case 6:  tetriminoActual = new PiezaT(0,matrizCeldas[1][4], matrizCeldas[2][4], matrizCeldas[1][3], matrizCeldas[1][5]);
				case 7:  tetriminoActual = new PiezaS(0,matrizCeldas[1][5], matrizCeldas[1][4], matrizCeldas[2][4], matrizCeldas[2][3]);
			}
		}	
	} 
	
	private void gameOver() {
		//Debemos para el reloj y el corrimiento automático hacia abajo del tetrimino actual. 
		this.miReloj.gameOver();
		//Debemos avisarle a la gui que terminó el juego. 
		this.miGui.gameOver();
	}

	public void rotarTetrimino() {
		boolean verificado = false;	
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		////Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.rotar(ocupar);		
		verificado = verificarPosicionesFuturas(ocupar);
		if(verificado) {
			realizarMovimientos(ocupar, desocupar);
		}
	}
	
	public void moverDerecha() {
		boolean verificado = false;
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		////Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.moverDerecha(ocupar);
		
		verificado = verificarPosicionesFuturas(ocupar);
		if(verificado) {
			realizarMovimientos(ocupar, desocupar);
		}
	}
	
	public void moverIzquierda() {
		boolean verificado = false;
		
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		////Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.moverIzquierda(ocupar);
		
		verificado = verificarPosicionesFuturas(ocupar);
		if(verificado) {
			realizarMovimientos(ocupar, desocupar);
		}
	}
	
	public void moverAbajo() {
		boolean verificado = false;
		
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		////Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.moverAbajo(ocupar);
		
		verificado = verificarPosicionesFuturas(ocupar);
		if(verificado) {
			realizarMovimientos(ocupar, desocupar);
		} else{
			int lineasCompletas = this.buscarLineasCompletas();
			if(lineasCompletas!=0) {
				this.sumarPuntaje(lineasCompletas);
			}
		}
	}
	
	private void sumarPuntaje(int lineasCompletas) {
		if(lineasCompletas == 1) {
			puntos = puntos + 100;
		}else if(lineasCompletas ==2) {
			puntos = puntos + 200;
		}else if(lineasCompletas == 3) {
			puntos = puntos + 500;
		}else {
			puntos = puntos + 800;
		}
	}

	private boolean verificarPosicionesFuturas(ArrayList<PairTupla> futuras) {
		boolean valida = true;
		int size = futuras.size();
		for(int i = 0; i<size && valida; i++) {
			PairTupla par = futuras.get(i);
			int x = par.getX();
			int y = par.getY();
			if(y>24 || y<0 || x>9 || x<0)
				valida=false;
			if(valida) {
				Celda aux = matrizCeldas[y][x];
				if(aux.getOcupado() == true)
					valida=false;
			}
		}
		return valida;
	}
	
	private void realizarMovimientos( ArrayList<PairTupla> ocupar, ArrayList<PairTupla> desocupar ) {
		boolean encontrado = false;
		//Obtenemos la lista de celdas de nuestro tetrimino actual.
		ArrayList<Celda> celT = tetriminoActual.getCeldas();
		
		for(int i =0; i < desocupar.size(); i++){
			PairTupla pOcupar = ocupar.get(i);
			PairTupla pDesocupar = desocupar.get(i);
			Celda cOcupar = matrizCeldas[pOcupar.getY()][pOcupar.getX()];
			Celda cDesocupar = matrizCeldas[pDesocupar.getY()][pDesocupar.getX()];
			for(int j = 0; j<=4  && !encontrado; j++) {
				Celda cActual  = celT.get(j);
				if(cActual == cDesocupar) {
					encontrado = true;
					cActual.actualizarImagen(imagenes.getGrisVacio());
					this.actualizarCelda(cActual);
					celT.set(j, cOcupar);
					cOcupar.actualizarImagen(tetriminoActual.getPhoto());
					cOcupar.setOcupado(true);
					this.actualizarCelda(cOcupar);
				}
			}
			encontrado = false;
		}
		
	}
	private void actualizarCelda(Celda celda) {
		miGui.actualizarCelda(celda.getY(), celda.getX(), celda.getImagen());
	}
	
	private int buscarLineasCompletas() {
		int cantLineasCompletas = 0; 
		boolean hayUnaVacia = false;
		for(int i =0; i < 21 ; i++ ) {
			if(matrizCeldas[i][0].getOcupado()==true) { 
				for( int j = 1; j < 10 && !hayUnaVacia;j++ ) {
					if(matrizCeldas[i][j].getOcupado() == false)
						hayUnaVacia=true;
					//significa que ya llegamos al final de la fila y vemos que toda está completa 
					if(j==9) {
						this.modificarMatrizLineaLlena(i);
						cantLineasCompletas++;
					}
				}
			}
			hayUnaVacia=false;
		}
		return cantLineasCompletas;
	}

	public void actualizarReloj() {
		this.miGui.actualizarReloj(miReloj.getMinutos(), miReloj.getSegundos());
	}
	
	private void modificarMatrizLineaLlena(int y) {
		boolean detener = false;
		//contador de celdas no ocupadas
		int celdasNoOcupadas = 0;
		for(int i =y; i>=0 && detener; y--) {
			//caso excepcional en el que queremos setear lo que está en la fila de arriba con respecto al comienzo de la matriz
			if(i == 0) {
				this.liberarLinea(0);
			}
			for(int j =0; j<=9;j++) {
				Celda arriba = matrizCeldas[i-1][j];
				Celda actual = matrizCeldas[i][j];
				actual.setOcupado(arriba.getOcupado());
				actual.actualizarImagen(arriba.getImagen());
				this.actualizarCelda(actual);
				if(actual.getOcupado()==false) {
					celdasNoOcupadas++;
				}
			}
			//En caso de que la última fila que hayamos modificado represente una linea vacía, entonces no necesitamos seguir recorriendo la matriz.
			//lo que restan son todas celdas no ocupadas
			if(celdasNoOcupadas ==10)
				detener =true;
		}
	}

	private void liberarLinea(int i) {
		for(int j = 0; j<=9; j++) {
			Celda aux = matrizCeldas[i][j];
			aux.actualizarImagen(imagenes.getGrisVacio());
			aux.setOcupado(false);
			this.actualizarCelda(aux);
		}
	}
	
}

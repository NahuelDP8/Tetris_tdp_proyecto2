package Logic;
import entities.*;
import java.util.ArrayList;

import GUI.*;

public class Logica {
	protected final int MOVER_ABAJO = 0;
	protected final int MOVER_IZQUIERDA = 1;
	protected final int MOVER_DERECHA = 2;
	protected final int MOVER_ROTAR = 3;
	
	protected Celda matrizCeldas[][] = new Celda[25][10] ;// Matriz de 25x10 celdas
	protected Tetrimino tetriminoActual;
	protected GUI miGui;
	protected Reloj miReloj;
	protected ImagenesEscaladas imagenes;
	protected Tetrimino tetriminoSiguiente;
	protected boolean perdiste=false;
	protected int puntos;
	protected FabricaTetrimino miFabrica;
	
	public Logica(GUI miGui) {
		imagenes=new ImagenesEscaladas();
		for(int i = 0; i<=24; i++) {
			for(int j  = 0; j<=9; j++) {
				matrizCeldas [i][j] = new Celda(j,i, false, imagenes.getGrisVacio());  
				matrizCeldas [i][j].setOcupado(false);
			}
		
		}
		this.miGui = miGui;
		this.puntos = 0;
		this.miFabrica = new FabricaTetrimino(this);
		this.tetriminoActual= crearTetrimino();
		this.tetriminoSiguiente= crearTetrimino();
		this.miGui.actualizarTetriminoSiguiente(tetriminoSiguiente.getMiImagen());
		this.miReloj = new Reloj(this); 
	}
	
	public Celda getCelda(int fila, int columna) {
		return matrizCeldas[fila][columna];
	}
	public int getAbajo() {
		return MOVER_ABAJO;
	}
	
	public int getIzquierda() {
		return MOVER_IZQUIERDA;
	}
	
	public int getDerecha() {
		return MOVER_DERECHA;
	}
	
	public int getRotar() {
		return MOVER_ROTAR;
	}
	
	public synchronized void operarJuego(int operacion) {
		switch(operacion) {
			case MOVER_ABAJO: {moverAbajo(); break;}
			case MOVER_IZQUIERDA: {moverIzquierda(); break;}
			case MOVER_DERECHA: {moverDerecha(); break;}
			case MOVER_ROTAR: {rotarTetrimino(); break;}
		}
	}
	
	private Tetrimino crearTetrimino() { 
		Tetrimino devolverT=null;
		//Antes de crear un tetrimino debemos verificar que no hayamos perdido
		boolean perdimos = this.verificarPerdidaJuego();
		if(perdimos) {
			gameOver();
		}else { 
		//retornamos un nuevo tetrimino a partir de nuestra fábrica
			devolverT = miFabrica.crearTetrimino();
		}	
		return devolverT;
		
	}
	
	private boolean verificarPerdidaJuego() {
		boolean hayDerrota = false;
		//La matriz de celdas habilitada para el juego comienza a partir de la fila 4
		//Por ende debemos corroborar que la primer fila inhabilitada nunca tenga una celda ocupada mientras se esté jugando
		for(int j =0 ; j<=9 && !hayDerrota; j++) {
			if(matrizCeldas[3][j].getOcupado() == true)
				hayDerrota=true;
		}
		return hayDerrota;
	} 
	
	private void gameOver() {
		perdiste=true;
		//Debemos avisarle a la gui que terminó el juego.
		miGui.gameOver();
		//Debemos parar el reloj y el corrimiento automático hacia abajo del tetrimino actual. 
		miReloj.gameOver();
	}
	
	public void rotarTetrimino() {
		boolean verificado = false;	
		int rotacion;
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		//Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.rotar(ocupar);		
		verificado = verificarPosicionesFuturas(desocupar);
		if(verificado) {
			realizarMovimientos(desocupar, ocupar);
			rotacion = tetriminoActual.getRotacion();
			if(rotacion == 270)
				tetriminoActual.setRotacion(0);
			else
				tetriminoActual.setRotacion(rotacion + 90);
					
		}
	}
	
	public void moverDerecha() {
		boolean verificado = false;
		//Creamos una lista de aquellas posiciones NUEVAS que pasarían a ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> ocupar =  new ArrayList<PairTupla> (); 
		//Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
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
		//Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
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
		//Creamos una lista de aquellas posiciones ANTIGUAS que dejarían de ser ocupadas por el tetrimino actual
		ArrayList<PairTupla> desocupar = tetriminoActual.moverAbajo(ocupar);
		
		verificado = verificarPosicionesFuturas(ocupar);	
		if(verificado) {
			realizarMovimientos(ocupar, desocupar);
		} else{
			//Significa que no podemos movernos más hacia abajo.
			int lineasCompletas = this.buscarLineasCompletas();
			if(lineasCompletas!=0) {
				this.sumarPuntaje(lineasCompletas);
				this.miGui.actualizaPuntaje(this.puntos);			}
				tetriminoActual=tetriminoSiguiente;
				tetriminoSiguiente= crearTetrimino();
			if(!perdiste) {
				miGui.actualizarTetriminoSiguiente(tetriminoSiguiente.getMiImagen());
			}
		}
	}
	
	private void sumarPuntaje(int lineasCompletas) {
		//Suma puntaje segun cantidad de lineas eliminadas.
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
			//Controla que no nos estemos yendo fuera de la dimensiones de nuestra matriz
			if(y>24 || y<0 || x>9 || x<0)
				valida=false;
			if(valida) {
				Celda aux = matrizCeldas[y][x];
				//Vemos que la celda que necesitamos ocupar esté liberada
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
			//Tanto la lista ocupar como desocupar tendrán la misma cantidad de elementos. 
			PairTupla pOcupar = ocupar.get(i);
			PairTupla pDesocupar = desocupar.get(i);
			Celda cOcupar = matrizCeldas[pOcupar.getY()][pOcupar.getX()];
			Celda cDesocupar = matrizCeldas[pDesocupar.getY()][pDesocupar.getX()];
			for(int j = 0; j<4  && !encontrado; j++) {
				Celda cActual  = celT.get(j);
				//Cuando encontremos una de las celdas a desocupar en la lista actual de celdas del tetrimino actual
				//debemos realizar los cambios pertinentes con respecto a la celda (intercambiandola por alguna de las que vamos a ocupar),
				// la lógica y notificar a la GUI. 
				if(cActual == cDesocupar) {
					encontrado = true;
					cActual.actualizarImagen(imagenes.getGrisVacio());
					//llamamos a la gui
					this.actualizarCelda(cActual);
					celT.set(j, cOcupar);
					cOcupar.actualizarImagen(tetriminoActual.getPhoto());
					cOcupar.setOcupado(true);
					cDesocupar.setOcupado(false);
					//llamaos a la gui 
					this.actualizarCelda(cOcupar);
				}
			}
			encontrado = false;
		}
		
	}
	private void actualizarCelda(Celda celda) {
		//Actualizamos la gui en la posición que corresponda. 
		this.miGui.actualizarCelda(celda.getY(), celda.getX(), celda.getImagen());
	}
	
	private int buscarLineasCompletas() {
		int cantLineasCompletas = 0; 
		boolean hayUnaVacia = false;
		for(int i =4; i < 25 ; i++ ) {
			//Con que haya una celda vacía, ya podemos descartar esta línea.
			if(matrizCeldas[i][0].getOcupado()==true) { 
				for( int j = 1; j < 10 && !hayUnaVacia;j++ ) {
					if(matrizCeldas[i][j].getOcupado() == false) 
						hayUnaVacia=true;
					else {
					//significa que ya llegamos al final de la fila y vemos que toda está completa 
						if(j==9) {
							this.modificarMatrizLineaLlena(i);
							cantLineasCompletas++;
						}
					}
				}
			}
			hayUnaVacia=false;
		}
		return cantLineasCompletas;
	}

	public void actualizarReloj() {
		//Llamamos a la Gui para que el reloj se actualice. 
		this.miGui.actualizarReloj(miReloj.getMinutos(), miReloj.getSegundos());
	}
	
	private void modificarMatrizLineaLlena(int y) {
		boolean detener = false;
		//contador de celdas no ocupadas
		int celdasNoOcupadas = 0;
		for(int i =y; i>=4 && !detener; i--) {
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
				if(arriba.getOcupado()==false) {
					celdasNoOcupadas++;
				}
			}
			//En caso de que la última fila que hayamos modificado represente una linea vacía, entonces no necesitamos seguir recorriendo la matriz.
			//lo que restan son todas celdas no ocupadas
			if(celdasNoOcupadas ==10)
				detener=true;
		}
	}

	private void liberarLinea(int i) {
		//Hacemos las modificaciones necesarias para que una línea quede completamente desocupada. 
		for(int j = 0; j<=9; j++) {
			Celda aux = matrizCeldas[i][j];
			aux.actualizarImagen(imagenes.getGrisVacio());
			aux.setOcupado(false);
			this.actualizarCelda(aux);
		}
	}
	
}

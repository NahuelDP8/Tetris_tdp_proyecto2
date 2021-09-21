package Logic;
import entities.*;

import java.awt.Image;
//import java.util.Random;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import GUI.*;

public class Logica {
	protected int puntos;
	protected Celda matrizCeldas[][] = new Celda[21][10] ;
	protected Tetrimino tetriminoActual;
	protected GUI miGui;
	protected Reloj miReloj;
	
	private static ImageIcon celdaDesocupadaImgSinEscalar =new ImageIcon(Logica.class.getResource("/imagenes/grisVacio.png"));
	private static Image EscalarFoto = celdaDesocupadaImgSinEscalar.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
	private static ImageIcon celdaDesocupadaImg = new ImageIcon(EscalarFoto);
	
	public Logica(GUI miGui) {
		puntos = 0;
		//this.crearTetrimino();
		this.miGui = miGui;
		for(int i = 0; i<=20; i++) {
			for(int j  = 0; j<=9; j++) {
				matrizCeldas [i][j] = new Celda(j,i, false, celdaDesocupadaImg);  
			}
		}
	}
	
	/*private void crearTetrimino() { 
		int min = 1;
		int max = 7;
		Random random = new Random();
		//Nos devuelve un número aleatorio del 1 al 7
		int valor = random.nextInt(max + min) + min;
		
		switch (valor) {
			case 1:  tetriminoActual = new PiezaI(0,matrizCeldas[3][0], matrizCeldas[4][0], matrizCeldas[5][0], matrizCeldas[6][0]);
			case 2:  tetriminoActual = new PiezaJ(0,matrizCeldas[0][4], matrizCeldas[1][4], matrizCeldas[2][4], matrizCeldas[3][4]);
			case 3: 
		}
	} */
	
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
			if(y>20 || y<0 || x>9 || x<0)
				valida=false;
			if(valida) {
				Celda aux = matrizCeldas[par.getX()][par.getY()];
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
					cActual.actualizarImagen(celdaDesocupadaImg);
					this.actualizarCelda(cActual);
					celT.set(j, cOcupar);
					cOcupar.actualizarImagen(tetriminoActual.getPathPhoto());
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
			aux.actualizarImagen(celdaDesocupadaImg);
			aux.setOcupado(false);
			this.actualizarCelda(aux);
		}
	}
	
}

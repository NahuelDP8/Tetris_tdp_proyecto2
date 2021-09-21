package entities;

import Logic.*;

public class Reloj implements Runnable {
	private int minutos, segundos, pausa;
	private Thread hiloTiempo, hiloBajar;
	private Logica miLogica;
	private final int minPausa = 200;
	

	public Reloj(Logica logic) {
		miLogica = logic;
		minutos = 0;
		segundos = 0;
		pausa = 500;
		//Hilo que actualiza el reloj.
		hiloTiempo = new Thread(this);
		hiloTiempo.start();
		
		//Hilo que notifica que se debe mover hacia abajo.
		hiloBajar = new Thread(this);
		hiloBajar.start();
	}

	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		//Actualiza el reloj
		while (ct == hiloTiempo) {
			try {
				Thread.sleep(1000);
				segundos += 1;
				if(segundos == 60) {
					minutos++;
					segundos = 0;
				}
				if(segundos % 5 == 0 && pausa >= minPausa)
					pausa -= 50;
					
				miLogica.actualizarReloj();
			} catch(InterruptedException e) {}
		}
		//Baja el tetrimino 
		while (ct == hiloBajar) {
			try {
				Thread.sleep(pausa);
				miLogica.moverAbajo();
			} catch(InterruptedException e) {}
		}
		
	}
	
	public int getMinutos() {
		return minutos;
	}
	public int getSegundos() {
		return segundos;
	}
	public void gameOver() {
		hiloBajar.stop();
		hiloTiempo.stop();
	}

}
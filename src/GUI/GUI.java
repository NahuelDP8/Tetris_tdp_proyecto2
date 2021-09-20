package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Logic.Logica;

public class GUI {
	
	private JFrame frame;
	private Logica log;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		log = new Logica(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void captarMovimientoIzq() {}
	
	public void captarMovimientoDer() {}
	
	public void captarOpcionRotar() {}
	
	public void actualizarCelda(int fila, int columna, String imagen) {}

	public void gameOver() {}
	
	public void actualizaPuntaje(int puntaje) {}

	public void actualizarReloj() {}

	
	
}

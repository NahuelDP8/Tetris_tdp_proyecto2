package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Logic.Logica;

public class GUI {
	
	private JFrame frame;
	private Logica log;
	private JLabel [][] labels = new JLabel[21][10];
	
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
		frame.setBounds(100,100,300,630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel label;
		
		
		for(int i = 0; i<labels[0].length;i++) {
			for(int j = 0; j<labels.length;j++) {
				label = new JLabel(Integer.toString(i)+'-'+Integer.toString(j));
				label.setBounds(i*30, j*30, 30, 30);
				frame.getContentPane().add(label);
				labels[j][i] = label;
			}
		}
		
	}
	
	public void captarMovimientoIzq() {}
	
	public void captarMovimientoDer() {}
	
	public void captarOpcionRotar() {}
	
	public void actualizarCelda(int fila, int columna, String imagen) {}

	public void gameOver() {}
	
	public void actualizaPuntaje(int puntaje) {}

	public void actualizarReloj(int minutos, int segundos) {}
	
}

package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Logic.Logica;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI implements KeyListener {
	
	private JFrame frame;
	private Logica log;
	private JLabel [][] labels = new JLabel[25][10];
	private JLabel JLTiempo;
	private JLabel JLPuntaje;
	
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
		frame.setBounds(400,60,400,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Panel donde se crea la matriz
		JPanel panelMatriz = new JPanel();
		panelMatriz.setBounds(50, 50, 250, 625);
		frame.getContentPane().add(panelMatriz);
		panelMatriz.setLayout(null);
		
		JPanel PTiempo = new JPanel();
		PTiempo.setBounds(0, 0, 250, 101);
		panelMatriz.add(PTiempo);
		PTiempo.setLayout(null);
		
		JLTiempo = new JLabel("00:00");
		JLTiempo.setBounds(60, 28, 80, 33);
		PTiempo.add(JLTiempo);
		panelMatriz.addKeyListener(this);
		JLabel label;
		
		for(int i = 0; i<labels[0].length;i++) {
			for(int j = 0; j<labels.length;j++) {
				label = new JLabel(i+"-"+j);
				label.setBounds(i*25, j*25, 25, 25);
				ImageIcon vacioFoto =new ImageIcon(GUI.class.getResource("/imagenes/grisVacio.png"));
				Image EscalarFoto = vacioFoto.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon vacioFotoEscalada = new ImageIcon(EscalarFoto);
				label.setIcon(vacioFotoEscalada);
				panelMatriz.add(label);
				labels[j][i] = label;
			}
		}
		
	}	
	public void captarMovimientoIzq() {
		log.moverIzquierda();
	}
	
	
	public void captarMovimientoDer() {
		log.moverDerecha();
	}
	
	public void captarOpcionRotar() {
		log.rotarTetrimino();
	}
	
	//escalamos en las mismas celdas
	public void actualizarCelda(int fila, int columna, ImageIcon imagen) {
		labels[fila][columna].setIcon(imagen);
	}

	public void gameOver() {
		//log.gameOver();
		
	}
	
	public void actualizaPuntaje(int puntaje) {
		// JLPuntaje crearlo y acomodarlo
	}
	

	public void actualizarReloj(int min, int seg) {
		JLTiempo.setText(min+":"+seg);
	}


	public void keyTyped(KeyEvent e) {
	}

	//se capta cuando se presionan las teclas izq,der,arriba
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			captarMovimientoIzq();
		}else
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				captarMovimientoDer();
			}else
				if((e.getKeyCode() == KeyEvent.VK_UP)) {
					captarOpcionRotar();
				}
	}

	public void keyReleased(KeyEvent e) {
	}
}

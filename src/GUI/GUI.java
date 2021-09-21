package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Logic.Logica;
import entities.Celda;
import entities.PiezaJ;
import entities.PiezaL;
import entities.Tetrimino;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI implements KeyListener {
	
	private JFrame frame;
	private Logica log;
	private JLabel [][] labels = new JLabel[25][10];
	private JLabel JLTiempo;
	private JLabel JLPuntaje;
	private JPanel PTiempo;
	private JPanel PMatriz;
	private JPanel PPerdiste;
	private JLabel JLPerdiste;
	
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
		
		PPerdiste = new JPanel();
		PPerdiste.setBackground(new Color(0, 0, 0));
		PPerdiste.setBounds(10, 247, 346, 133);
		frame.getContentPane().add(PPerdiste);
		PPerdiste.setLayout(null);
		
		
	
		JLPerdiste = new JLabel("Lo siento has sido derrotado");
		JLPerdiste.setForeground(new Color(0, 128, 0));
		JLPerdiste.setBackground(new Color(0, 128, 0));
		JLPerdiste.setToolTipText("");
		JLPerdiste.setHorizontalAlignment(SwingConstants.CENTER);
		JLPerdiste.setFont(new Font("Stencil", Font.PLAIN, 20));
		JLPerdiste.setBounds(0, 10, 354, 123);
		PPerdiste.add(JLPerdiste);
		
		//Panel donde se crea la matriz
		PMatriz = new JPanel();
		PMatriz.setBounds(50, 50, 250, 625);
		frame.getContentPane().add(PMatriz);
		PMatriz.setLayout(null);
		PMatriz.addKeyListener(this);
	
		
		PTiempo = new JPanel();
		PTiempo.setBackground(Color.WHITE);
		PTiempo.setBounds(0, 0, 250, 101);
		PMatriz.add(PTiempo);
		PTiempo.setLayout(null);
		
		
		JLTiempo = new JLabel("00:00");
		JLTiempo.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 48));
		JLTiempo.setBounds(31, 10, 209, 52);
		PTiempo.add(JLTiempo);
		
		JLPuntaje = new JLabel("Puntaje: 0");
		JLPuntaje.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		JLPuntaje.setBounds(10, 66, 230, 25);
		PTiempo.add(JLPuntaje);
		
		
		JLabel label;
		for(int i = 0; i<labels[0].length;i++) {
			for(int j = 0; j<labels.length;j++) {
				label = new JLabel("");
				label.setBounds(i*25, j*25, 25, 25);
				ImageIcon vacioFoto =new ImageIcon(GUI.class.getResource("/imagenes/grisVacio.png"));
				Image EscalarFoto = vacioFoto.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon vacioFotoEscalada = new ImageIcon(EscalarFoto);
				label.setIcon(vacioFotoEscalada);
				PMatriz.add(label);
				labels[j][i] = label;
			}
		}
		PPerdiste.setVisible(false);
		
	}	
	public void captarMovimientoIzq() {
		log.moverIzquierda();
		JLPuntaje.setText("vije cuidado a la IZQUIERDA");
	}
	
	
	public void captarMovimientoDer() {
		log.moverDerecha();
		JLPuntaje.setText("DERECHA PAPA ");
	}
	
	public void captarOpcionRotar() {
		log.rotarTetrimino();
	}
	
	//actualiza la imagen
	public void actualizarCelda(int fila, int columna, ImageIcon imagen) {
		labels[fila][columna].setIcon(imagen);
	}

	public void gameOver() {
		PPerdiste.setVisible(true);
	}
	
	public void actualizaPuntaje(int puntaje) {
		JLPuntaje.setText("Puntaje= "+puntaje);
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

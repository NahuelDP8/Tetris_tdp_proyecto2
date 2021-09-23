package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Logic.Logica;
import entities.ImagenesEscaladas;
import entities.PiezaI;
import entities.PiezaJ;
import entities.PiezaL;
import entities.PiezaO;
import entities.PiezaS;
import entities.PiezaT;
import entities.PiezaZ;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GUI{
	
	private JFrame frame;
	private Logica log;
	private JLabel [][] labels = new JLabel[25][10];
	private JLabel JLTiempo;
	private JLabel JLPuntaje;
	private JPanel PTiempo_Puntos_TetriminoS;
	private JPanel PMatriz;
	private JPanel PPerdiste;
	private JLabel JLPerdiste;
	private boolean jugando;
	private JLabel JLTetriminoSiguiente;
	
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
		//Listener Eventos
		EventoDeTeclado tecla=new EventoDeTeclado();
		frame = new JFrame();
		frame.setBounds(400,60,400,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(tecla);
		frame.getContentPane().setLayout(null);
		
		jugando = true;
	
		//Panel donde se crea la matriz
		PMatriz = new JPanel();
		PMatriz.setBounds(20, 25, 356, 678);
		frame.getContentPane().add(PMatriz);
		PMatriz.setLayout(null);
	
		PTiempo_Puntos_TetriminoS = new JPanel();
		PTiempo_Puntos_TetriminoS.setBackground(new Color(0, 0, 0));
		PTiempo_Puntos_TetriminoS.setBounds(0, 0, 356, 100);
		PMatriz.add(PTiempo_Puntos_TetriminoS);
		PTiempo_Puntos_TetriminoS.setLayout(null);
		
		
		JLTiempo = new JLabel("00:00");
		JLTiempo.setForeground(new Color(0, 128, 0));
		JLTiempo.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 48));
		JLTiempo.setBounds(10, 10, 203, 52);
		PTiempo_Puntos_TetriminoS.add(JLTiempo);
		
		JLPuntaje = new JLabel("Puntaje: 0");
		JLPuntaje.setForeground(new Color(0, 128, 0));
		JLPuntaje.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		JLPuntaje.setBounds(10, 66, 230, 25);
		PTiempo_Puntos_TetriminoS.add(JLPuntaje);
		
			//necesitamos que el random aparezca antes, osea crear un metodo para el random
			//y tener q imagen y tetrimino siguiente viene
			JLTetriminoSiguiente = new JLabel("");
			JLTetriminoSiguiente.setBounds(223, 0, 133, 100);
			PTiempo_Puntos_TetriminoS.add(JLTetriminoSiguiente);
			JLTetriminoSiguiente.setForeground(new Color(0, 128, 0));
			JLTetriminoSiguiente.setBackground(Color.WHITE);
			
			
			PPerdiste = new JPanel();
			PPerdiste.setBounds(0, 335, 356, 142);
			PMatriz.add(PPerdiste);
			PPerdiste.setBackground(new Color(0, 0, 0));
			PPerdiste.setLayout(null);
			
			JLPerdiste = new JLabel("Lo siento has sido derrotado");
			JLPerdiste.setBounds(10, 10, 336, 123);
			PPerdiste.add(JLPerdiste);
			JLPerdiste.setForeground(new Color(0, 128, 0));
			JLPerdiste.setBackground(new Color(255, 255, 255));
			JLPerdiste.setToolTipText("");
			JLPerdiste.setHorizontalAlignment(SwingConstants.CENTER);
			JLPerdiste.setFont(new Font("Stencil", Font.PLAIN, 20));
			PPerdiste.setVisible(false);
		
		
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
		
	}	
	public void captarMovimientoIzq() {
		log.moverIzquierda();
		
	}
	
	
	public void captarMovimientoDer() {
		log.moverDerecha();
		
	}
	
	// gui ya tiene que saber cual es el proximo, el random lo tenemos que hacer antes
	public void actualizarTetriminoSiguiente(ImageIcon imagen){
		JLTetriminoSiguiente.setIcon(imagen);
	}
	
	public void captarMovimientoAbajo() {
		log.moverAbajo();
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
		jugando = false;
	}
	
	public void actualizaPuntaje(int puntaje) {
		JLPuntaje.setText("Puntaje= "+puntaje);
	}
	

	public void actualizarReloj(int min, int seg) {
		JLTiempo.setText(min+":"+seg);
	}



	
	
	class EventoDeTeclado implements KeyListener{
		public void keyTyped(KeyEvent e) {
			
			}
			
		
		
	//se capta cuando se presionan las teclas izq,der,arriba
		public void keyPressed(KeyEvent e) {
			if(jugando) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					captarMovimientoIzq();
				}else
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						captarMovimientoDer();
					}else
						if((e.getKeyCode() == KeyEvent.VK_UP)) {
							captarOpcionRotar();
						}else {
							if(e.getKeyCode() == KeyEvent.VK_DOWN) {
								//captarAbajoNormalizarPausa();
								captarMovimientoAbajo();
							}
						}
							
			}
		}

		public void keyReleased(KeyEvent e) {
						
		}
	}
}
	


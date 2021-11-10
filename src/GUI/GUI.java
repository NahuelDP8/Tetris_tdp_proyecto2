package GUI;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Logic.Logica;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI{
	
	public JFrame frmTetris;
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
		EventoDeTeclado tecla=new EventoDeTeclado();
		frmTetris = new JFrame();
		frmTetris.setTitle("Tetris");
		frmTetris.getContentPane().setBackground(new Color(0, 0, 0));
		frmTetris.setBounds(400,60,351,722);
		frmTetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTetris.addKeyListener(tecla);
		frmTetris.getContentPane().setLayout(null);
		frmTetris.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Imagenes/naranja.png")));
		
		jugando = true;
			
		PPerdiste = new JPanel();
		PPerdiste.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PPerdiste.setForeground(Color.WHITE);
		PPerdiste.setBounds(0, 335, 335, 142);
		frmTetris.getContentPane().add(PPerdiste);
		PPerdiste.setBackground(new Color(0, 0, 0));
		PPerdiste.setLayout(null);
		
		JButton btnNewButton = new JButton("Volver al menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIMenu Nframe = new GUIMenu();
							Nframe.setVisible(true);
							frmTetris.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(109, 87, 118, 23);
		PPerdiste.add(btnNewButton);
		
		JLPerdiste = new JLabel("JUEGO TERMINADO");
		JLPerdiste.setBounds(10, 0, 320, 112);
		PPerdiste.add(JLPerdiste);
		JLPerdiste.setForeground(Color.WHITE);
		JLPerdiste.setBackground(new Color(255, 255, 255));
		JLPerdiste.setToolTipText("");
		JLPerdiste.setHorizontalAlignment(SwingConstants.CENTER);
		JLPerdiste.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		PPerdiste.setVisible(false);
	
		PMatriz = new JPanel();
		PMatriz.setBorder(null);
		PMatriz.setBackground(new Color(0, 0, 0));
		PMatriz.setBounds(42, 27, 250, 630); 
		frmTetris.getContentPane().add(PMatriz);
		PMatriz.setLayout(null);
		
		PTiempo_Puntos_TetriminoS = new JPanel();
		PTiempo_Puntos_TetriminoS.setBounds(0, 0, 250, 100);
		PMatriz.add(PTiempo_Puntos_TetriminoS);
		PTiempo_Puntos_TetriminoS.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PTiempo_Puntos_TetriminoS.setBackground(new Color(0, 0, 0));
		PTiempo_Puntos_TetriminoS.setLayout(null);
		
		
		JLTiempo = new JLabel("00:00");
		JLTiempo.setVerticalAlignment(SwingConstants.TOP);
		JLTiempo.setForeground(Color.WHITE);
		JLTiempo.setFont(new Font("Yu Gothic Light", Font.PLAIN, 48));
		JLTiempo.setBounds(10, 10, 203, 52);
		PTiempo_Puntos_TetriminoS.add(JLTiempo);
		
		JLPuntaje = new JLabel("Puntos: 0");
		JLPuntaje.setVerticalAlignment(SwingConstants.TOP);
		JLPuntaje.setForeground(Color.WHITE);
		JLPuntaje.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		JLPuntaje.setBounds(10, 66, 230, 25);
		PTiempo_Puntos_TetriminoS.add(JLPuntaje);
		
		JLTetriminoSiguiente = new JLabel("");
		JLTetriminoSiguiente.setBounds(180, 25, 60, 48);
		PTiempo_Puntos_TetriminoS.add(JLTetriminoSiguiente);
		JLTetriminoSiguiente.setForeground(new Color(0, 128, 0));
		JLTetriminoSiguiente.setBackground(Color.WHITE);
		
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
		log.operarJuego(log.getIzquierda());
		
	}
	
	
	public void captarMovimientoDer() {
		log.operarJuego(log.getDerecha());
		
	}
	
	public void actualizarTetriminoSiguiente(ImageIcon imagen){
		Image EscalarFoto = imagen.getImage().getScaledInstance(JLTetriminoSiguiente.getWidth(),JLTetriminoSiguiente.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon FotoEscalada = new ImageIcon(EscalarFoto);
		JLTetriminoSiguiente.setIcon(FotoEscalada);
	}
	
	public void captarMovimientoAbajo() {
		log.operarJuego(log.getAbajo());
	}
	
	public void captarOpcionRotar() {
		log.operarJuego(log.getRotar());
		
	}
	
	public void actualizarCelda(int fila, int columna, ImageIcon imagen) {
		labels[fila][columna].setIcon(imagen);
	}

	public void gameOver() {
		PPerdiste.setVisible(true);
		jugando = false;
	}
	
	public void actualizaPuntaje(int puntaje) {
		JLPuntaje.setText("Puntos: "+puntaje);
	}
	

	public void actualizarReloj(int min, int seg) {
		String minutos= ""+min;
		String segundos= ""+seg;
		if(min<10)
			minutos= "0"+min;
		if(seg<10)
			segundos= "0"+seg;
		JLTiempo.setText(minutos+":"+segundos);
	}

	
	class EventoDeTeclado implements KeyListener{
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(jugando) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					captarMovimientoIzq();
				}else
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						captarMovimientoDer();
					}else
						if((e.getKeyCode() == KeyEvent.VK_UP)) {

						}else {
							if(e.getKeyCode() == KeyEvent.VK_DOWN) {
								//captarAbajoNormalizarPausa();
								captarMovimientoAbajo();
							}
						}
							
			}
		}
		public void keyReleased(KeyEvent e) {}
	}
}

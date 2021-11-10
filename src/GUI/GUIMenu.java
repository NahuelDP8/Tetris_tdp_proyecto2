package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class GUIMenu extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMenu frame = new GUIMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIMenu() {
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400,60,351,722);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(GUIMenu.class.getResource("/Imagenes/naranja.png")));
		
		JButton btnNewButton = new JButton("JUGAR");
		btnNewButton.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUI window = new GUI();
							window.frmTetris.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		btnNewButton.setBounds(87, 162, 155, 46);
		
		JLabel lblNewLabel = new JLabel("TETRIS");
		lblNewLabel.setBounds(22, 30, 291, 78);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 45));
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("MANUAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL url = getClass().getResource("/Documentacion/manual.pdf");
				try {
					File file = new File(url.toURI());
					Desktop.getDesktop().open(file);
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		btnNewButton_1.setBounds(88, 277, 155, 46);
		contentPane.add(btnNewButton_1);
		
		JLabel Fondo = new JLabel("");
		Fondo.setVerticalAlignment(SwingConstants.BOTTOM);
		Fondo.setBounds(10, 30, 315, 642);
		Fondo.setIcon(new javax.swing.ImageIcon(GUIMenu.class.getResource("/Imagenes/fondo.png")));
		contentPane.add(Fondo);
	}
}

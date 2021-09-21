package entities;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class PiezaO extends Tetrimino{

	private static ImageIcon miFoto =new ImageIcon(PiezaO.class.getResource("/imagenes/naranja.png"));
	private static Image EscalarFoto = miFoto.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
	private static ImageIcon miFotoEscalada = new ImageIcon(EscalarFoto);
	public PiezaO(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion, miFotoEscalada,c1,c2,c3,c4);
	}

	public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas) {
		return null;
	}
}
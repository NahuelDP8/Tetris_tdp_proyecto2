package entities;

import java.util.ArrayList;


public class PiezaO extends Tetrimino{

	public PiezaO(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion, null,c1,c2,c3,c4);
		ImagenesEscaladas imagenes=new ImagenesEscaladas();
		this.setImageIcon(imagenes.getAmarillo());
	}

	public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas) {
		return null;
	}
}
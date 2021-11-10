package entities;

import java.util.ArrayList;


public class PiezaO extends Tetrimino{

	public PiezaO(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion,c1,c2,c3,c4);
		this.setImageIcon(imagenes.getAmarillo());
		MiImagen=imagenes.getOAmarillo();
	}

	public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas) {
		return new ArrayList<PairTupla>();//devuelve lista vacia.
	}
}
package entities;

import java.util.ArrayList;

public class PiezaO extends Tetrimino{
	private static final String path = "pathDeLafotito=";
	public PiezaO(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion, path,c1,c2,c3,c4);
	}

	public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas) {
		return null;
	}
}
package entities;

import java.util.ArrayList;

public abstract class Tetrimino {
	//Atributos de clase
	protected String pathPhoto; 
	protected int rotacion;
	protected Celda X;
	protected Celda Y;
	protected Celda Z;
	protected Celda W; 
	protected ArrayList<Celda> listaCeldas; 
	
	public Tetrimino(int rotacion, String pathPhoto, Celda c1, Celda c2, Celda c3, Celda c4)  {
		this.rotacion = rotacion;
		this.pathPhoto = pathPhoto;
		this.X = c1;
		this.Y = c2;
		this.Z = c3;
		this.W = c4;
		listaCeldas.add(X);
		listaCeldas.add(Y);
		listaCeldas.add(Z);
		listaCeldas.add(W);
	}
	
	abstract public PairTupla[] rotar(); 
	
	public ArrayList<PairTupla> moverDerecha() {
		boolean encontrado = false;
		int p = 3;
		//Esto se puede optimizar probablemente
		ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
		//Esto se puede optimizar probablemente
		retorno.add(new PairTupla(X.getX(), X.getY())); 
		retorno.add(new PairTupla(Y.getX(), Y.getY())); 
		retorno.add(new PairTupla(Z.getX(), Z.getY())); 
		retorno.add(new PairTupla(W.getX(), W.getY()));
		
		//Buscamos solo aquellas posiciones (x,y) que serán modificadas al mover a la derecha el tetrimino 
		for(int i =0; i<=p; i++) {
			PairTupla auxPair = retorno.get(i);
			for(int j = 0; j<=3 && !encontrado; j++) {
				Celda auxCelda = listaCeldas.get(j);
				if(auxCelda.getX() == auxPair.getX() && auxCelda.getY() == auxPair.getY()) {
					encontrado=true;
					p--;
					retorno.remove(i);
				}
			}
			encontrado=false;
		}
		return retorno;
	}
}

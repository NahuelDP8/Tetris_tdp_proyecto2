package entities;

import java.util.ArrayList;

public abstract class Tetrimino {
	//Atributos de clase
	protected String pathPhoto; 
	protected int rotacion; 
	protected ArrayList<Celda> listaCeldas; 
	
	public Tetrimino(int rotacion, String pathPhoto, Celda c1, Celda c2, Celda c3, Celda c4)  {
		this.rotacion = rotacion;
		this.pathPhoto = pathPhoto;
		listaCeldas.add(c1);
		listaCeldas.add(c2);
		listaCeldas.add(c3);
		listaCeldas.add(c4);
	}
	
	abstract public ArrayList<PairTupla> rotar(); 
	
	public ArrayList<PairTupla> moverDerecha() {
		//Esto se puede optimizar probablemente
		ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
		//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			retorno.add(new PairTupla(aux.getX()+1, aux.getY()));		
		}
		
		posicionesAModificar(retorno);
		return retorno;
	}
	
	public ArrayList<PairTupla> moverIzquierda(){
		//Esto se puede optimizar probablemente
				ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
				//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			retorno.add(new PairTupla(aux.getX()-1, aux.getY()));		
		}			
			posicionesAModificar(retorno);
			return retorno;
	}
	
	public String getPathPhoto() {
		return pathPhoto;
	}
	
	public ArrayList<PairTupla> moverAbajo(){
		//Esto se puede optimizar probablemente
				ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
				//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			retorno.add(new PairTupla(aux.getX(), aux.getY()+1));		
		}			
			posicionesAModificar(retorno);
			return retorno;
	}
	
	//A modificar
	public ArrayList<Celda> getCeldas(){
		return listaCeldas;
	}
	
	//Modifica una estructura, en este caso ArrayList, dejando solo aquellas posiciones futuras que serán modificadas 
	//debido a alguna modificación de la ocupación del tetrimino.
	private void posicionesAModificar(ArrayList<PairTupla> lista ){
		boolean encontrado = false;
		int p = 3;
		for(int i =0; i<=p; i++) {
					PairTupla auxPair = lista.get(i);
					for(int j = 0; j<=3 && !encontrado; j++) {
						Celda auxCelda = listaCeldas.get(j);
						if(auxCelda.getX() == auxPair.getX() && auxCelda.getY() == auxPair.getY()) {
							encontrado=true;
							p--;
							lista.remove(i);
						}
					}
					encontrado=false;
				}
	}
	
}
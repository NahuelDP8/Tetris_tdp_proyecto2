package entities;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Tetrimino {
	//Atributos de clase
	protected ImageIcon Photo; 
	protected int rotacion; 
	protected ArrayList<Celda> listaCeldas;
	//Constructor
	public Tetrimino(int rotacion, ImageIcon Photo, Celda c1, Celda c2, Celda c3, Celda c4)  {
		listaCeldas = new ArrayList<Celda>();
		this.rotacion = rotacion;
		this.Photo = Photo;
		listaCeldas.add(c1);
		listaCeldas.add(c2);
		listaCeldas.add(c3);
		listaCeldas.add(c4);
	}
	protected void setImageIcon(ImageIcon P){
		Photo=P;
	}

	abstract public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas); 
	
	//A modificar
	public ArrayList<Celda> getCeldas(){
		return listaCeldas;
	}
	
	public ImageIcon getPhoto() {
		return Photo;
	}
	public void setCeldas(int i, Celda reemplazo) {
		listaCeldas.set(i, reemplazo);
	}
	
	public void setRotacion(int rot) {
		rotacion = rot;
	}
	
	public int getRotacion() {
		return rotacion;
	}
	
	public ArrayList<PairTupla> moverDerecha(ArrayList<PairTupla> futuras) {
		//Esto se puede optimizar probablemente
		ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
		//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			int xc = aux.getX();
			int yc = aux.getY();
			futuras.add(new PairTupla(xc+1, yc));		
			retorno.add(new PairTupla(xc,yc));	
		}			
		posAOcuparYDesocupar(futuras, retorno);
		return retorno;
	}
	
	public ArrayList<PairTupla> moverIzquierda(ArrayList<PairTupla> futuras){
		//Esto se puede optimizar probablemente
		ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
		//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			int xc = aux.getX();
			int yc = aux.getY();
			futuras.add(new PairTupla(xc-1, yc));		
			retorno.add(new PairTupla(xc,yc));	
		}			
		posAOcuparYDesocupar(futuras, retorno);
		return retorno;
	}
	
	
	public ArrayList<PairTupla> moverAbajo(ArrayList<PairTupla> futuras){
		//Esto se puede optimizar probablemente
		ArrayList<PairTupla> retorno = new ArrayList<PairTupla>();
				//Esto se puede optimizar probablemente
		for(int i = 0; i<=3; i++) {
			Celda aux = listaCeldas.get(i); 
			int xc = aux.getX();
			int yc = aux.getY();
			futuras.add(new PairTupla(xc, yc+1));		
			retorno.add(new PairTupla(xc,yc));	
		}			
			posAOcuparYDesocupar(futuras, retorno);
			return retorno;
	}
	
	private void posAOcuparYDesocupar(ArrayList<PairTupla> futuras , ArrayList<PairTupla> actuales ){
		boolean encontrado = false;
		int p = 3, x=3;
		for(int i =0; i<=p; i++) {
					PairTupla auxPair = futuras.get(i);
					for(int j = 0; j<=x && !encontrado; j++) {
						PairTupla auxDupla = actuales.get(j);
						if(auxDupla.getX() == auxPair.getX() && auxDupla.getY() == auxPair.getY()) {
							p--;
							futuras.remove(auxPair);
							x--;
							actuales.remove(j);
							i--;
							j--;

						} 
					}
				}
	}
}
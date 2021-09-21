package entities;

import java.util.ArrayList;



public class PiezaS extends Tetrimino{

	
	public PiezaS(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion, null,c1,c2,c3,c4);
		ImagenesEscaladas imagenes=new ImagenesEscaladas();
		this.setImageIcon(imagenes.getVerde());
	}
	
	private void ordenar(ArrayList<Celda> lista) {
		Celda temp;
	    boolean sorted = false;
	    if (rotacion == 0 || rotacion == 180) {
		    while (!sorted) {
		        sorted = true;
		        for (int i = 0; i < lista.size()-1; i++) {
		            if ((lista.get(i).getX() > (lista.get(i + 1).getX()) ||
		            		((lista.get(i).getX()) == (lista.get(i + 1).getX())
		            		&& (lista.get(i).getY()) < (lista.get(i + 1).getY())))) {
		                temp = lista.get(i);
		                lista.set(i, lista.get(i + 1));
		                lista.set(i + 1, temp);
		                sorted = false;
		            }
		        }
		    }
	    } else if (rotacion == 90 || rotacion == 270) {
		    while (!sorted) {
		        sorted = true;
		        for (int i = 0; i < lista.size()-1; i++) {
		        	if ((lista.get(i).getY() > (lista.get(i + 1).getY()) ||
		            		((lista.get(i).getY()) == (lista.get(i + 1).getY())
		            		&& (lista.get(i).getX()) > (lista.get(i + 1).getX())))) {
		                temp = lista.get(i);
		                lista.set(i, lista.get(i + 1));
		                lista.set(i + 1, temp);
		                sorted = false;
		            }
		        }
		    }
	    }
	}

	public ArrayList<PairTupla> rotar(ArrayList<PairTupla> antiguas) {
		ArrayList<PairTupla> futuras = new ArrayList<PairTupla>();
		Celda celdaAux;
		
		ordenar(listaCeldas);
		
		if(rotacion == 0) {
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+2, celdaAux.getY()));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX(), celdaAux.getY()+2));	
			
			rotacion = 90;
		} else if(rotacion == 90){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()+2));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()));
			
			rotacion = 180;
			
		} else if(rotacion == 180){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX(), celdaAux.getY()-2));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()-2));;

			rotacion = 270;
			
		} else if(rotacion == 270){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()));

			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()-2));
			
			rotacion = 0;
			
		}
			
		return futuras;
	}
}
package entities;
import java.util.ArrayList;

public class PiezaI extends Tetrimino{

	public PiezaI(int rotacion, Celda c1, Celda c2, Celda c3, Celda c4) {
		super(rotacion,c1,c2,c3,c4);
		this.setImageIcon(imagenes.getCeleste());
		MiImagen=imagenes.getICeleste();
	}
	// Ordena la lista de celdas, para facilitar su manejo:
	private void ordenar(ArrayList<Celda> lista) {
		//Mediante metodo burbuja, ordena la lista segun su posicion.
		Celda temp;
	    boolean sorted = false;
	    // Si esta en sentido horizontal ordena segun x:
	    if (rotacion == 0 || rotacion == 180) {
		    while (!sorted) {
		        sorted = true;
		        for (int i = 0; i < lista.size()-1; i++) {
		            if (lista.get(i).getX() > (lista.get(i + 1).getX())) {
		                temp = lista.get(i);
		                lista.set(i, lista.get(i + 1));
		                lista.set(i + 1, temp);
		                sorted = false;
		            }
		        }
		    }
		 // Si esta en sentido vertical ordena segun y:
	    } else if (rotacion == 90 || rotacion == 270) {
		    while (!sorted) {
		        sorted = true;
		        for (int i = 0; i < lista.size()-1; i++) {
		            if (lista.get(i).getY() > (lista.get(i + 1).getY())) {
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
		//La lista antiguas guarda las posiciones de las celdas que dejaran de estar ocupadas.
		//La lista futuras guarda las posiciones de las celdas que empezaran a ocuparse.
		//Para las celdas que, tras la rotaci�n, siguen estando ocupadas, no cambia nada.
		
		//Ordenamos la lista de celdas que componen al tetrimino:
		ordenar(listaCeldas);
		//Rotacion en angulo 0:
		if(rotacion == 0) {
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+2, celdaAux.getY()+2));
			
			celdaAux = listaCeldas.get(1);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()-1));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()+1));	
		//Rotacion en angulo 90:
		} else if(rotacion == 90){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-2, celdaAux.getY()+2));
			
			celdaAux = listaCeldas.get(1);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()+1));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()-1));
		//Rotacion en angulo 180:
		} else if(rotacion == 180){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()-2));
			
			celdaAux = listaCeldas.get(2);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()-1));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-2, celdaAux.getY()+1));
		//Rotacion en angulo 270:
		} else if(rotacion == 270){
			celdaAux = listaCeldas.get(0);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()-1, celdaAux.getY()+2));
			
			celdaAux = listaCeldas.get(1);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+1, celdaAux.getY()+1));
			
			celdaAux = listaCeldas.get(3);
			antiguas.add(new PairTupla(celdaAux.getX(), celdaAux.getY()));
			futuras.add(new PairTupla(celdaAux.getX()+2, celdaAux.getY()-1));
			
		}
			
		return futuras;
	}
}

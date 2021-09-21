package entities;

import javax.swing.ImageIcon;

public class Celda {
	private boolean estaOcupado;
	private int x,y;
	private ImageIcon imagen;
	public Celda(int x, int y, boolean ocupado, ImageIcon img){
		this.x = x;
		this.y = y;
		estaOcupado = ocupado;
		imagen = img;
	}
	
	public boolean getOcupado() {
		return estaOcupado;
	}
	public void setOcupado(boolean ocupado) {
		estaOcupado = ocupado;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void actualizarImagen(ImageIcon img) {
		imagen = img;
	}
	public ImageIcon getImagen(){
		return imagen;
	}
}

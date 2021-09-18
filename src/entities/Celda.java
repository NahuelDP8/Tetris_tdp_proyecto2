package entities;

public class Celda {
	private boolean estaOcupado;
	private int x,y;
	private String imagen;
	public Celda(int x, int y, boolean ocupado, String img){
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
	public void actualizarImagen(String img) {
		imagen = img;
	}
	public String getImagen(){
		return imagen;
	}
}

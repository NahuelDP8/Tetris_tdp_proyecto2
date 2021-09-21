package entities;

import java.awt.Image;

import javax.swing.ImageIcon;

	
public class ImagenesEscaladas {
	private ImageIcon EscaladaAmarillo=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/amarillo.png"));
	private ImageIcon EscaladaAzul=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/azul.png"));
	private ImageIcon EscaladaCeleste=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/celeste.png"));
	private ImageIcon EscaladaGrisVacio= new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/grisVacio.png"));
	private ImageIcon EscaladaNaranja=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/naranja.png"));
	private ImageIcon EscaladaRojo=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/rojo.png"));
	private ImageIcon EscaladaVerde=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/verde.png"));
	private ImageIcon EscaladaVioleta=new ImageIcon(ImagenesEscaladas.class.getResource("/imagenes/violeta.png"));
	private final static int x=25;
	private final static int y=25;
	
	public ImagenesEscaladas() {
		Image EscalandoFoto=null;
		//escalado de Amarillo
		EscalandoFoto = EscaladaAmarillo.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaAmarillo = new ImageIcon(EscalandoFoto);
		//escalado de Azul
		EscalandoFoto = EscaladaAzul.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaAzul = new ImageIcon(EscalandoFoto);
		//escalado de Celeste
		EscalandoFoto = EscaladaCeleste.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaCeleste = new ImageIcon(EscalandoFoto);
		//escalado de grisVacio
		EscalandoFoto = EscaladaGrisVacio.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaGrisVacio= new ImageIcon(EscalandoFoto);
		//escalado de naranja
		EscalandoFoto = EscaladaNaranja.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaNaranja = new ImageIcon(EscalandoFoto);
		//escalado de rojo
		EscalandoFoto = EscaladaRojo.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaRojo = new ImageIcon(EscalandoFoto);
		//escalado de verde
		EscalandoFoto = EscaladaVerde.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaVerde = new ImageIcon(EscalandoFoto);
		//escalado de violeta
		EscalandoFoto = EscaladaVioleta.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
		EscaladaVioleta = new ImageIcon(EscalandoFoto);
	}
	

	// consultas que devuelven las imagenes escaladas
	public ImageIcon getAmarillo() {
		return EscaladaAmarillo;
	}
	public ImageIcon getAzul() {
		return EscaladaAzul;
	}
	public ImageIcon getCeleste() {
		return EscaladaCeleste;
	}
	public ImageIcon getGrisVacio() {
		return EscaladaGrisVacio;
	}
	public ImageIcon getNaranja() {
		return EscaladaNaranja;
	}
	public ImageIcon getRojo() {
		return EscaladaRojo;
	}
	public ImageIcon getVerde() {
		return EscaladaVerde;
	}
	public ImageIcon getVioleta() {
		return EscaladaVioleta;
	}







}

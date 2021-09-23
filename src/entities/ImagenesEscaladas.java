package entities;

import java.awt.Image;

import javax.swing.ImageIcon;

	
public class ImagenesEscaladas {
	private ImageIcon EscaladaAmarillo=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/amarillo.png"));
	private ImageIcon EscaladaAzul=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/azul.png"));
	private ImageIcon EscaladaCeleste=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/celeste.png"));
	private ImageIcon EscaladaGrisVacio= new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/grisVacio.png"));
	private ImageIcon EscaladaNaranja=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/naranja.png"));
	private ImageIcon EscaladaRojo=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/rojo.png"));
	private ImageIcon EscaladaVerde=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/verde.png"));
	private ImageIcon EscaladaVioleta=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/violeta.png"));
	private ImageIcon EscaladaOAmarillo=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/cuadradoAma.png"));
	private ImageIcon EscaladaJAzul=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/JAzul.png"));
	private ImageIcon EscaladaICeleste=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/ICeleste.png"));
	private ImageIcon EscaladaLNaranja=new ImageIcon(ImagenesEscaladas.class.getResource("/Imagenes/LNaranja.png"));
	//private ImageIcon
	//private ImageIcon
	private final static int x=25;
	private final static int y=25;
	private final static int XG=133;
	private final static int YG=100;
	
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
		//Imagen del cuadrado "O" Amarillo
		EscalandoFoto = EscaladaOAmarillo.getImage().getScaledInstance(XG,YG, Image.SCALE_SMOOTH);
		EscaladaOAmarillo = new ImageIcon(EscalandoFoto);
		//Imagen del "J" Azul
		EscalandoFoto = EscaladaJAzul.getImage().getScaledInstance(XG,YG, Image.SCALE_SMOOTH);
		EscaladaJAzul = new ImageIcon(EscalandoFoto);
		//Imagen del "I" Celeste
		EscalandoFoto = EscaladaICeleste.getImage().getScaledInstance(XG,YG, Image.SCALE_SMOOTH);
		EscaladaICeleste = new ImageIcon(EscalandoFoto);
		//Imagen del "L"
		EscalandoFoto = EscaladaLNaranja.getImage().getScaledInstance(XG,YG, Image.SCALE_SMOOTH);
		EscaladaLNaranja = new ImageIcon(EscalandoFoto);
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
	public ImageIcon getOAmarillo() {
		return EscaladaOAmarillo;
	}
	public ImageIcon getJAzul(){
		return EscaladaJAzul;
	}
	
	public ImageIcon getICeleste() {
		return EscaladaICeleste;
	}

}



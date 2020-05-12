package esqueletoAgua;

public class Principal {

	
	public static void main(String[] args){
		GestorAgua gestor = new GestorAgua();
		Hidrogeno[] h = new Hidrogeno[10];
		Oxigeno[] o = new Oxigeno[5];
		for (int i = 0; i<h.length; i++){
			h[i] = new Hidrogeno(i,gestor);
		}
		for (int i = 0; i<o.length; i++){
			o[i] = new Oxigeno(i,gestor);
		}
		for (int i = 0; i<h.length; i++){
			h[i].start();;
		}
		for (int i = 0; i<o.length; i++){
			o[i].start();
		}
	}
}

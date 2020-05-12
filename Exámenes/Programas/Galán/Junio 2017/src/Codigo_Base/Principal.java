package Codigo_Base;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int NUM_ADULTOS = 5;
		int NUM_BEBES = 15;
		
		Guarderia g = new Guarderia();
		Bebe[] b = new Bebe[NUM_BEBES];
		for (int i = 0; i<b.length; i++){
			b[i] = new Bebe(g,i);
		}
		Adulto[] a = new Adulto[NUM_ADULTOS];
		for (int i = 0; i<a.length; i++){
			a[i] = new Adulto(g,i);
		}
		for (int i = 0; i<b.length; i++){
			b[i].start();
		}
		for (int i = 0; i<a.length; i++){
			a[i].start();
		}

	}

}

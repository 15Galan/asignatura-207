package montanaRusa;

public class Principal {

	public static void main(String[] args) {

		MontanaRusa m=new MontanaRusa();
		Coche c =new Coche(m);
		Pasajero p[]=new Pasajero[10];
		
		for(int i=0;i<10;i++){
			p[i]=new Pasajero(i,m);
			p[i].start();
		}
		c.start();
	}

}

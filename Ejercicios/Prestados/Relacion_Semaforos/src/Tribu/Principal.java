package Tribu;

public class Principal {

	public static void main(String[] args) {
		
		Olla olla=new Olla();
		Cocinero c=new Cocinero(1,olla);
		Canibal canibales []=new Canibal [4];
		for(int i=0;i<4;i++){
			canibales[i]=new Canibal(i,olla);
			canibales[i].start();
		}
		c.start();
	}

}

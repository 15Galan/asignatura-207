package Junio2013;

public class Principal {

	public static void main(String[] args) {
		int TAM=100;
		Buffer buffer=new Buffer(TAM);
		Consumidor c=new Consumidor(buffer);
		ProductorPar cp=new ProductorPar(buffer);
		ProductorImpar ci=new ProductorImpar(buffer);
		
		c.start();
		cp.start();
		ci.start();
		
	}

}

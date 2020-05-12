package VariableCompartida;

public class ProductorConsumidor {
	
	public static void main(String [] args){
		
		Variable<Integer> v = new Variable<Integer>();
		
		Productor p  = new Productor(10, v);
		Consumidor c = new Consumidor(10, v);
		
		p.start();
		c.start();
	}
}

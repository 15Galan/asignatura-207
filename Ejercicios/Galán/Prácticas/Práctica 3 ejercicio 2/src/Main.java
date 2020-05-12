
public class Main {
	
	public static void main(String[]args){
		
		VariableCompartida v = new VariableCompartida();
		
		Thread T1 = new Thread(v);
		Thread T2 = new Thread(v);
		
		T1.start(); T2.start();
		
		System.out.println(v.get());
	}
}

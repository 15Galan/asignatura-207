
public class Main {

	public static void main(String[] args) {
		
		int i = 0, N = 7, fib;
		
		VariableCompartida v = new VariableCompartida(i);
		Thread[] hebra = new Thread[N];
				
		for( ; i < N; i++){
			
			hebra[i] = new Thread(v);
		}
	}

}


public class Fibonacci implements Runnable{
	
	private int id, fib;
	
	public Fibonacci(int id){
		
		this.id = id;
	}
	
	public void run(){
		
		if(id == 0 || id == 1){
			
			fib = id;
			
		}else{
			
			fib += (id-1) + (id-2);
		}
		
	}
}

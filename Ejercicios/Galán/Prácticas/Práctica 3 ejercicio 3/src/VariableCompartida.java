
public class VariableCompartida extends Thread{
	
	private int v;
	
	public VariableCompartida(){
		
		this.v = 0;
	}
	
	public VariableCompartida(int v){
		
		this.v = v;
	}
	
	public void set(int v){
		
		this.v = v;
	}
	
	public int get(){
		
		return v;
	}
	
	public void incrementar(int n){
		
		v += n;
	}
	
	public void run(){
		
		for(int i = 0; i < 100; i++){
			
			incrementar(1);
		}
	}
}

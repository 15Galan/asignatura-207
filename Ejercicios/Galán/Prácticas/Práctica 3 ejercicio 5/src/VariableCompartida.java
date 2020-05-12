
public class VariableCompartida implements Runnable{
	
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
	
	public void run(){
		
		if(v != 0 || v != 1){
			
			v += (v-1) + (v-2);
		}
	}
}

package Mesa;

public class Fumador {
	
	private Mesa mesa;
	private int id;
	
	public Fumador(int id, Mesa mesa){
		
		this.id = id;
		this.mesa = mesa;
	}
	
	public void run(){
		
		while(true){
			
			try{
				
				mesa.qFumador(id);
			}catch(ExceptionInterrupted e){

				System.err.println("ERROR: En algo, no sé.");
			}
		}
	}
}

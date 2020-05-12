package Mesa;

public class Agente {
	
	private Mesa mesa;
	
	public Agente (Mesa mesa){
		
		this.mesa = mesa;
	}
	
	public void run(){
		
		while(true){
			
			mesa.nuevoIngrediente();
		}
	}
}

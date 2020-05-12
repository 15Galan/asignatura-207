package Mesa;

public class Principal {
	
	private int id;
	
	public static void main(String[]args){
		
		Mesa mesa 		= new Mesa();
		Agente agente	= new Agente(mesa);
		Fumador[] fumador = new Fumador[3];
		
		for(int i = 0; i < fumador.length; i++){
			
			fum[i] = new Fumador(i, mesa);
		}	
				fum[i].start();
	}
}

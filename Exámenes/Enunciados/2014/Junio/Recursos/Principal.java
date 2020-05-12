package impresorasMonitor;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int NUM_IMP = 2;
		int NUM_USUARIO = 5;
		Impresora[] imp = new Impresora[NUM_IMP];
		GestorImpresoras g = new GestorImpresoras(imp,NUM_USUARIO);
		for (int i = 0; i<NUM_IMP; i++){
			imp[i] = new Impresora(i,g);
		}
		
		Usuario[] usuario = new Usuario[NUM_USUARIO];
		for (int i = 0; i<NUM_USUARIO; i++){
			usuario[i] = new Usuario(i,g);
		}
		
		for (int i = 0; i<NUM_IMP; i++){
			imp[i].start();
		}
		
		for (int i = 0; i<NUM_USUARIO; i++){
			usuario[i].start();
		}
		
		

	}

}
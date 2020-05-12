package Mesa_Semaforos;

public class Principal {
	
	public static void main(String [] args){

		int tam = 5;

		Silla[] silla = new Silla[tam];
		Tenedor[] ten = new Tenedor[tam];
		Filosofo[] f = new Filosofo[tam];
		
		for(int i = 0;  i < tam; i++){
			
			ten[i] = new Tenedor(i);
			
			f[i] = new Filosofo(i, ten[i], ten[(i+1)%5], silla[i]);
		}
		
		for(int j = 0; j < f.length; j++){
			
			f[j].start();
		}
	}
}

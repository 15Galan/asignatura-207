package Mesa_Monitores;

public class Principal {
	
	public static void main(String [] args){
		
		Silla[] silla = new Silla[5];
		Tenedor[] ten = new Tenedor[5];
		Filosofo[] f = new Filosofo[5];
		
		for(int i = 0;  i < ten.length; i++){
			
			ten[i] = new Tenedor(i);
			
			f[i] = new Filosofo(i, ten[i], ten[(i+1)%5], silla[i]);
		}
		
		for(int j = 0; j < f.length; j++){
			
			f[j].start();
		}
	}

}

package clase;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sillas sillas=new Sillas();
		Tenedor[] ten=new Tenedor[5];
		for(int i=0;i<5;i++){
			ten[i]=new Tenedor(i);
		}
		
		Filosofo[] f=new Filosofo[5];
		for(int i=0;i<5;i++){
			f[i]=new Filosofo(i,ten[i],ten[(i+1)%5],sillas);
		}
		for(int i=0;i<5;i++){
			f[i].start();
		}
	}

}

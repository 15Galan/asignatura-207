package pajaros;

public class Principal {

	public static void main(String[] args) {
		
		Nido nido=new Nido();
		Padre papa=new Padre(1,nido);
		Padre mama=new Padre(2,nido);
		Cria crias[]=new Cria[4];
		//Cria cria1=new Cria(1,nido);
	
		for(int i=0;i<4;i++){
			crias[i]=new Cria(i,nido);
		}
		for(int i=0;i<4;i++){
			crias[i].start();
		}
		papa.start();
		mama.start();
		//cria1.start();
	}

}

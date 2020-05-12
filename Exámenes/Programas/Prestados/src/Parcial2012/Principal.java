package Parcial2012;
public class Principal {
	
	public static void main(String[] args){
		int H = 5;
		int N = 10;
		Tarro tarro = new Tarro(H);
		Oso oso = new Oso(tarro);
		Abeja[] abeja = new Abeja[N];
		for (int i = 0; i<N; i++){
			abeja[i] = new Abeja(tarro,i);
		}
		oso.start();
		for (int i = 0; i<N; i++){
			abeja[i].start();
		}
		
	}

}
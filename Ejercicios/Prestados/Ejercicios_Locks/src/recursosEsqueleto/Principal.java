package recursosEsqueleto;

public class Principal {
	public static void main(String[] args){
		int N = 5;
		int NUMP = 10;
		GestorRecursos gestor = new GestorRecursos(N);
		Cliente[] c = new Cliente[NUMP];
		for (int i = 0; i<c.length; i++)
			c[i] = new Cliente(i,gestor,N);
		for (int i = 0; i<c.length; i++)
			c[i].start();
	}

}

package CuentaDeAhorros;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cuenta c=new Cuenta();
		Cliente clientes[]=new Cliente [10];
		for(int i=0;i<clientes.length;i++){
			clientes[i]=new Cliente(i,c);
			clientes[i].start();
		}

	}

}

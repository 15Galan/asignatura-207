package Septiembre2014;


public class Principal {

public static void main(String[] args){
	int numPlantas = 10;
	int numClientes = 10;
	Ascensor asc = new Ascensor(numPlantas);
	Cliente[] cliente = new Cliente[numClientes];
	for (int i = 0; i<cliente.length; i++){
		cliente[i] = new Cliente(i,numPlantas,asc);
	}
	asc.start();
	for (int i = 0; i<cliente.length; i++){
		cliente[i].start();
	}
}
}





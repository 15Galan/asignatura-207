package Recursos;

public class Cliente {

    private int id, origen, destino;
    Ascensor ascensor = new Ascensor();

    public Cliente(int id, int origen, int detino, Ascensor ascensor){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.ascensor = ascensor;
    }

    public void entrar() throws InterruptedException {

        System.out.println("El cliente "+id+" entra en el ascensor.");

        ascensor.llenar();
    }

    public void salir(Ascensor ascensor) throws InterruptedException {

        System.out.println("El cliente "+id+" abandona el ascensor.");

        ascensor.vaciar();
    }

    public void run(){

        try {
            if(ascensor.estaVacio()) {
                ascensor.llenar();

            }else if(ascensor.planta() == 0){
                do {
                    ascensor.subir();

                }while(ascensor.planta() != destino);

            }else{
                do {
                    ascensor.bajar();

                }while(ascensor.planta() != destino);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

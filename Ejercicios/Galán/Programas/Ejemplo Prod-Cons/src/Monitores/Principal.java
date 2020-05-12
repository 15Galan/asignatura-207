package Monitores;

public class Principal {

    public static void main(String[] args) {
        Peterson monitor = new Peterson();

        Buffer buffer = new Buffer(1024);

        Productor pro = new Productor(buffer, monitor);
        Consumidor con = new Consumidor(buffer, monitor);

        pro.start();
        con.start();
    }
}

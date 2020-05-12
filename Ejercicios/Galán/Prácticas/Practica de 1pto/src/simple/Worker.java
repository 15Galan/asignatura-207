package simple;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Worker extends SwingWorker<List<Primo>, Void> {

    private Panel panel;
    private int n;

    public Worker(int n, Panel panel){
        this.panel = panel;
        this.n = n;
    }

    private boolean esPrimo(int p) {
        if (p == 1 || p == 2)
            return true;

        else {
            int div = 2;
            boolean esPrimo = true;

            while (esPrimo && div * div <= p) {
                if (p % div == 0){
                    esPrimo = false;
                }

                div++;
            }

            return esPrimo;
        }
    }

    @Override
    protected List<Primo> doInBackground() throws Exception {

        List<Primo> lista = new ArrayList<Primo>();
        int p = 0; int primo = 1;

        while (p<n){
            if (esPrimo(primo)){
                lista.add(new Primo(primo, p));
                p++;
            }

            primo++;
        }

        return lista;
    }

    public void done(){

        try {
            panel.listaPrimos(get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();

        }
    }
}

package simpleProgreso;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Worker extends SwingWorker<Void, Primo> {

    private Panel panel;
    private int n;

    public Worker(int n, Panel panel) {
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
                if (p % div == 0) {
                    esPrimo = false;
                }

                div++;
            }

            return esPrimo;
        }
    }

    @Override
    protected Void doInBackground() throws Exception {

        List<Primo> lista = new ArrayList<Primo>();
        int p = 0;
        int primo = 1;

        while (p < n) {
            if (esPrimo(primo)) {
                publish(new Primo(primo, p));   //Almacena datos simultáneamente
                p++;
                this.setProgress(p*100/n);
            }

            primo++;
        }

        return null;
    }

    public void process(List<Primo> lista) {    //Consume datos simultáneamente
        panel.listaPrimos(lista);
    }
}

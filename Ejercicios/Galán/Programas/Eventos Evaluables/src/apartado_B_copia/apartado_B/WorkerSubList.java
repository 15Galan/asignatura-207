package apartado_B_copia.apartado_B;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class WorkerSubList extends SwingWorker<Void, Double> {              // Genera una sublista con los menores o mayores de una lista original

    List<Double> original = new ArrayList<>();
    private Panel panel;
    private boolean menores;

    public WorkerSubList(List<Double> lista, Panel panel, boolean esMenor){ // La variable esMenor establece si la sublista será de los números mayores o menores de la lista original

        original = lista;
        this.panel = panel;
        menores = esMenor;
    }

    protected Void doInBackground() throws Exception {                      // Crea la subLista y la rellena (en segundo plano)
        List<Double> subLista = new ArrayList<>();
        ListIterator<Double> it = original.listIterator();

        for(int i = 0; i < original.size(); i++){
            Double num = it.next();

            if(menores && num < 0.5){
                subLista.add(num);
                publish(num);

            }else if(!menores && num > 0.5){
                subLista.add(num);
                publish(num);
            }
        }

        return null;
    }

    public void process(List<Double> lista){
        if(menores) {
            panel.listaMenores(lista);

        }else{
            panel.listaMayores(lista);
        }
    }
}

package apartado_A;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class WorkerSubList extends SwingWorker<List<Double>, Void> {        // Genera una sublista con los menores o mayores de una lista original

    List<Double> original = new ArrayList<>();
    private Panel panel;
    private boolean menores;

    public WorkerSubList(List<Double> lista, Panel panel, boolean esMenor){ // La variable esMenor establece si la sublista será de los números mayores o menores de la lista original

        original = lista;
        this.panel = panel;
        menores = esMenor;
    }

    protected List<Double> doInBackground() throws Exception {              // Crea la subLista y la rellena (en segundo plano)
        List<Double> subLista = new ArrayList<>();
        ListIterator<Double> it = original.listIterator();

        for(int i = 0; i < original.size(); i++){
            Double num = it.next();

            if(menores && num < 0.5){
                subLista.add(num);

            }else if(!menores && num > 0.5){
                subLista.add(num);
            }
        }

        return subLista;
    }

    public void done(){                                                 // Cuando doInBackground termine se ejecutará
        try{
            if(menores) {
                panel.listaMenores(get());                              // Devuelve la lista de menores si se estableció hacer la lista de menores

            }else{
                panel.listaMayores(get());                              // Devuelve la lista de mayores si se estableció no hacer la lista de menores
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

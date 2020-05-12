package evaluable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class WorkerSubList extends SwingWorker<List<Double>, Void> {

    List<Double> original = new ArrayList<>();
    private Panel panel;
    private boolean menores;

    public WorkerSubList(List<Double> lista, Panel panel, boolean esMenor){

        original = lista;
        this.panel = panel;
        menores = esMenor;
    }

    protected List<Double> doInBackground() throws Exception {
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

    public void done(){
        try{
            if(menores) {
                panel.listaMenores(get());

            }else{
                panel.listaMayores(get());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package apartado_B;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkerGenerator extends SwingWorker<Void, Double>{   // Genera una lista original aleatoria y la muestra

    private Panel panel;
     List<Double> lista = new ArrayList<>();
    private Lock l = new ReentrantLock();
    private Condition original = l.newCondition();
    private Condition menores  = l.newCondition();
    private Condition mayores  = l.newCondition();

    public WorkerGenerator(Panel panel){
        this.panel = panel;
    }

    @Override
    protected Void doInBackground() throws Exception {            // Crea la lista y la rellena (en segundo plano)

        Random r = new Random();

        for(int i = 0; i < panel.cantidad(); i++){
            Double num = r.nextDouble();

            TimeUnit.MILLISECONDS.sleep(100);
            lista.add(num);
            publish(num);
        }

        return null;
    }

//    public List<Double> getLista(){
//        return lista;
//    }

    public void process(List<Double> lista){
        panel.listaOriginal(lista);
    }
}

package apartado_A;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerGenerator extends SwingWorker<List<Double>, Void>{   // Genera una lista original aleatoria y la muestra

    Panel panel;

    public WorkerGenerator(Panel panel){
        this.panel = panel;
    }

    @Override
    protected List<Double> doInBackground() throws Exception {          // Crea la lista y la rellena (en segundo plano)
        Random r = new Random();

        List<Double> lista = new ArrayList<>();

        for(int i = 0; i < panel.cantidad(); i++){
            lista.add(r.nextDouble());
        }

        return lista;
    }

    public void done(){                                                 // Cuando doInBackground termine se ejecutará
        try{
            panel.listaOriginal(get());                                 // Sustituye "get()" por el argumento devuelto por el método anterior

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

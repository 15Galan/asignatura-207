package evaluable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class Controlador implements ActionListener {

    private WorkerGenerator wg;
    private WorkerSubList ws1, ws2;
    private Panel panel;

    public Controlador(Panel panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("START")){

            panel.limpiarArea();
            wg = new WorkerGenerator(panel);
            wg.execute();

            try {
                ws1 = new WorkerSubList(wg.get(), panel, true);
                ws2 = new WorkerSubList(wg.get(), panel, false);
                ws1.execute();
                ws2.execute();

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }
    }
}

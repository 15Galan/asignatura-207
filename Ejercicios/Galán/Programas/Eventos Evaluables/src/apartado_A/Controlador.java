package apartado_A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class Controlador implements ActionListener {                        // Crea un controlador

    private WorkerGenerator wg;
    private WorkerSubList ws1, ws2;
    private Panel panel;

    public Controlador(Panel panel) {                                       // Asigna el controlador a un panel
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {                            // Acción que realiza el controlador según los comandos que reciba
        if (e.getActionCommand().equalsIgnoreCase("START")){

            panel.limpiarArea();
            wg = new WorkerGenerator(panel);                                // Crea un WorkerGenerator nuevo asociado al panel asociado al controlador
            wg.execute();

            try {
                ws1 = new WorkerSubList(wg.get(), panel, true);     // Crea un WorkerSubList para trabajar con los números menores
                ws2 = new WorkerSubList(wg.get(), panel, false);    // Crea un WorkerSubList para trabajar con los números mayores
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

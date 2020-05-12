package apartado_A;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Panel extends JPanel {                                         // Crea una ventana con diferentes paneles

    JLabel cabecera = new JLabel("Longitud de la lista original:");
    JLabel original = new JLabel("Lista original");
    JLabel mayores  = new JLabel("Lista de elementos > 0.5");
    JLabel menores  = new JLabel("Lista de elementos < 0.5");

    JTextField cantidad = new JTextField(5);

    JTextArea aOriginal = new JTextArea(10,50);
    JTextArea aMayores  = new JTextArea(10,20);
    JTextArea aMenores  = new JTextArea(10,20);

    JScrollPane sOriginal = new JScrollPane(aOriginal);
    JScrollPane sMayores  = new JScrollPane(aMayores);
    JScrollPane sMenores  = new JScrollPane(aMenores);


    public Panel(){                                                         // Crea la ventana con la estructura indicada
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new GridLayout(1,2));
        panel4.setLayout(new GridLayout(1,2));
        panel5.setLayout(new GridLayout(1,2));

        panel1.add(cabecera);
        panel1.add(cantidad);

        panel2.add(sOriginal);

        panel3.add(original);

        panel4.add(sMenores);
        panel4.add(sMayores);

        panel5.add(menores);
        panel5.add(mayores);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }


    public void controlador(ActionListener ctr){                            // Asigna un controlador a la variable "cantidad"
        cantidad.addActionListener(ctr);
        cantidad.setActionCommand("START");
    }

    public void listaOriginal(java.util.List<Double> listaOriginales){      // Muestra en el área para la lista original, todos los números de la lista original
        for (int i = 0; i < listaOriginales.size(); i++){
            aOriginal.append("["+(i+1)+"] "+listaOriginales.get(i).toString()+"\n");
        }
    }

    public void listaMayores(java.util.List<Double> listaMayores){          // Muestra en el área para la lista de mayores, todos los números de la lista mayores
        for (int i = 0; i < listaMayores.size(); i++){
            aMayores.append("["+(i+1)+"] "+listaMayores.get(i).toString()+"\n");
        }
    }

    public void listaMenores(java.util.List<Double> listaMenores){          // Muestra en el área para la lista de menores, todos los números de la lista menores
        for (int i = 0; i < listaMenores.size(); i++){
            aMenores.append("["+(i+1)+"] "+listaMenores.get(i).toString()+"\n");
        }
    }

    public void limpiarArea() {                                             // Borra el contenido de todas las áreas
        aOriginal.setText("");
        aMayores.setText("");
        aMenores.setText("");
    }

    public int cantidad(){                                                  // Devuelve la cantidad de números que se quieren generar
        return Integer.parseInt(cantidad.getText());
    }
}

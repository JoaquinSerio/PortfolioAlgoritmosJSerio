import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ArrayListComboBoxExample {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>();
        items.add("Opción 1");
        items.add("Opción 2");
        items.add("Opción 3");
        items.add("Opción 4");

        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));

        JFrame frame = new JFrame("ArrayList ComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Seleccione una opción:"));
        frame.add(comboBox);
        frame.setSize(250, 100);
        frame.setVisible(true);
    }
}


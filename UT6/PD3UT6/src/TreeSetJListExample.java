import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TreeSetJListExample {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Zebra");
        treeSet.add("Apple");
        treeSet.add("Banana");
        treeSet.add("Cherry");

        JList<String> list = new JList<>(treeSet.toArray(new String[0]));

        JFrame frame = new JFrame("TreeSet JList Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(list));
        frame.setSize(200, 150);
        frame.setVisible(true);
    }
}
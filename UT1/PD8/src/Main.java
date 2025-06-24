//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int memoria =
                3 * Short.BYTES +
                        Long.BYTES +
                        Float.BYTES +
                        Double.BYTES +
                        8 * Character.BYTES;
        System.out.println(memoria + " bytes");
    }
}
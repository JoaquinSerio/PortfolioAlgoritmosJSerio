public class ComputeResult {
    public static void main(String[] args) {
        String original = "Franco Colapinto es el mejor piloto de F1";
        StringBuilder result = new StringBuilder("hola");
        int index = original.indexOf('a');

        result.setCharAt(0, original.charAt(0));
        System.out.println("1: " + result);

        result.setCharAt(1, original.charAt(original.length() - 1));
        System.out.println("2: " + result);

        result.insert(1, original.charAt(4));
        System.out.println("3: " + result);

        result.append(original.substring(1, 4));
        System.out.println("4: " + result);

        result.insert(3, original.substring(index, index + 2) + " ");
        System.out.println("5: " + result);
    }
}

public class PalindromeChecker {
    public static void main(String[] args) {
        String[] tests = {
                "Anita lava la tina",
                "¿Acaso hubo búhos acá?",
                "Esto no es palíndromo"
        };

        for (String original : tests) {
            String cleaned = original.replaceAll("[^A-Za-z]", "")
                    .toLowerCase();
            String reversed = new StringBuilder(cleaned)
                    .reverse()
                    .toString();
            System.out.printf("\"%s\" → %b%n",
                    original,
                    cleaned.equals(reversed)
            );
        }
    }
}

public class VowelConsonantCounter {
    public static void main(String[] args) {
        String text = "¡Hola, Mundo!";
        int vowels = 0, consonants = 0;

        for (char c : text.toCharArray()) {
            switch (LetterType.classify(c)) {
                case VOWEL      -> vowels++;
                case CONSONANT  -> consonants++;
                default         -> { }
            }
        }

        System.out.println("Vocales: " + vowels);
        System.out.println("Consonantes: " + consonants);
    }
}
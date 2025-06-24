public enum LetterType {
    VOWEL, CONSONANT, OTHER;

    public static LetterType classify(char c) {
        c = Character.toLowerCase(c);
        if ("aeiou".indexOf(c) >= 0) return VOWEL;
        if (Character.isLetter(c))    return CONSONANT;
        return OTHER;
    }
}
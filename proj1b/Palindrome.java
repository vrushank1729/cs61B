public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> resultingDeque = new ArrayDeque<>();

        for (int i = 0; i < word.length(); i++) {
            resultingDeque.addLast(word.charAt(i));
        }

        return resultingDeque;
    }

    private boolean isPalindromeHelper(Deque<Character> dequeOfWord) {
        if (dequeOfWord.isEmpty() || dequeOfWord.size() == 1) {
            return true;
        }
        if (dequeOfWord.removeFirst() == dequeOfWord.removeLast()) {
            return isPalindromeHelper(dequeOfWord);
        }
        return false;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> dequeOfWord = this.wordToDeque(word);
        return isPalindromeHelper(dequeOfWord);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int leftCharIndex = 0;
        int rightCharIndex = word.length() - 1;

        while (leftCharIndex < rightCharIndex) {
            if ( !cc.equalChars( word.charAt(leftCharIndex), word.charAt(rightCharIndex) ) ) {
                return false;
            }
            leftCharIndex++;
            rightCharIndex--;
        }
        return true;
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String word1 = "";
        String word2 = "a";
        String word3 = "racecar";
        String word4 = "racetcar";

        assertTrue(palindrome.isPalindrome(word1));
        assertTrue(palindrome.isPalindrome(word2));
        assertTrue(palindrome.isPalindrome(word3));
        assertFalse(palindrome.isPalindrome(word4));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String word1 = "";
        String word2 = "a";
        String word3 = "racecar";
        String word4 = "flake";
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome(word1, cc));
        assertTrue(palindrome.isPalindrome(word2, cc));
        assertFalse(palindrome.isPalindrome(word3, cc));
        assertTrue(palindrome.isPalindrome(word4, cc));
    }
}

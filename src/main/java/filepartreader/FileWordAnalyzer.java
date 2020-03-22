package filepartreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {

    private final FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        String fileContent = this.filePartReader.readLines();
        fileContent = removePunctuationsFrom(fileContent);
        String[] arrayOfWords = fileContent.split(" ");
        List<String> words = Arrays.asList(arrayOfWords);
        Collections.sort(words);
        return words;
    }

    public List<String> getWordsContainingSubstring(String subString) throws IOException {
        String fileContent = this.filePartReader.readLines();
        fileContent = removePunctuationsFrom(fileContent);
        String[] words = fileContent.split(" ");
        return getWordsContainingSubstring(words, subString);
    }

    public List<String> getStringsWhichPalindromes() throws IOException {
        String fileContent = this.filePartReader.readLines();
        fileContent = removePunctuationsFrom(fileContent);
        String[] words = fileContent.split(" ");
        return getPalindromes(words);
    }

    private String removePunctuationsFrom(String text) {
        return text.replaceAll("([.,:?;])", "");
    }

    private List<String> getWordsContainingSubstring(String[] words, String subString) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (word.contains(subString)) {
                result.add(word);
            }
        }

        return result;
    }

    private List<String> getPalindromes(String[] words) {
        List<String> palindroms = new ArrayList<>();

        for (String word : words) {
            if (isPalindrome(word)) {
                palindroms.add(word);
            }
        }

        return palindroms;
    }

    private boolean isPalindrome(String word) {
        int rightIndex = word.length() - 1;

        for (int leftIndex = 0; leftIndex < word.length() / 2; leftIndex++) {
            if (Character.toLowerCase(word.charAt(leftIndex)) != Character.toLowerCase(word.charAt(rightIndex))) {
                return false;
            }

            rightIndex--;
        }

        return true;
    }
}

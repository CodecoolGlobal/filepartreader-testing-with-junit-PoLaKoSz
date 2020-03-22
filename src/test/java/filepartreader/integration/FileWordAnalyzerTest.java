package filepartreader.integration;

import filepartreader.FilePartReader;
import filepartreader.FileWordAnalyzer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {

    @Test
    public void getWordsOrderedAlphabeticallyRemovesPunctuation() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("with-punctuation.txt");

        List<String> actual = analyzer.getWordsOrderedAlphabetically();
        List<String> expected = getExpectedAlphabeticList();

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsOrderedAlphabeticallyFromReverseOrder() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("reversed-list.txt");

        List<String> actual = analyzer.getWordsOrderedAlphabetically();
        List<String> expected = getExpectedAlphabeticList();

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsOrderedAlphabeticallyFromCorrectOrder() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("ordered-list.txt");

        List<String> expected = getExpectedAlphabeticList();
        List<String> actual = analyzer.getWordsOrderedAlphabetically();

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsOrderedAlphabeticallyFromUnorderedList() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("unordered-list.txt");

        List<String> expected = getExpectedAlphabeticList();
        List<String> actual = analyzer.getWordsOrderedAlphabetically();

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsContainingSubstringRemovesPunctuation() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("ordered-list.txt");

        List<String> actual = analyzer.getWordsContainingSubstring("app");
        List<String> expected = new ArrayList<>(Arrays.asList("apple", "pineapple"));

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsContainingSubstringWithoutMatch() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("ordered-list.txt");

        List<String> expected = new ArrayList<>();
        List<String> actual = analyzer.getWordsContainingSubstring("codecool");

        assertEquals(expected, actual);
    }

    @Test
    public void getWordsContainingSubstringWithMatches() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("ordered-list.txt");

        List<String> expected = new ArrayList<>(Arrays.asList("dog", "door"));
        List<String> actual = analyzer.getWordsContainingSubstring("do");

        assertEquals(expected, actual);
    }

    @Test
    public void getStringsWhichPalindromesRemovesPunctuation() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("with-palindromes-and-punctuations.txt");

        List<String> expected = new ArrayList<>(Arrays.asList("madam", "noon", "anna"));
        List<String> actual = analyzer.getStringsWhichPalindromes();

        assertEquals(expected, actual);
    }

    @Test
    public void getStringsWhichPalindromesCasesInsensitive() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("with-uppercase-palindromes.txt");

        List<String> expected = new ArrayList<>(Arrays.asList("nOoN", "Anna", "MaDaM"));
        List<String> actual = analyzer.getStringsWhichPalindromes();

        assertEquals(expected, actual);
    }

    @Test
    public void getStringsWhichPalindromesWithNoResult() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("without-palindromes.txt");

        List<String> actual = analyzer.getStringsWhichPalindromes();

        assertEquals(0, actual.size());
    }

    @Test
    public void getStringsWhichPalindromesWithResults() throws IOException {
        FileWordAnalyzer analyzer = getAnalyzerFor("with-palindromes.txt");

        List<String> expected = new ArrayList<>(Arrays.asList("madam", "noon", "anna"));
        List<String> actual = analyzer.getStringsWhichPalindromes();

        assertEquals(expected, actual);
    }

    private FileWordAnalyzer getAnalyzerFor(String filePath) {
        FilePartReader reader = new FilePartReader();
        reader.setup(getFilePathFor(filePath), 1, 1);
        return new FileWordAnalyzer(reader);
    }

    private List<String> getExpectedAlphabeticList() {
        return new ArrayList<>(Arrays.asList(
                "apple",
                "cat",
                "dog",
                "door",
                "pineapple"
        ));
    }

    private String getFilePathFor(String fileName) {
        return Paths.get(
                "src",
                "test",
                "java",
                "filepartreader",
                "integration",
                "resources",
                "filewordanalyzer",
                fileName)
                .toAbsolutePath()
                .toString();
    }
}
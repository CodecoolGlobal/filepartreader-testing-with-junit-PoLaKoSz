package filepartreader.integration;

import filepartreader.FilePartReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    public static final String existingFilePath;

    static {
        existingFilePath = Paths.get(
                "src",
                "test",
                "java",
                "filepartreader",
                "integration",
                "resources",
                "filepartreader",
                "test.txt")
                .toAbsolutePath()
                .toString();
    }

    @Test
    public void setupThrowsIllegalArgumentExceptionWhenToLineIsSmallerThanFromLine() {
        FilePartReader reader = new FilePartReader();

        assertThrows(IllegalArgumentException.class, ()-> {
            reader.setup(this.existingFilePath, 2, 1);
        });
    }

    @Test
    public void setupThrowsIllegalArgumentExceptionWhenFromLineIsLessThan1() {
        FilePartReader reader = new FilePartReader();

        assertThrows(IllegalArgumentException.class, ()-> {
            reader.setup(this.existingFilePath, 0, 10);
        });
    }

    @Test
    public void readThrowsIOExceptionWhenFileDoesNotExist() {
        FilePartReader reader = new FilePartReader();
        reader.setup("non-existing-file.txt", 1, 1);

        assertThrows(IOException.class, ()-> {
            reader.read();
        });
    }

    @Test
    public void readPreservesNewLines() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 1, 6);

        assertEquals("What is Lorem Ipsum?\n" +
                "Lorem Ipsum is simply dummy text\n" +
                "of the printing and typesetting\n" +
                "industry. Lorem Ipsum has been\n" +
                "the industry's standard dummy\n" +
                "text ever since the 1500s, when\n" +
                "principle of selection: he rejects\n", reader.read());
    }

    @Test
    public void readLinesThrowsIOExceptionWhenFileDoesNotExist() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup("non-existing-file.txt", 1, 1);

        assertThrows(IOException.class, ()-> {
            reader.readLines();
        });
    }

    @Test
    public void readLinesReplaceNewLinesWithSpace() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 2, 3);

        assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting", reader.readLines());
    }

    @Test
    public void readLinesFromLineStartsFrom1AndNotFrom0() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 1, 1);

        assertEquals("What is Lorem Ipsum?", reader.readLines());
    }

    @Test
    public void readLinesIncludesFromLine() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 3, 6);

        assertTrue(reader.readLines().startsWith("of the printing and typesetting"));
    }

    @Test
    public void readLinesIncludesToLine() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 1, 2);

        assertEquals("What is Lorem Ipsum? Lorem Ipsum is simply dummy text", reader.readLines());
    }

    @Test
    public void readLinesReturnsOnlyOneLineWhenToLineAndFromLineIsTheSameValue() throws IOException {
        FilePartReader reader = new FilePartReader();
        reader.setup(existingFilePath, 1, 1);

        assertEquals("What is Lorem Ipsum?", reader.readLines());
    }
}
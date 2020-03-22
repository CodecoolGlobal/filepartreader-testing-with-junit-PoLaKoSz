package filepartreader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FilePartReader {

    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    /**
     * Sets up the class for usage.
     * @param filePath Content will be read from here.
     * @param fromLine readLine() will return content from this line.
     * @param toLine readLine() will return content till this line.
     * @throws IllegalArgumentException Occurs when fromLine is
     * bigger than toLine or fromLine is smaller than 1.
     */
    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (fromLine < 1) {
            throw new IllegalArgumentException("fromLine should be greater or equal to 1!");
        }

        if (toLine < fromLine) {
            throw new IllegalArgumentException("fromLine parameter cannot be bigger than toLine!");
        }

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    /**
     * Reads the whole file content into a String.
     * @return File content as a String (new lines replaced with a space).
     * @throws IOException Occurs when the source file doesn't exist.
     */
    public String read() throws IOException {
        return Files.readString(Paths.get(this.filePath), StandardCharsets.UTF_8);
    }

    /**
     * Gets all line(s) between the fromLine and toLine (included) as a String.
     * @return File content as a String (new lines replaced with a space).
     * @throws IOException Occurs when the source file doesn't exist.
     */
    public String readLines() throws IOException {
        String[] lines = this.read().split("\n");

        lines = Arrays.copyOfRange(lines, this.fromLine - 1, this.toLine);

        return String.join(" ", lines);
    }
}

package filepartreader;

import java.io.IOException;

public class FilePartReader {

    /**
     * Sets up the class for usage.
     * @param filePath Content will be read from here.
     * @param fromLine readLine() will return content from this line.
     * @param toLine readLine() will return content till this line.
     * @throws IllegalArgumentException Occurs when fromLine is
     * bigger than toLine or fromLine is smaller than 1.
     */
    public void setup(String filePath, Integer fromLine, Integer toLine) { }

    /**
     * Reads the whole file content into a String.
     * @return File content as a String (new lines replaced with a space).
     * @throws IOException Occurs when the source file doesn't exist.
     */
    public String read() {
        return "";
    }

    /**
     * Gets all line(s) between the fromLine and toLine (included) as a String.
     * @return File content as a String (new lines replaced with a space).
     * @throws IOException Occurs when the source file doesn't exist.
     */
    public String readLines() {
        return "";
    }
}

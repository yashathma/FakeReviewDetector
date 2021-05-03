import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class wordsRunner {
    public static ArrayList<String> buildDictionary(String filename) {
        Scanner scanner;
        ArrayList<String> words = new ArrayList<>();
        try {
            scanner = new Scanner(new FileReader(filename));
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                words.add(line.trim());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return words;
    }
}

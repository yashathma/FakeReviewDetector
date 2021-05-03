import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class reviewRunner {

    public static ArrayList<Review> getinfo(String filename) {
        Scanner scanner;
        ArrayList<Review> out = new ArrayList<>();

        try {
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Review info = processLine(line);
                if (info != null) {
                    out.add(info);
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        }
        return out;
    }

    private static Review processLine(String line) {
        if (line.equals("\"")) {
            return null;
        }
        String truth = line.substring(0, line.indexOf(","));
        int truthful;
        if (truth.equals("truthful")) {
            truthful = 1;
        } else truthful = 0;
        String review = line.substring(line.indexOf("\"") + 1, line.length() - 1);
        String productName = line.substring(line.indexOf(",") + 1, line.indexOf(",", line.indexOf(",") + 1));
        return new Review(review.toLowerCase(), productName, truthful);
    }


    public static int numOfWords(String string) {
        return string.split(" ").length;
    }
}


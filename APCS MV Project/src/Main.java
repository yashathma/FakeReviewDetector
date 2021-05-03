import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Review> reviews = reviewRunner.getinfo("reviews.csv");
        ArrayList<String> words = wordsRunner.buildDictionary("words.txt");
        ArrayList<String> adjectives = adjRunner.buildAdjectives(("adjectives.txt"));
        double rightcount = 0;
        double count = 0;
        String review = "";
        for (int i = 0; i < 1600; i++) {

            count = 0;
            System.out.println(i);
            review = reviews.get(i).getreview();
            review = review.toLowerCase();
            review = takeOutWhiteSpace(review);
            count += checkAdjectives(review, adjectives);
            count += checkReviewLength(review);
            count += checkPunc(review);
            count += checkSpelling(words, review);
            count += checkBestWorst(review);
            count += checkName(reviews.get(i).getproductname(), review);
            if (count > 0) {
                if (reviews.get(i).getTruthfulDeceptive() == 1) {
                    rightcount++;
                }
            } else if (count < 0) {
                if (reviews.get(i).getTruthfulDeceptive() == 0) {
                    rightcount++;
                }
            }
        }
        System.out.println("I am " + ((rightcount / 800) * 100) + "% right");
    }

    private static double checkName(String productname, String review) {
        double numName = numOfName(review, productname);
        if (numName / numberofwords(review) < 0.01 || numName / numberofwords(review) > 0.2) {
            return -0.5;
        } else return 0.5;
    }

    private static double numOfName(String review, String productname) {
        String words[] = review.split(" ");
        int count = 0;
        for (String word : words) {
            if (word.toLowerCase().equals(productname)) {
                count++;
            }
        }
        return count;
    }

    private static double checkBestWorst(String review) {
        if (review.contains("the best") || review.contains("the worst")) {
            return -0.5;
        }
        return 0;
    }

    private static double checkAdjectives(String review, ArrayList<String> adjectives) {
        String words[] = review.split(" ");
        double numberOfWords = words.length;
        double count = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < adjectives.size(); j++) {
                if (words[i].equals(adjectives.get(j))) {
                    count++;
                }
            }
        }
        if (words.length > 200) {

            if (count / numberOfWords >= 0.13) {
                return -1;
            }
        } else if (words.length <= 75) {
            if (count / numberOfWords >= 0.13) {
                return -1;
            }
        }
        return 0;
    }

    private static String takeOutWhiteSpace(String review) {
        review = review.trim();

        while (review.contains("  ")) {
            int index = review.indexOf("  ");
            review = review.substring(0, index) + review.substring(index + 1);
        }
        return review;
    }

    private static void checkCount(double count) {
        if (count >= 0) {
            //System.out.println("Real");
        } else {
            //System.out.println("Fake");
        }
    }

    private static double checkSpelling(ArrayList<String> words, String review) {
        if (spelling(words, review)) {
            return -1;
        }
        return 0.5;
    }

    private static double checkPunc(String review) {
        if (review.contains("!")) {
            return -1;
        }
        return 0;
    }

    private static int checkReviewLength(String review) {
        int numberofWords = numberofwords(review);
        if (numberofWords >= 200) {
            return -1;
        } else if (numberofWords <= 75) {
            return -1;
        }
        return 1;
    }

    private static boolean spelling(ArrayList<String> words, String review) {
        review = stripPunc(review);
        String reviewWords[] = review.split(" ");
        int count = 0;
        for (int i = 0; i < reviewWords.length; i++) {
            for (int j = 0; j < words.size(); j++) {
                if (reviewWords[i].equals(words.get(j))) {
                    count++;
                }
            }
        }
        double numOfWordsMisSpelled = reviewWords.length - count;
        double numOfWords = reviewWords.length;
        if ((numOfWordsMisSpelled / numOfWords) > 0.2) {
            return true;
        }
        return false;
    }

    private static String stripPunc(String review) {
        String newLine = "";
        for (int i = 0; i < review.length(); i++) {
            if (isLetter(review.substring(i, i + 1))) {
                newLine = newLine + review.substring(i, i + 1);
            }
        }
        return newLine;
    }

    private static boolean isLetter(String substring) {
        return "abcdefghijklmnopqrstuvwxyz ".contains(substring.toLowerCase());
    }

    private static int numberofwords(String review) {
        String words[] = review.split(" ");
        return words.length;
    }
}

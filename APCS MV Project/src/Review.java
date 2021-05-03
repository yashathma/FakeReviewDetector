import java.lang.reflect.Array;
import java.util.ArrayList;

public class Review {
    private String review;
    private String productname;
    private int TruthfulDeceptive;

    public Review(String review, String productname, int TruthfulDeceptive) {
        this.review = review;
        this.productname = productname;
        this.TruthfulDeceptive = TruthfulDeceptive;
    }

    public String getproductname() {
        return productname;
    }

    public String getreview() {
        return review;
    }

    public int getTruthfulDeceptive() {
        return TruthfulDeceptive;
    }

    public String getFilename() {
        return review;
    }

}

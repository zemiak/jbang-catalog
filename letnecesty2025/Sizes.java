import java.util.Arrays;
import java.util.Optional;

public enum Sizes {
    VIRTUAL("Virtual", 5, "V"),
    OTHER("Other", 6, "O"),
    MICRO("Micro", 2, "M"),
    SMALL("Small", 8, "S"),
    REGULAR("Regular", 3, "R"),
    LARGE("Large", 4, "L"),
    NOTCHOSEN("Not chosen", 1, "?");

    private String text;
    private int code;
    private String abbreviation;

    public static Sizes of(String text) {
        Optional<Sizes> value = Arrays.stream(Sizes.values()).filter(v -> v.getText().equals(text)).findFirst();

        if (value.isPresent()) {
            return value.get();
        }

        throw new RuntimeException("Size " + text + " is not known yet");
    }

    public String getText() {
        return text;
    }

    Sizes(String text, int code, String abbreviation) {
        this.text = text;
        this.code = code;
        this.abbreviation = abbreviation;
    }

    public int getCode() {
        return this.code;
    }

    public String getImageName() {
        return this.toString().toLowerCase() + ".png";
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}

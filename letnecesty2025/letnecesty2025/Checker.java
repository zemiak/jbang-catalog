package letnecesty2025.letnecesty2025;
import java.util.List;

public class Checker {
    public static void run(List<Geocache> geocaches, String cacherName) {
        MestskaCesta.run(geocaches);
        LesnaCesta.run(geocaches);
        HadankovaCesta.run(geocaches);
    }
}

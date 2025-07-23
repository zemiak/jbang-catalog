package letnecesty2025;

import java.util.List;

public class Checker {
    public static void run(List<Geocache> geocaches, String cacherName) {
        System.out.println("");
        MestskaCesta.run(geocaches);
        System.out.println("");
        LesnaCesta.run(geocaches);
        System.out.println("");
        HadankovaCesta.run(geocaches);
    }
}

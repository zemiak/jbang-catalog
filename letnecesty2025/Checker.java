package letnecesty2025;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Checker {
    public static void run(List<Geocache> geocaches, String cacherName, Path outputFile) throws IOException {
        MestskaCesta.run(geocaches, outputFile);
        LesnaCesta.run(geocaches, outputFile);
        HadankovaCesta.run(geocaches, outputFile);
    }
}

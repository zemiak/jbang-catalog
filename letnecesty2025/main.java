package letnecesty2025;

//JAVA 21
//SOURCES Attribute.java
//SOURCES Geocache.java
//SOURCES LogHandler.java
//SOURCES Reader.java
//SOURCES Sizes.java
//SOURCES Types.java
//SOURCES Checker.java
//SOURCES MestskaCesta.java
//SOURCES LesnaCesta.java
//SOURCES HadankovaCesta.java
//SOURCES Uncompress.java

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class main {
    public static void main(String[] args) {
        var reader = new letnecesty2025.Reader();
        System.out.println("Checker Letne Cesty 2025 0.0.4");

        Path uncompressedFinds = letnecesty2025.Uncompress.getUncompressedFinds();

        List<letnecesty2025.Geocache> geocaches = Collections.emptyList();
        try {
            geocaches = reader.read(uncompressedFinds.toString());
        } catch (Exception e) {
            System.err.println("Chyba: nepodarilo sa nacitat My Finds GPX: " + e.getMessage());
            System.exit(20);
        }

        String cacherName = reader.getCacherName();
        System.out.println("Nick: " + cacherName);

        letnecesty2025.Checker.run(geocaches, cacherName);
    }
}

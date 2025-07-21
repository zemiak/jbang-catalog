package letnecesty2025;
///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 21
//SOURCES letnecesty2025/Attribute.java
//SOURCES letnecesty2025/Geocache.java
//SOURCES letnecesty2025/LogHandler.java
//SOURCES letnecesty2025/Reader.java
//SOURCES letnecesty2025/Sizes.java
//SOURCES letnecesty2025/Types.java
//SOURCES letnecesty2025/Checker.java
//SOURCES letnecesty2025/MestskaCesta.java
//SOURCES letnecesty2025/LesnaCesta.java
//SOURCES letnecesty2025/HadankovaCesta.java

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class main {
    public static void main(String[] args) {
        var reader = new letnecesty2025.Reader();
        String cacherName = null;

        if (args.length > 0) {
            cacherName = args[0];
        } else {
            try (var scanner = new java.util.Scanner(System.in)) {
                System.out.print("Zadaj svoj nick: ");
                cacherName = scanner.nextLine();
            }
        }

        if (cacherName == null || cacherName.isBlank()) {
            System.err.println("Chyba: Nick nemoze byt prazdny.");
            System.exit(10);
        }

        Path uncompressedFinds = letnecesty2025.Uncompress.getUncompressedFinds();

        List<letnecesty2025.Geocache> geocaches = Collections.emptyList();
        try {
            geocaches = reader.read(cacherName, uncompressedFinds.toString());
        } catch (Exception e) {
            System.err.println("Cyba: nepodarilo sa nacitat My Finds GPX: " + e.getMessage());
            System.exit(20);
        }

        letnecesty2025.Checker.run(geocaches, cacherName);
    }
}

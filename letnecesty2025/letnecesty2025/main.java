package letnecesty2025.letnecesty2025;
///usr/bin/env jbang "$0" "$@" ; exit $?

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

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class main {
    public static void main(String[] args) {
        var reader = new letnecesty2025.letnecesty2025.Reader();
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

        List<letnecesty2025.letnecesty2025.Geocache> geocaches = Collections.emptyList();
        try {
            geocaches = reader.read(cacherName, uncompressedFinds.toString());
        } catch (Exception e) {
            System.err.println("Cyba: nepodarilo sa nacitat My Finds GPX: " + e.getMessage());
            System.exit(20);
        }

        letnecesty2025.Checker.run(geocaches, cacherName);
    }
}

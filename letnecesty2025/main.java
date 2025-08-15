package letnecesty2025;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;

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
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

class main {
    public static void main(String[] args) throws IOException {
        var reader = new letnecesty2025.Reader();
        System.out.println("Checker Letne Cesty 2025 0.0.6");

        Path uncompressedFinds = letnecesty2025.Uncompress.getUncompressedFinds();

        List<letnecesty2025.Geocache> geocaches = Collections.emptyList();
        try {
            geocaches = reader.read(uncompressedFinds.toString());
        } catch (Exception e) {
            System.err.println("Chyba: nepodarilo sa nacitat My Finds GPX: " + e.getMessage());
            System.exit(20);
        }

        String cacherName = reader.getCacherName();

        Path tmpHtml;
        try {
            tmpHtml = Files.createTempFile("letnecesty2025-", ".html");
        } catch (java.io.IOException e) {
            System.err.println("Chyba: nepodarilo sa vytvorit docasny subor: " + e.getMessage());
            System.exit(30);
            return;
        }

        Files.writeString(tmpHtml, """
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="UTF-8">
        <title>%s</title>
      </head>
      <body>
        <h1>%s</h1>
    """.formatted(cacherName, cacherName), StandardOpenOption.APPEND);

        letnecesty2025.Checker.run(geocaches, cacherName, tmpHtml);

        Files.writeString(tmpHtml, "</body></html>", StandardOpenOption.APPEND);

        // Open in default browser (Windows/macOS; Linux fallback)
        try {
            openInBrowser(tmpHtml);
        } catch (Exception e) {
            System.err.println("Chyba: nepodarilo sa otvorit prehliadac: " + e.getMessage());
        }
    }

    private static void openInBrowser(Path htmlFile) throws Exception {
        // Prefer Desktop API when available
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(htmlFile.toUri());
                return;
            } else if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(htmlFile.toFile());
                return;
            }
        }

        // OS-specific fallbacks
        String os = System.getProperty("os.name", "").toLowerCase();
        ProcessBuilder pb;
        if (os.contains("mac")) {
            pb = new ProcessBuilder("open", htmlFile.toAbsolutePath().toString());
        } else if (os.contains("win")) {
            pb = new ProcessBuilder("cmd", "/c", "start", "", htmlFile.toAbsolutePath().toString());
        } else {
            pb = new ProcessBuilder("xdg-open", htmlFile.toAbsolutePath().toString());
        }
        pb.inheritIO().start();
    }
}

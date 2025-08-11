package letnecesty2025;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

public class Uncompress {
    public static Path getUncompressedFinds() {
        List<Path> zipFiles;
        try {
            Path currentPath = Paths.get(".");
            try (Stream<Path> paths = Files.walk(currentPath, 1)) {
                zipFiles = paths.filter(path -> path.toString().endsWith(".zip"))
                    .filter(path -> path.getFileName().toString().matches("\\d+\\.zip"))
                    .toList();
            }
        } catch (IOException e) {
            zipFiles = Collections.emptyList();
            System.err.println("Chyba pri citani suborov: " + e.getMessage());
            System.exit(50);
        }

        if (zipFiles.isEmpty()) {
            zipFiles = loadFromDownloadLocation();

            if (zipFiles.isEmpty()) {
                System.err.println("Nenasiel som ziadny subor s My Finds Pocket Query.");
                System.exit(51);
            }
        }

        if (zipFiles.size() > 1) {
            System.err.println("Nasiel som viac suborov ktore by mohli obsahovat My Finds: " + zipFiles.size());
            zipFiles.forEach(System.out::println);
            System.exit(52);
        }

        Path zipFile = zipFiles.get(0);
        Path newFile = null;
        try {
            Path tempDir = Files.createTempDirectory("finds_");
            tempDir.toFile().deleteOnExit();

            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile.toFile()))) {
                var entry = zis.getNextEntry();
                while (entry != null) {
                    newFile = tempDir.resolve(entry.getName());
                    if (! newFile.toString().endsWith(".gpx")) {
                        System.err.println("Subor neobsahuje Pocket Query GPX: " + newFile);
                        System.exit(53);
                    }

                    // Create parent directories if they don't exist
                    Files.createDirectories(newFile.getParent());

                    // Extract the file
                    Files.copy(zis, newFile, StandardCopyOption.REPLACE_EXISTING);

                    entry = zis.getNextEntry();

                    if (null != entry) {
                        System.err.println("V archive je viac ako jeden subor: " + entry);
                        System.exit(55);
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.err.println("Chyba pri dekomprimovani suboru: " + e.getMessage());
            System.exit(56);
        }

        return newFile;
    }

    private static ArrayList<Path> loadFromDownloadLocation() {
        var result = new ArrayList<Path>();

        String os = System.getProperty("os.name", "").toLowerCase();
        String home = System.getProperty("user.home", "");

        Path downloadsDir;
        if (os.contains("win")) {
            String userProfile = System.getenv("USERPROFILE");
            if (userProfile != null && !userProfile.isBlank()) {
                downloadsDir = Paths.get(userProfile, "Downloads");
            } else if (home != null && !home.isBlank()) {
                downloadsDir = Paths.get(home, "Downloads");
            } else {
                downloadsDir = null;
            }
        } else {
            downloadsDir = (home != null && !home.isBlank()) ? Paths.get(home, "Downloads") : null;
        }

        if (downloadsDir == null || !Files.exists(downloadsDir) || !Files.isDirectory(downloadsDir)) {
            return result; // empty
        }

        try (Stream<Path> paths = Files.walk(downloadsDir, 1)) {
            paths.filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".zip"))
                .filter(p -> p.getFileName().toString().matches("\\d+\\.zip"))
                .forEach(result::add);
        } catch (IOException e) {
            System.err.println("Chyba pri citani priecinka Downloads: " + e.getMessage());
        }

        return result;
    }
}

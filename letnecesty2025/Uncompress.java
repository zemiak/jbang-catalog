package letnecesty2025;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

public class Uncompress {
    public static Path getUncompressedFinds() {
        var zipFiles = new ArrayList<Path>();
        try {
            Path currentPath = Paths.get(".");
            try (Stream<Path> paths = Files.walk(currentPath)) {
                paths.filter(path -> path.toString().endsWith(".zip"))
                    .filter(path -> path.getFileName().toString().matches("\\d+\\.zip"))
                    .forEach(path -> {
                        zipFiles.add(path);
                    });
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            System.exit(50);
        }

        if (zipFiles.isEmpty()) {
            System.err.println("No zip files found in the current directory.");
            System.exit(51);
        }

        if (zipFiles.size() > 1) {
            System.err.println("More than one zip file found in the current directory.");
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
                        System.err.println("The file is not a My Finds gpx file: " + newFile);
                        System.exit(53);
                    }

                    // Create parent directories if they don't exist
                    Files.createDirectories(newFile.getParent());

                    // Extract the file
                    Files.copy(zis, newFile, StandardCopyOption.REPLACE_EXISTING);

                    entry = zis.getNextEntry();

                    if (null != entry) {
                        System.err.println("There is more than one file: " + entry);
                        System.exit(55);
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.err.println("Error uncompressing file: " + e.getMessage());
            System.exit(56);
        }

        return newFile;
    }
}

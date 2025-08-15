package letnecesty2025;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;

/**
🚌 nájdi 5 kešiek s atribútom Public Transportation Nearby
👶 nájdi 3 kešky s atribútom Stroller Accessible
🚲 nájdi 5 kešiek s atribútom Bicycles
🍔 nájdi 2 kešky s atribútom Food Nearby
*/

public class MestskaCesta {
    public static void run(List<Geocache> geocaches, Path outputFile) throws IOException {
        long publicTransportationNearby = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.publictransportation))
            .count();

        long strollerAccessible = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.stroller))
            .count();

        long bicycles = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.bicycles))
            .count();

        long foodNearby = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.food))
            .count();

        Boolean fullfilled = true;
        String html = "";

        if (publicTransportationNearby < 5) {
            fullfilled = false;
            html += "<p>🚌 chýba " + (5 - publicTransportationNearby) + " kešiek s atribútom Public Transportation Nearby (5 potrebných)</p>";
        } else {
            html += "<p>🚌 " + publicTransportationNearby + " s atribútom Public Transportation Nearby (5 potrebných)</p>";
        }

        if (strollerAccessible < 3) {
            fullfilled = false;
            html += "<p>👶 chýba " + (3 - strollerAccessible) + " kešiek s atribútom Stroller Accessible (3 potrebné)</p>";
        } else {
            html += "<p>👶 " + strollerAccessible + " s atribútom Stroller Accessible (3 potrebné)</p>";
        }

        if (bicycles < 5) {
            fullfilled = false;
            html += "<p>🚲 chýba " + (5 - bicycles) + " kešiek s atribútom Bicycles (5 potrebných)</p>";
        } else {
            html += "<p>🚲 " + bicycles + " s atribútom Bicycles (5 potrebných)</p>";
        }

        if (foodNearby < 2) {
            fullfilled = false;
            html += "<p>🍔 chýba " + (2 - foodNearby) + " kešiek s atribútom Food Nearby (2 potrebné)</p>";
        } else {
            html += "<p>🍔 " + foodNearby + " s atribútom Food Nearby (2 potrebné)</p>";
        }

        if (fullfilled) {
            html += "<p>🎉 Gratulujem, splnil si podmienky pre mestskú cestu (ak si založil event)!</p>";
        } else {
            html += "<p>😞 Nesplnil si podmienky pre mestskú cestu.</p>";
        }

        Files.writeString(outputFile, """
            <h2>Mestská Cesta</h2><p>%s</p>
        """.formatted(html), StandardOpenOption.APPEND);
    }
}

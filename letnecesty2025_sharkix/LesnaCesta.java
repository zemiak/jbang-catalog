package letnecesty2025;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;

/**
🌎 nájdi 5 earth kešiek
🧳 nájdi 3 kešky s atribútom Recommended for Tourists
⛰ nájdi 5 kešiek s atribútom Scenic view
🥾 nájdi 2 kešky s atribútom Long Hike >10km
*/

public class LesnaCesta {
    public static void run(List<Geocache> geocaches, Path outputFile) throws IOException {
        long earthCaches = geocaches.stream()
            .filter(g -> g.type.equals(Types.EARTH.getText()))
            .count();

        long recommendedForTourists = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.touristok))
            .count();

        long scenicView = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.scenic))
            .count();

        long longHike = geocaches.stream()
            .filter(g -> g.hasAttribute(Attribute.hike_long))
            .count();

        Boolean fullfilled = true;
        String html = "";

        if (earthCaches < 5) {
            fullfilled = false;
            html += "<p>🌎 chýba " + (5 - earthCaches) + " earth kešiek (5 potrebných)</p>";
        } else {
            html += "<p>🌎 " + earthCaches + " earth kešiek (5 potrebných)</p>";
        }

        if (recommendedForTourists < 3) {
            fullfilled = false;
            html += "<p>🧳 chýba " + (3 - recommendedForTourists) + " kešiek s atribútom Recommended for Tourists (3 potrebné)</p>";
        } else {
            html += "<p>🧳 " + recommendedForTourists + " s atribútom Recommended for Tourists (3 potrebné)</p>";
        }

        if (scenicView < 5) {
            fullfilled = false;
            html += "<p>⛰ chýba " + (5 - scenicView) + " kešiek s atribútom Scenic view (5 potrebných)</p>";
        } else {
            html += "<p>⛰ " + scenicView + " s atribútom Scenic view (5 potrebných)</p>";
        }

        if (longHike < 2) {
            fullfilled = false;
            html += "<p>🥾 chýba " + (2 - longHike) + " kešiek s atribútom Long Hike >10km (2 potrebné)</p>";
        } else {
            html += "<p>🥾 " + longHike + " s atribútom Long Hike >10km (2 potrebné)</p>";
        }

        if (fullfilled) {
            html += "<p>🎉 Gratulujem, splnil si podmienky pre lesnú cestu (ak si založil fyzickú kešku)!</p>";
        } else {
            html += "<p>😞 Nesplnil si podmienky pre lesnú cestu.</p>";
        }

        Files.writeString(outputFile, """
            <h2>Lesná Cesta</h2><p>%s</p>
        """.formatted(html), StandardOpenOption.APPEND);
    }
}

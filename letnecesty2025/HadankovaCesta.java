package letnecesty2025;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;

/**
🧩 nájdi 5 mystery kešiek bez atribútu Challenge Cache
🏅 nájdi 3 challenge kešky, ktoré spĺňaš (mystery keška s atribútom Challenge Cache)
🚶 nájdi 5 multi kešiek
📫 nájdi 2 kešky typu Whereigo alebo Letterbox
*/

public class HadankovaCesta {
    public static void run(List<Geocache> geocaches, Path outputFile) throws IOException {
        long mysteryCaches = geocaches.stream()
            .filter(g -> g.type.equals(Types.UNKNOWN.getText()) && !g.hasAttribute(Attribute.challengecache))
            .count();

        long challengeCaches = geocaches.stream()
            .filter(g -> g.type.equals(Types.UNKNOWN.getText()) && g.hasAttribute(Attribute.challengecache))
            .count();

        long multiCaches = geocaches.stream()
            .filter(g -> g.type.equals(Types.MULTI.getText()))
            .count();

        long whereigoOrLetterbox = geocaches.stream()
            .filter(g -> g.type.equals(Types.WHEREIGO.getText()) || g.type.equals(Types.LETTERBOX.getText()))
            .count();

        Boolean fullfilled = true;
        String html = "";

        if (mysteryCaches < 5) {
            html += "<p>🧩 chýba " + (5 - mysteryCaches) + " mystery kešiek bez atribútu Challenge Cache (5 potrebných)</p>";
            fullfilled = false;
        } else {
            html += "<p>🧩 " + mysteryCaches + " mystery kešiek bez atribútu Challenge Cache (5 potrebných)</p>";
        }

        if (challengeCaches < 3) {
            html += "<p>🏅 chýba " + (3 - challengeCaches) + " challenge kešiek, ktoré spĺňaš (3 potrebné)</p>";
            fullfilled = false;
        } else {
            html += "<p>🏅 " + challengeCaches + " challenge kešiek, ktoré spĺňaš (3 potrebné)</p>";
        }

        if (multiCaches < 5) {
            html += "<p>🚶 chýba " + (5 - multiCaches) + " multi kešiek (5 potrebných)</p>";
            fullfilled = false;
        } else {
            html += "<p>🚶 " + multiCaches + " multi kešiek (5 potrebných)</p>";
        }

        if (whereigoOrLetterbox < 2) {
            html += "<p>📫 chýba " + (2 - whereigoOrLetterbox) + " kešiek typu WhereIGo alebo LetterBox (2 potrebné)</p>";
            fullfilled = false;
        } else {
            html += "<p>📫 " + whereigoOrLetterbox + " kešiek typu WhereIGo alebo LetterBox (2 potrebné)</p>";
        }

        if (fullfilled) {
            html += "<p>🎉 Gratulujem, splnil si podmienky pre hádankovú cestu (ak si založil mystery kešku)!</p>";
        } else {
            html += "<p>😞 Nesplnil si podmienky pre hádankovú cestu.</p>";
        }

        Files.writeString(outputFile, """
            <h2>Hádanková Cesta</h2><p>%s</p>
        """.formatted(html), StandardOpenOption.APPEND);
    }
}

package letnecesty2025.letnecesty2025;
import java.util.List;

/**
🌎 nájdi 5 earth kešiek
🧳 nájdi 3 kešky s atribútom Recommended for Tourists
⛰ nájdi 5 kešiek s atribútom Scenic view
🥾 nájdi 2 kešky s atribútom Long Hike >10km
*/

public class LesnaCesta {
    public static void run(List<Geocache> geocaches) {
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

        Boolean fullfilled = earthCaches >= 5
            && recommendedForTourists >= 3
            && scenicView >= 5
            && longHike >= 2;

        if (earthCaches < 5) {
            System.out.println("🌎 chýba " + (5 - earthCaches) + " earth kešiek");
        }

        if (recommendedForTourists < 3) {
            System.out.println("🧳 chýba " + (3 - recommendedForTourists) + " kešiek s atribútom Recommended for Tourists");
        }

        if (scenicView < 5) {
            System.out.println("⛰ chýba " + (5 - scenicView) + " kešiek s atribútom Scenic view");
        }

        if (longHike < 2) {
            System.out.println("🥾 chýba " + (2 - longHike) + " kešiek s atribútom Long Hike >10km");
        }

        if (fullfilled) {
            System.out.println("Gratulujem, splnil si podmienky pre lesnú cestu!");
            System.out.println("🌎 " + earthCaches + " earth kešiek (5 potrebných)");
            System.out.println("🧳 " + recommendedForTourists + " s atribútom Recommended for Tourists (3 potrebné)");
            System.out.println("⛰ " + scenicView + " s atribútom Scenic view (5 potrebných)");
            System.out.println("🥾 " + longHike + " s atribútom Long Hike >10km (2 potrebné)");
        }
    }
}

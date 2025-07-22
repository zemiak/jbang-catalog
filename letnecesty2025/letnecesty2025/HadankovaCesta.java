package letnecesty2025.letnecesty2025;
import java.util.List;

/**
🧩 nájdi 5 mystery kešiek bez atribútu Challenge Cache
🏅 nájdi 3 challenge kešky, ktoré spĺňaš (mystery keška s atribútom Challenge Cache)
🚶 nájdi 5 multi kešiek
📫 nájdi 2 kešky typu Whereigo alebo Letterbox
*/

public class HadankovaCesta {
    public static void run(List<Geocache> geocaches) {
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

        Boolean fullfilled = mysteryCaches >= 5
            && challengeCaches >= 3
            && multiCaches >= 5
            && whereigoOrLetterbox >= 2;

        if (mysteryCaches < 5) {
            System.out.println("🧩 chýba " + (5 - mysteryCaches) + " mystery kešiek bez atribútu Challenge Cache");
        }

        if (challengeCaches < 3) {
            System.out.println("🏅 chýba " + (3 - challengeCaches) + " challenge kešiek, ktoré spĺňaš");
        }

        if (multiCaches < 5) {
            System.out.println("🚶 chýba " + (5 - multiCaches) + " multi kešiek");
        }

        if (whereigoOrLetterbox < 2) {
            System.out.println("📫 chýba " + (2 - whereigoOrLetterbox) + " kešiek typu Whereigo alebo Letterbox");
        }

        if (fullfilled) {
            System.out.println("Gratulujem, splnil si podmienky pre hádankovú cestu!");
            System.out.println("🧩 " + mysteryCaches + " mystery kešiek bez atribútu Challenge Cache (5 potrebných)");
            System.out.println("🏅 " + challengeCaches + " challenge kešiek, ktoré spĺňaš (3 potrebné)");
            System.out.println("🚶 " + multiCaches + " multi kešiek (5 potrebných)");
            System.out.println("📫 " + whereigoOrLetterbox + " kešiek typu WhereIGo alebo LetterBox (2 potrebné)");
        }
    }
}

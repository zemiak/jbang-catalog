package letnecesty2025.letnecesty2025;
import java.util.List;

/**
🚌 nájdi 5 kešiek s atribútom Public Transportation Nearby
👶 nájdi 3 kešky s atribútom Stroller Accessible
🚲 nájdi 5 kešiek s atribútom Bicycles
🍔 nájdi 2 kešky s atribútom Food Nearby
*/

public class MestskaCesta {
    public static void run(List<Geocache> geocaches) {
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

        Boolean fullfilled = publicTransportationNearby >= 5
            && strollerAccessible >= 3
            && bicycles >= 5
            && foodNearby >= 2;

        if (publicTransportationNearby < 5) {
            System.out.println("🚌 chýba " + (5 - publicTransportationNearby) + " kešiek s atribútom Public Transportation Nearby");
        }

        if (strollerAccessible < 3) {
            System.out.println("👶 chýba " + (3 - strollerAccessible) + " kešiek s atribútom Stroller Accessible");
        }

        if (bicycles < 5) {
            System.out.println("🚲 chýba " + (5 - bicycles) + " kešiek s atribútom Bicycles");
        }

        if (foodNearby < 2) {
            System.out.println("🍔 chýba " + (2 - foodNearby) + " kešiek s atribútom Food Nearby");
        }

        if (fullfilled) {
            System.out.println("Gratulujem, splnil si podmienky pre mestskú cestu!");
            System.out.println("🚌 " + publicTransportationNearby + " s atribútom Public Transportation Nearby (5 potrebných)");
            System.out.println("👶 " + strollerAccessible + " s atribútom Stroller Accessible (3 potrebné)");
            System.out.println("🚲 " + bicycles + " s atribútom Bicycles (5 potrebných)");
            System.out.println("🍔 " + foodNearby + " s atribútom Food Nearby (2 potrebné)");
        }
    }
}

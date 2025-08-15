package letnecesty2025;

import java.util.Arrays;
import java.util.Optional;

public enum Types {
    TRADITIONAL("Traditional Cache", false, "2"),
    UNKNOWN("Unknown Cache", false, "8"),
    WHEREIGO("Wherigo Cache", false, "1858"),
    LETTERBOX("Letterbox Hybrid", false, "5"),
    MULTI("Multi-cache", false, "3"),
    VIRTUAL("Virtual Cache", false, "4"),
    WEBCAM("Webcam Cache", false, "11"),
    EARTH("Earthcache", false, "137"),
    EVENT("Event Cache", true, "6"),
    MEGAEVENT("Mega-Event Cache", true, "453"),
    GIGAEVENT("Giga-Event Cache", true, "7005"),
    LAB("LAB Cache", false, "adventure-lab"),
    LOCATIONLESS("Locationless (Reverse) Cache", false, "12"),
    CCE("Community Celebration Event", true, "3653"),
    HQ_BLOCK_PARTY("Geocaching HQ Block Party", true, "4738"),
    HQ_HEADQUARTERS("Gecaching Headquarters", false, "3773"),
    HQ_CELEBRATION("Groundspeak Lost and Found Celebration", true, "3774"),
    CITO("Cache In Trash Out Event", true, "13"),
    APE("Project APE Cache", false, "9"),
    MAZE_EXHIBIT("GPS Adventures Exhibit", false, "1304");

    private String text;
    private boolean event;
    private String code;

    public String getText() {
        return text;
    }

    public String getCode() {
        return this.code;
    }

    public static Types of(String text) {
        Optional<Types> value = Arrays.stream(Types.values()).filter(v -> v.text.equals(text)).findFirst();

        if (value.isPresent()) {
            return value.get();
        }

        throw new RuntimeException("Type " + text + " is not known yet");
    }

    Types(String text, boolean event, String code) {
        this.text = text;
        this.event = event;
        this.code = code;
    }

    public boolean isEvent() {
        return this.event;
    }

    public String getImageHtmlCode() {
        return getImageHtmlCode("24px");
    }

    public String getImageHtmlCode(String width) {
        return "<svg height=\"%s\" width=\"%s\"><use xlink:href=\"/geostats-static/types/cache-types.svg#icon-%s\"/></svg>".formatted(width, width, code);
    }
}

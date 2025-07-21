package letnecesty2025;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Geocache {
    public String lat;
	public String lon;
    public LocalDateTime logDateTime;
    public String size;
    public String type;
    public String code;
    public String longName;
    public String name;
    public Double difficulty;
    public Double terrain;
    public String owner;
    public String placedBy;
    public String country;
    public String county;
    public String state;

    public String logText;
    public Boolean ftf;

    public Integer dayValue;
    public Integer monthValue;
    public Integer dayOfWeekValue;

    public LocalDate placedOn;
    public Integer placedOnMonth;
    public Integer placedOnDay;
    public Integer placedOnYear;
    public Integer placedOnDayOfWeek;

    public Boolean disabled;
    public Boolean archived;

    public List<Attribute> attributes;

    @Override
    public String toString() {
        return "%s (%s) DT %,.1f %,.1f %s [%s] %s".formatted(type, size, difficulty, terrain, name, owner, logDateTime.toString());
    }

    public Collection<Attribute> getAttributes() {
        return null == attributes ? new ArrayList<Attribute>() : attributes;
    }

    public boolean hasAttribute(Attribute attribute) {
        return null != attributes && attributes.contains(attribute);
    }

    public void addAttribute(Attribute attribute) {
        if (null == attributes) {
            attributes = new ArrayList<Attribute>();
        }

        attributes.add(attribute);
    }

    public void copyFrom(Geocache imported) {
        this.lat = imported.lat;
        this.lon = imported.lon;
        this.logDateTime = imported.logDateTime;
        this.size = imported.size;
        this.type = imported.type;
        this.code = imported.code;
        this.longName = imported.longName;
        this.name = imported.name;
        this.difficulty = imported.difficulty;
        this.terrain = imported.terrain;
        this.owner = imported.owner;
        this.placedBy = imported.placedBy;
        this.country = imported.country;
        this.state = imported.state;
        this.logText = imported.logText;
        this.dayValue = imported.dayValue;
        this.monthValue = imported.monthValue;
        this.placedOn = imported.placedOn;
        this.placedOnMonth = imported.placedOnMonth;
        this.placedOnDay = imported.placedOnDay;
        this.placedOnYear = imported.placedOnYear;
        this.disabled = imported.disabled;
        this.archived = imported.archived;
        this.dayOfWeekValue = imported.dayOfWeekValue;
        this.placedOnDayOfWeek = imported.placedOnDayOfWeek;
        this.ftf = imported.ftf;
        this.county = imported.county;
        this.attributes = imported.attributes;
    }
}

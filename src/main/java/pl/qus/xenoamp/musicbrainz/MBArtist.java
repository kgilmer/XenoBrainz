package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBArtist {
    private final String name;
    private final String sortName;
    private final String disambiguation;
    private final String id;

    public MBArtist(Element e) {
        id = e.getAttributeValue("id");
        name = e.getChild("name", e.getNamespace()).getValue();
        sortName = e.getChild("sort-name", e.getNamespace()).getValue();
        disambiguation = e.getChild("disambiguation", e.getNamespace()).getValue();
    }

    @Override
    public String toString() {
        return "[ARTIST] " + getId() + " " + getName() + " " + getDisambiguation();
    }

    public String getName() {
        return name;
    }

    public String getSortName() {
        return sortName;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public String getId() {
        return id;
    }
}

package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

public class MBLabel {
    private final String id;
    private final String name;
    private final String sortName;

    public MBLabel(Element e) {
        name = e.getChild("name", e.getNamespace()).getValue();
        if (e.getChild("sort-name", e.getNamespace()) != null) {
            sortName = e.getChild("sort-name", e.getNamespace()).getValue();
        } else {
            sortName = name;
        }
        id = e.getAttributeValue("id");
    }

    @Override
    public String toString() {
        return "[LABEL] " + id + " " + name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSortName() {
        return sortName;
    }

}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBTag {
    private String name;

    public MBTag(Element e) {
        name = e.getChild("name", e.getNamespace()).getValue();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}

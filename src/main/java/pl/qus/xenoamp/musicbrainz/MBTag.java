package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBTag {
    String name;

    public MBTag(Element e) {
        try {
            name = e.getChild("name", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
    }

    @Override
    public String toString() {
        return name;
    }
}

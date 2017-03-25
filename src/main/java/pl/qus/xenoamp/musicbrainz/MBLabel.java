package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBLabel {
    String id;
    String name;
    String sortName;

    public MBLabel(Element e) {
        try {
            name = e.getChild("name", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            sortName = e.getChild("sort-name", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            id = e.getAttributeValue("id");
        } catch (Exception ex) {
        }
        ;
    }

    @Override
    public String toString() {
        return "[LABEL] " + id + " " + name;
    }
}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBLabelInfo {
    String catalogNumber;
    MBLabel label;

    public MBLabelInfo(Element e) {
        try {
            catalogNumber = e.getChild("catalog-number", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            label = new MBLabel(e.getChild("label", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
    }

    @Override
    public String toString() {
        return "[LABELINFO] " + label.toString() + " " + catalogNumber;
    }

}

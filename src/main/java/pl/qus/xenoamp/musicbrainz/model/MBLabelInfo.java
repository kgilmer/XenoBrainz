package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

public class MBLabelInfo {
    private final String catalogNumber;
    private final MBLabel label;

    public MBLabelInfo(Element e) {
        catalogNumber = e.getChild("catalog-number", e.getNamespace()).getValue();
        label = new MBLabel(e.getChild("label", e.getNamespace()));
    }

    @Override
    public String toString() {
        return "[LABELINFO] " + label.toString() + " " + catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public MBLabel getLabel() {
        return label;
    }
}

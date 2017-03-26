package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

public class MBArtistCredit extends MBNameCredit {
    public MBArtistCredit(final Element e) {
        super(e.getChild("name-credit", e.getNamespace()));
    }
}

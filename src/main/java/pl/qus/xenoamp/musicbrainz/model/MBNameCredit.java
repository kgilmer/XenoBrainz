package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

public class MBNameCredit {

    private final String joinphrase;
    private final MBArtist artist;

    public MBNameCredit(Element e) {
        joinphrase = e.getAttributeValue("joinphrase", e.getNamespace());
        if (e.getChild("artist", e.getNamespace()) != null) {
            artist = new MBArtist(e.getChild("artist", e.getNamespace()));
        } else {
            artist = null;
        }
    }

    @Override
    public String toString() {
        return "[NAMECREDIT] " + artist + " " + joinphrase;
    }

    public String getJoinphrase() {
        return joinphrase;
    }

    public MBArtist getArtist() {
        return artist;
    }
}

package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MBNameCredit {

    private final String joinPhrase;
    private final MBArtist artist;

    public MBNameCredit(@Nonnull final Element e) {
        joinPhrase = JDomUtil.getChildValueAsString(e, "joinPhrase");
        artist = MBArtist.fromElement(e.getChild("artist", e.getNamespace()));
    }

    @Override
    public String toString() {
        return "[NAMECREDIT] " + artist + " " + joinPhrase;
    }

    public @Nullable String getJoinphrase() {
        return joinPhrase;
    }

    public @Nullable MBArtist getArtist() {
        return artist;
    }
}

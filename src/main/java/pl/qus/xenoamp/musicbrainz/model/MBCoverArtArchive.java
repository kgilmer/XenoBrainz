package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

import javax.annotation.Nullable;

/**
 * Created by kgilmer on 3/25/17.
 *
 <cover-art-archive>
     <artwork>true</artwork>
     <count>3</count>
     <front>true</front>
     <back>true</back>
 </cover-art-archive>
 */
public class MBCoverArtArchive {

    private final boolean artwork;
    private final int count;
    private final boolean front;
    private final boolean back;

    public MBCoverArtArchive(final boolean artwork, final int count, final boolean front, final boolean back) {
        this.artwork = artwork;
        this.count = count;
        this.front = front;
        this.back = back;
    }

    public static @Nullable MBCoverArtArchive fromElement(final Element e) {
        try {
            final boolean artwork = Boolean.parseBoolean(e.getChild("artwork", e.getNamespace()).getValue());
            final int count = Integer.parseInt(e.getChild("count", e.getNamespace()).getValue());
            final boolean front = Boolean.parseBoolean(e.getChild("front", e.getNamespace()).getValue());
            final boolean back = Boolean.parseBoolean(e.getChild("back", e.getNamespace()).getValue());

            return new MBCoverArtArchive(artwork, count, front, back);
        } catch (final RuntimeException exception) {
            return null;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean hasArtwork() {
        return artwork;
    }

    public boolean hasBack() {
        return back;
    }

    public boolean hasFront() {
        return front;
    }
}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBMedium {
    private int position;
    private String format;
    private MBTrackList tracklist;
    private MBDiscList discList;

    public MBMedium(Element e) {
        position = Integer.parseInt(e.getChild("position", e.getNamespace()).getValue());
        format = e.getChild("format", e.getNamespace()).getValue();
        tracklist = new MBTrackList(e.getChild("track-list", e.getNamespace()));
        discList = new MBDiscList(e.getChild("disc-list", e.getNamespace()));
    }

    @Override
    public String toString() {
        return "[MEDIUM] Pos:" + position + " format:" + format + "\n" + tracklist.toString();
    }

    public int getPosition() {
        return position;
    }

    public String getFormat() {
        return format;
    }

    public MBTrackList getTracklist() {
        return tracklist;
    }

    public MBDiscList getDiscList() {
        return discList;
    }
}

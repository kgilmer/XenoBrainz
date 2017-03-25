package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBRecording {
    private final String id;
    private final String title;
    private final int length;
    private final MBArtistCredit artistCredit;
    private final MBReleaseList releaseList;
    private final MBTagList tagList;

    public MBRecording(Element e) {
        id = e.getAttributeValue("id");
        //score=Integer.parseInt(e.getAttributeValue("ext:score",MBParser.MBNamespace));
        title = e.getChild("title", e.getNamespace()).getValue();

        if (e.getChild("length") != null) {
            length = Integer.parseInt(e.getChild("length", e.getNamespace()).getValue());
        } else {
            length = -1;
        }
        artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));
        releaseList = new MBReleaseList(e.getChild("release-list", e.getNamespace()));

        if (e.getChild("tag-list") != null) {
            tagList = new MBTagList(e.getChild("tag-list", e.getNamespace()));
        } else {
            tagList = null;
        }
    }

    @Override
    public String toString() {
        return "[RECORDING] " + id + ", title:" + title + ", len:" + length + ", artist:\n" + artistCredit + "\nReleases:" + releaseList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public MBArtistCredit getArtistCredit() {
        return artistCredit;
    }

    public MBReleaseList getReleaseList() {
        return releaseList;
    }

    public MBTagList getTagList() {
        return tagList;
    }
}

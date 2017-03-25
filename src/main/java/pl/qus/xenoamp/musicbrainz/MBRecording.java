package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBRecording {
    String id;
    String title;
    int length;
    int score;
    MBArtistCredit artistCredit;
    MBReleaseList releaseList;
    MBTagList tagList;

    public MBRecording(Element e) {
        id = e.getAttributeValue("id");
        //score=Integer.parseInt(e.getAttributeValue("ext:score",MBParser.MBNamespace));
        title = e.getChild("title", e.getNamespace()).getValue();
        try {
            length = Integer.parseInt(e.getChild("length", e.getNamespace()).getValue());
        } catch (Exception ex) {
        }
        ;
        try {
            artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            releaseList = new MBReleaseList(e.getChild("release-list", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            tagList = new MBTagList(e.getChild("tag-list", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
    }

    public MBRecording(MBTrack t) {

    }

    @Override
    public String toString() {
        return "[RECORDING] " + id + ", score:" + score + ", title:" + title + ", len:" + length + ", artist:\n" + artistCredit + "\nReleases:" + releaseList;
    }
}

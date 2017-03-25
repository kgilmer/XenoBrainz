package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MBRecording {
    private final String id;
    private final String title;
    private final int length;
    private final MBArtistCredit artistCredit;
    private final List<MBRelease> releaseList;
    private final List<String> tagList;

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

        releaseList = new ArrayList<>();
        List<Element> elementy = e.getChild("release-list", e.getNamespace()).getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
        }

        if (e.getChild("tag-list", e.getNamespace()) != null) {
            tagList = new ArrayList<>();
            List<Element> tags = e.getChild("tag-list", e.getNamespace()).getChildren();

            for (Element child : tags) {
                if (child.getName().equals("tag")) {
                    String tag = child.getChild("name", e.getNamespace()).getValue();
                    tagList.add(tag);
                }
            }
        } else {
            tagList = Collections.emptyList();
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

    public List<MBRelease> getReleaseList() {
        return releaseList;
    }

    public List<String> getTagList() {
        return tagList;
    }
}

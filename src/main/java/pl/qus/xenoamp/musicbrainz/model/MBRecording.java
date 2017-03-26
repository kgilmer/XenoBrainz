package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MBRecording {
    private final String id;
    private final String title;
    private final int length;
    private final MBArtistCredit artistCredit;
    private final List<MBRelease> releaseList;
    private final List<MBTag> tagList;

    public MBRecording(final Element e) {
        id = e.getAttributeValue("id");
        //score=Integer.parseInt(e.getAttributeValue("ext:score",MusicBrainzClient.MBNamespace));
        title = JDomUtils.getChildValueAsString(e, "title");
        length = JDomUtils.getChildValueAsInteger(e, "length", -1);
        artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));

        releaseList = new ArrayList<>();
        final List<Element> elementy = e.getChild("release-list", e.getNamespace()).getChildren();

        for (final Element child : elementy) {
            if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
        }

        if (e.getChild("tag-list", e.getNamespace()) != null) {
            tagList = new ArrayList<>();
            final List<Element> tags = e.getChild("tag-list", e.getNamespace()).getChildren();

            for (final Element child : tags) {
                if (child.getName().equals("tag")) {
                    tagList.add(new MBTag(child));
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

    public List<MBTag> getTagList() {
        return tagList;
    }
}

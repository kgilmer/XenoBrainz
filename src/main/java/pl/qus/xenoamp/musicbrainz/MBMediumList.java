package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBMediumList extends Vector<MBMedium> {
    private int trackCount;

    public MBMediumList(Element e) {
        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("medium")) add(new MBMedium(child));
        }

        trackCount = Integer.parseInt(e.getChild("track-count", e.getNamespace()).getValue());
    }

    public int getTrackCount() {
        return trackCount;
    }
}

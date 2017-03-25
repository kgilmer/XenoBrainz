package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBTrackList extends Vector<MBTrack> {
    public MBTrackList(Element e) {
        List<Element> elementy = e.getChildren();
        for (Element child : elementy) {
            if (child.getName().equals("track")) add(new MBTrack(child));
        }
    }

}

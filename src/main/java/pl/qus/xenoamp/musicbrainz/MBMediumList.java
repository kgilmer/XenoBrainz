package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBMediumList extends Vector<MBMedium> {
    /**
     *
     */
    private static final long serialVersionUID = 7993415081102132226L;
    int trackCount;
    //List<MBMedium> media;

    public MBMediumList(Element e) {
        //media = new Vector<MBMedium>();
        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("medium")) add(new MBMedium(child));
        }

        try {
            trackCount = Integer.parseInt(e.getChild("track-count", e.getNamespace()).getValue());
        } catch (Exception ex) {
        }
        ;
    }
}

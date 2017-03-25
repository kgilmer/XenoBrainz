package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBTagList extends Vector<MBTag> {

    public MBTagList(Element e) {
        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("tag")) add(new MBTag(child));
        }
    }

}

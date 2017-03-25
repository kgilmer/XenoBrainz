package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBReleaseList extends Vector<MBRelease> {
    //List<MBRelease> releases;

    public MBReleaseList(Element e) {
        //releases=new Vector<MBRelease>();

        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("release")) add(new MBRelease(child));
        }

    }
}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBLabelInfoList extends Vector<MBLabelInfo> {
    public MBLabelInfoList(Element e) {
        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("label-info")) add(new MBLabelInfo(child));
        }
    }
}

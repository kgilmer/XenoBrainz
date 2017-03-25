package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBLabelInfoList extends Vector<MBLabelInfo> {
    //List<MBLabelInfo> media;

    /**
     *
     */
    private static final long serialVersionUID = 4222882649745170438L;

    public MBLabelInfoList(Element e) {
        //media=new Vector<MBLabelInfo>();

        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("label-info")) add(new MBLabelInfo(child));
        }

    }
}

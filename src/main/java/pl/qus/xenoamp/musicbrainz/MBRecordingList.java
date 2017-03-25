package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

import java.util.List;
import java.util.Vector;

public class MBRecordingList extends Vector<MBRecording> {
    public MBRecordingList(Element e) {

        List<Element> elementy = e.getChildren();

        for (Element child : elementy) {
            if (child.getName().equals("recording")) add(new MBRecording(child));
        }

    }
}

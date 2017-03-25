package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

//<track><number>18</number><title>Elektroneuronowa przyjaźń</title><length>205000</length></track>

public class MBTrack {
    int number;
    int position;
    String title;
    int length;
    MBRecording recording;

    public MBTrack(Element e) {
        try {
            number = Integer.parseInt(e.getChild("number", e.getNamespace()).getValue());
        } catch (Exception ex) {
        }
        ;
        try {
            position = Integer.parseInt(e.getChild("position", e.getNamespace()).getValue());
        } catch (Exception ex) {
        }
        ;
        try {
            title = e.getChild("title", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            length = Integer.parseInt(e.getChild("length", e.getNamespace()).getValue());
        } catch (Exception ex) {
        }
        ;
        try {
            recording = new MBRecording(e.getChild("recording", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
    }

    @Override
    public String toString() {
        return "[TRACK] " + number + ". " + title + " " + length + " recording:" + recording;
    }
}

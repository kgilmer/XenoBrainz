package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBTrack {
    private final int number;
    private final int position;
    private final String title;
    private final int length;
    private final MBRecording recording;

    public MBTrack(Element e) {
            number = Integer.parseInt(e.getChild("number", e.getNamespace()).getValue());
            if (e.getChild("position") != null) {
                position = Integer.parseInt(e.getChild("position", e.getNamespace()).getValue());
            } else {
                position = -1;
            }
            title = e.getChild("title", e.getNamespace()).getValue();
            length = Integer.parseInt(e.getChild("length", e.getNamespace()).getValue());

            if (e.getChild("recording") != null) {
                recording = new MBRecording(e.getChild("recording", e.getNamespace()));
            } else {
                recording = null;
            }
    }

    @Override
    public String toString() {
        return "[TRACK] " + number + ". " + title + " " + length + " recording:" + recording;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public MBRecording getRecording() {
        return recording;
    }
}

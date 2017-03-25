package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBTextRepresentation {
    private final String language;
    private final String script;

    public MBTextRepresentation(Element e) {
        language = e.getChild("language", e.getNamespace()).getValue();
        script = e.getChild("script", e.getNamespace()).getValue();
    }

    @Override
    public String toString() {
        return "[TEXTREPRESENTATION] " + language + " " + script;
    }

    public String getLanguage() {
        return language;
    }

    public String getScript() {
        return script;
    }
}

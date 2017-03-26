package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MBTextRepresentation {
    private final String language;
    private final String script;

    public MBTextRepresentation(String language, String script) {
        this.language = language;
        this.script = script;
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

    public static @Nullable MBTextRepresentation fromElement(@Nonnull final Element e) {
        try {
            String language = e.getChild("language", e.getNamespace()).getValue();
            String script = e.getChild("script", e.getNamespace()).getValue();

            if (language != null && script != null) {
                return new MBTextRepresentation(language, script);
            }
        } catch (final RuntimeException ex) {
        }

        return null;
    }
}

package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * https://wiki.musicbrainz.org/Label
 */
public class MBLabel {
    private final String id;
    private final String name;
    private final String sortName;

    public MBLabel(final String id, final String name, final String sortName) {
        this.id = id;
        this.name = name;
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "[LABEL] " + id + " " + name;
    }

    public @Nonnull String getId() {
        return id;
    }

    public @Nonnull String getName() {
        return name;
    }

    public @Nonnull String getSortName() {
        return sortName;
    }

    public static @Nullable MBLabel fromElement(final Element e) {
        try {
            final String name = JDomUtil.getChildValueAsString(e, "name");
            final String sortName = JDomUtil.getChildValueAsString(e, "sort-name", name);
            final String id = e.getAttributeValue("id");

            if (name != null && sortName != null && id != null) {
                return new MBLabel(id, name, sortName);
            }
        } catch (final RuntimeException exception) {
        }

        return null;
    }

}

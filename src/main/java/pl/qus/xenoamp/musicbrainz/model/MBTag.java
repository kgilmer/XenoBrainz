package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MBTag {
    private final String name;
    private final int count;

    public MBTag(@Nonnull final Element e) {
        name = e.getChild("name", e.getNamespace()).getValue();
        try {
            count = e.getAttribute("count").getIntValue();
        } catch (final DataConversionException e1) {
            throw new IllegalArgumentException("Expected attribute 'count' not found or invalid.");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public @Nonnull
    String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public static List<MBTag> listFromElements(@Nullable final Element e) {
        final List<MBTag> tagList;
        if (e != null) {
            tagList = new ArrayList<>();
            final List<Element> tags = e.getChildren();

            for (final Element child : tags) {
                if (child.getName().equals("tag")) {
                    tagList.add(new MBTag(child));
                }
            }
        } else {
            tagList = Collections.emptyList();
        }
        return tagList;
    }
}

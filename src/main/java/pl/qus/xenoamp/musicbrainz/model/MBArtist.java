package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MBArtist {
    private final String name;
    private final String sortName;
    private final String disambiguation;
    private final String id;
    private final List<MBTag> tagList;

    public MBArtist(final String name, final String sortName, final String disambiguation, final String id, final List<MBTag> tagList) {
        this.name = name;
        this.sortName = sortName;
        this.disambiguation = disambiguation;
        this.id = id;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "[ARTIST] " + getId() + " " + getName() + " " + getDisambiguation();
    }

    public String getName() {
        return name;
    }

    public String getSortName() {
        return sortName;
    }

    public @Nullable String getDisambiguation() {
        return disambiguation;
    }

    public @Nonnull String getId() {
        return id;
    }

    public @Nonnull List<MBTag> getTagList() {
        return tagList;
    }

    public static @Nullable MBArtist fromElement(@Nonnull final Element e) {
        final String id = e.getAttributeValue("id");
        final String name = JDomUtils.getChildValueAsString(e, "name");
        final String sortName = JDomUtils.getChildValueAsString(e, "sort-name");
        final String disambiguation = JDomUtils.getChildValueAsString(e, "disambiguation");
        final List<MBTag> tagList = MBTag.listFromElements(e.getChild("tag-list", e.getNamespace()));

        if (id != null && name != null && tagList != null) {
            return new MBArtist(name, sortName, disambiguation, id, tagList);
        }

        return null;
    }

}

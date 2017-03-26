package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://wiki.musicbrainz.org/Label
 */
public class MBLabelInfo {
    private final String catalogNumber;
    private final MBLabel label;

    private MBLabelInfo(final Element e) {
        catalogNumber = JDomUtils.getChildValueAsString(e, "catalog-number");
        label = MBLabel.fromElement(e.getChild("label", e.getNamespace()));
    }

    @Override
    public String toString() {
        return "[LABELINFO] " + label.toString() + " " + catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public MBLabel getLabel() {
        return label;
    }

    public static @Nonnull List<MBLabelInfo> listFromElement(@Nonnull final Element e) {
        final List<MBLabelInfo> labelInfoList;

        if (!e.getChildren().isEmpty()) {
            labelInfoList = new ArrayList<>();
            for (final Element child : e.getChildren()) {
                if (child.getName().equals("label-info")) labelInfoList.add(new MBLabelInfo(child));
            }
        } else {
            labelInfoList = Collections.emptyList();
        }

        return labelInfoList;

    }
}

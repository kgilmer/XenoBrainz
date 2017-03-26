package pl.qus.xenoamp.musicbrainz.model;

import org.jdom2.Element;
import pl.qus.xenoamp.musicbrainz.util.JDomUtils;

import java.util.List;

public class MBRelease {
    private final String id;
    private final String title;
    private final String status;
    private final String quality;
    private final MBTextRepresentation textRepresentation;
    private final MBArtistCredit artistCredit;
    private final String date;
    private final String country;
    private final String barcode;
    private final String asin;
    private final List<MBLabelInfo> labelInfoList;
    private final MBCoverArtArchive coverArtArchive;

    public MBRelease(final Element e) {
        id = e.getAttributeValue("id");
        title = e.getChild("title", e.getNamespace()).getValue();
        status = e.getChild("status", e.getNamespace()).getValue();
        date = e.getChild("date", e.getNamespace()).getValue();
        country = e.getChild("country", e.getNamespace()).getValue();
        textRepresentation = MBTextRepresentation.fromElement(e.getChild("text-representation", e.getNamespace()));
        if (e.getChild("artist-credit", e.getNamespace()) != null) {
            artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));
        } else {
            artistCredit = null;
        }

        labelInfoList = MBLabelInfo.listFromElement(e.getChild("label-info-list", e.getNamespace()));
        barcode = JDomUtils.getChildValueAsString(e, "barcode", null);
        asin = JDomUtils.getChildValueAsString(e, "asin", null);
        quality = JDomUtils.getChildValueAsString(e, "quality", null);

        coverArtArchive = MBCoverArtArchive.fromElement(e.getChild("cover-art-archive", e.getNamespace()));
    }

    @Override
    public String toString() {
        return "[RELEASE] Id:" + id + ", Title:" + title + ", status:" + status + ", date:" + date + ", country:" + country;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getQuality() {
        return quality;
    }

    public MBTextRepresentation getTextRepresentation() {
        return textRepresentation;
    }

    public MBArtistCredit getArtistCredit() {
        return artistCredit;
    }

    public String getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getAsin() {
        return asin;
    }

    public List<MBLabelInfo> getLabelInfoList() {
        return labelInfoList;
    }

    public MBCoverArtArchive getCoverArtArchive() {
        return coverArtArchive;
    }
}

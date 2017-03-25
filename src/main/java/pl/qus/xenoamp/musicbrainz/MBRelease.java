package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

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
    private final MBLabelInfoList labelInfoList;
    private final MBMediumList mediumList;

    public MBRelease(Element e) {
        id = e.getAttributeValue("id");
        title = e.getChild("title", e.getNamespace()).getValue();
        status = e.getChild("status", e.getNamespace()).getValue();
        date = e.getChild("date", e.getNamespace()).getValue();
        country = e.getChild("country", e.getNamespace()).getValue();
        mediumList = new MBMediumList(e.getChild("medium-list", e.getNamespace()));
        if (e.getChild("text-representation") != null) {
            textRepresentation = new MBTextRepresentation(e.getChild("text-representation", e.getNamespace()));
        } else {
            textRepresentation = null;
        }
        if (e.getChild("artist-credit", e.getNamespace()) != null) {
            artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));
        } else {
            artistCredit = null;
        }
        if (e.getChild("label-info-list", e.getNamespace()) != null) {
            labelInfoList = new MBLabelInfoList(e.getChild("label-info-list", e.getNamespace()));
        } else {
            labelInfoList = null;
        }
        if (e.getChild("barcode", e.getNamespace()) != null) {
            barcode = e.getChild("barcode", e.getNamespace()).getValue();
        } else {
            barcode = null;
        }

        if (e.getChild("asin", e.getNamespace()) != null) {
            asin = e.getChild("asin", e.getNamespace()).getValue();
        } else {
            asin = null;
        }
        if (e.getChild("quality", e.getNamespace()) != null) {
            quality = e.getChild("quality", e.getNamespace()).getValue();
        } else {
            quality = null;
        }
    }

    @Override
    public String toString() {
        return "[RELEASE] Id:" + id + ", Title:" + title + ", status:" + status + ", date:" + date + ", country:" + country + ", Media:" + mediumList;
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

    public MBLabelInfoList getLabelInfoList() {
        return labelInfoList;
    }

    public MBMediumList getMediumList() {
        return mediumList;
    }
}

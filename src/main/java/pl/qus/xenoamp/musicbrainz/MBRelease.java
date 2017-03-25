package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBRelease {
    String id;
    String title;
    String status;
    String quality;
    MBTextRepresentation textRepresentation;
    MBArtistCredit artistCredit;
    String releaseGroup;
    String date;
    String country;
    String barcode;
    String asin;
    MBLabelInfoList labelInfoList;
    MBMediumList mediumList;

    public MBRelease(Element e) {
        id = e.getAttributeValue("id");
        title = e.getChild("title", e.getNamespace()).getValue();
        try {
            status = e.getChild("status", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        // releaseGroup
        try {
            date = e.getChild("date", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            country = e.getChild("country", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            mediumList = new MBMediumList(e.getChild("medium-list", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            textRepresentation = new MBTextRepresentation(e.getChild("text-representation", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            artistCredit = new MBArtistCredit(e.getChild("artist-credit", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            labelInfoList = new MBLabelInfoList(e.getChild("label-info-list", e.getNamespace()));
        } catch (Exception ex) {
        }
        ;
        try {
            barcode = e.getChild("barcode", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            asin = e.getChild("asin", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
        try {
            quality = e.getChild("quality", e.getNamespace()).getValue();
        } catch (Exception ex) {
        }
        ;
    }

    @Override
    public String toString() {
        return "[RELEASE] Id:" + id + ", Title:" + title + ", status:" + status + ", date:" + date + ", country:" + country + ", Media:" + mediumList;
    }

    public String getId() {
        return id;
    }
}

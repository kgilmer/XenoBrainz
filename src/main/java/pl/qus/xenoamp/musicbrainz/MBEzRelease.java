package pl.qus.xenoamp.musicbrainz;

import java.util.Vector;

public class MBEzRelease {
    private String artistName;
    private String artistSortName;
    private String artistId;
    private String artistDisamb;
    private String country;
    private String date;
    private String id;
    private String label;
    private Vector<MBEzRecording> tracks;
    private String title;
    private String format;
    private int likeness;

    public MBEzRelease(MBRelease r) {
        try {
            artistName = r.artistCredit.nameCredit.artist.name;
        } catch (Exception ex) {
        }
        ;
        try {
            artistSortName = r.artistCredit.nameCredit.artist.sortName;
        } catch (Exception ex) {
        }
        ;
        try {
            artistId = r.artistCredit.nameCredit.artist.id;
        } catch (Exception ex) {
        }
        ;
        try {
            artistDisamb = r.artistCredit.nameCredit.artist.disambiguation;
        } catch (Exception ex) {
        }
        ;
        country = r.country;
        date = r.date;
        id = r.id;
        try {
            label = r.labelInfoList.elementAt(0).label.name;
        } catch (Exception ex) {
        }
        ;
        title = r.title;

        try {
            format = r.mediumList.elementAt(0).format;
        } catch (Exception ex) {
        }
        ;

        MBTrackList tracki = r.mediumList.elementAt(0).tracklist;
        tracks = new Vector<MBEzRecording>();

        int i = 1;

        for (MBTrack t : tracki) {
            MBEzRecording nowy = new MBEzRecording(t.recording);
            nowy.setReleaseTrack(i++);
            tracks.add(nowy);
        }
    }

    public Vector<MBEzRecording> getTracks() {
        return tracks;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCountryCode() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getLabels() {
        return label;
    }

    public int getTrackNum() {
        if (tracks != null)
            return tracks.size();
        else return 0;
    }

    public String getFormats() {
        return format;
    }

    public int getLikeness() {
        return likeness;
    }

    public String getId() {
        return id;
    }

    public void setLikeness(int l) {
        likeness = l;
    }

    public String getArtistSortName() {
        return artistSortName;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistDisamb() {
        return artistDisamb;
    }
}

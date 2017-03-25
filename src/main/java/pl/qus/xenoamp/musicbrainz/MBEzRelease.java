package pl.qus.xenoamp.musicbrainz;

import java.util.Vector;

public class MBEzRelease {
    private final String artistName;
    private final String artistSortName;
    private final String artistId;
    private final String artistDisamb;
    private final String date;
    private final String id;
    private final String label;
    private final Vector<MBEzRecording> tracks;
    private final String title;
    private final String format;

    public MBEzRelease(MBRelease r) {
        artistName = r.artistCredit.getArtist().getName();
        artistSortName = r.artistCredit.getArtist().getSortName();
        artistId = r.artistCredit.getArtist().getId();
        artistDisamb = r.artistCredit.getArtist().getDisambiguation();
        date = r.date;
        id = r.id;
        label = r.labelInfoList.elementAt(0).getLabel().getName();
        title = r.title;
        format = r.mediumList.elementAt(0).getFormat();
        MBTrackList tracki = r.mediumList.elementAt(0).getTracklist();
        tracks = new Vector<MBEzRecording>();

        int i = 1;

        for (MBTrack t : tracki) {
            MBEzRecording nowy = new MBEzRecording(t.getRecording());
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

    public String getId() {
        return id;
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

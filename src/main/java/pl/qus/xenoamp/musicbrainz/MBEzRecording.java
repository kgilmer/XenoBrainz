package pl.qus.xenoamp.musicbrainz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MBEzRecording {
    private final String id;
    private final String title;
    private final int length;
    private final String artist;
    private final String artistId;
    private final String artistDisamb;
    private final String releaseId;
    private final String releaseTitle;
    private final String releaseDate;
    private final String releaseCountry;
    private final int releaseTrack;

    public MBEzRecording(MBRecording r) {
        id = r.getId();
        title = r.getTitle();
        length = r.getLength();
        artist = r.getArtistCredit().getArtist().getName();
        artistId = r.getArtistCredit().getArtist().getId();
        artistDisamb = r.getArtistCredit().getArtist().getDisambiguation();
        releaseId = r.getReleaseList().elementAt(0).getId();
        releaseTitle = r.getReleaseList().elementAt(0).getTitle();
        releaseDate = r.getReleaseList().elementAt(0).getDate();
        releaseCountry = r.getReleaseList().elementAt(0).getCountry();
        releaseTrack = r.getReleaseList().elementAt(0).getMediumList().elementAt(0).getTracklist()
                .elementAt(0).getNumber();
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDuration() {
        Date date = new Date(length / 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

    public String getArtistName() {
        return artist;
    }

    public String getDisambiguation() {
        return artistDisamb;
    }

    public int getDuration() {
        return length;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public String getReleaseCountry() {
        return releaseCountry;
    }

    public int getReleaseTrack() {
        return releaseTrack;
    }
}

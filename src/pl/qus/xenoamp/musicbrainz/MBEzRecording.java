package pl.qus.xenoamp.musicbrainz;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.format.DateFormat;

public class MBEzRecording {
	private String id;
	private String title;
	private int length;
	private String artist;
	private String artistId;
	private String artistDisamb;
	private String releaseId;
	private String releaseTitle;
	private String releaseDate;
	private String releaseCountry;
	int releaseTrack;
	private int likeness;

	public MBEzRecording(MBRecording r) {
		id = r.id;
		title = r.title;
		length = r.length;
		artist = r.artistCredit.nameCredit.artist.name;
		artistId = r.artistCredit.nameCredit.artist.id;
		artistDisamb = r.artistCredit.nameCredit.artist.disambiguation;
		try {
			releaseId = r.releaseList.elementAt(0).id;
			releaseTitle = r.releaseList.elementAt(0).title;
			releaseDate = r.releaseList.elementAt(0).date;
			releaseCountry = r.releaseList.elementAt(0).country;
			releaseTrack = r.releaseList.elementAt(0).mediumList.elementAt(0).tracklist
					.elementAt(0).number;
		} catch (Exception ex) {
		}
		;
	}

	public String getTitle() {
		return title;
	}

	public String getFormattedDuration() {
		Date date = new Date(length/1000);
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}

	public String getArtistName() {
		return artist;
	}

	public String getDisambiguation() {
		return artistDisamb;
	}

	public int getLikeness() {
		return likeness;
	}

	public int getDuration() {
		return length;
	}

	public void setLikeness(int l) {
		likeness=l;		
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
	
	public void setReleaseTrack(int i) {
		releaseTrack=i;
	}
}

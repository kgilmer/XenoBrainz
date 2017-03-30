package pl.qus.xenoamp.musicbrainz;

import javax.annotation.Nonnull;

/**
 * Created by kgilmer on 3/29/17.
 */
public class RecordingTerm {

    enum RecordingKey {
        ARID,
        ARTIST,
        ARTISTNAME,
        CREDITNAME,
        COMMENT,
        COUNTRY,
        DATE,
        DUR,
        FORMAT,
        ISRC,
        NUMBER,
        POSITION,
        PRIMARYTYPE,
        QDUR,
        RECORDING,
        RECORDINGACCENT,
        REID,
        RELEASE,
        RGID,
        RID,
        SECONDARYTYPE,
        STATUS,
        TID,
        TNUM,
        TRACKS,
        TRACKSRELEASE,
        TAG,
        TYPE,
        VIDEO;

        public RecordingTerm is(@Nonnull final String value) {
            if (value == null || value.length() == 0 || value.contains("\"")) {
                throw new IllegalArgumentException("Input empty or contains an illegal character.");
            }

            return new RecordingTerm(this.toString().toLowerCase() + ":\"" + value + "\"");
        }
    }

    private final String term;

    public RecordingTerm(final String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return term;
    }
}

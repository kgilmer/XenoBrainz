package pl.qus.xenoamp.musicbrainz;

import javax.annotation.Nonnull;

/**
 * All possible query terms for the Recording entity.
 */
public class RecordingTerm {

    /**
     * Recording terms
     */
    public enum RecordingKey {
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

        /**
         * Return term composed of name and value.
         *
         * @param value value for the given term.
         * @return term composed of name and value.
         */
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

package pl.qus.xenoamp.musicbrainz;

/**
 * All possible query terms for the Release entity.
 */
public class ReleaseTerm {

    /**
     * Query terms for releases.
     */
    public enum ReleaseKey {
        ARID,
        ARTIST,
        ARTISTNAME,
        ASIN,
        BARCODE,
        CATNO,
        COMMENT,
        COUNTRY,
        CREDITNAME,
        DATE,
        DISCIDS,
        DISCIDSMEDIUM,
        FORMAT,
        LAID,
        LABEL,
        LANG,
        MEDIUMS,
        PRIMARYTYPE,
        PUID,
        QUALITY,
        REID,
        RELEASE,
        RELEASEACCENT,
        RGID,
        SCRIPT,
        SECONDARYTYPE,
        STATUS,
        TAG,
        TRACKS,
        TRACKSMEDIUM,
        TYPE;

        /**
         * Return term composed of name and value.
         *
         * @param value value for the given term.
         * @return term composed of name and value.
         */
        public ReleaseTerm is(final String value) {
            if (value == null || value.length() == 0 || value.contains("\"")) {
                throw new IllegalArgumentException("Input empty or contains an illegal character.");
            }

            return new ReleaseTerm(this.toString().toLowerCase() + ":\"" + value + "\"");
        }
    }

    private final String term;

    public ReleaseTerm(final String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return term;
    }
}

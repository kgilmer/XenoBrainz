package pl.qus.xenoamp.musicbrainz;

/**
 * Created by kgilmer on 3/29/17.
 */
public class ReleaseTerm {

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

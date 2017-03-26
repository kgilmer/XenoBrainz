package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;
import pl.qus.xenoamp.musicbrainz.model.MBArtist;
import pl.qus.xenoamp.musicbrainz.model.MBLabelInfo;
import pl.qus.xenoamp.musicbrainz.model.MBRelease;
import pl.qus.xenoamp.musicbrainz.model.MBTag;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by kgilmer on 3/24/17.
 */
public class ReleaseTest {

    private static final String TEST_RELEASE_TITLE = "Dial 'M' for Monkey";
    private static final String TEST_RELEASE_ID = "54d3ad49-ce0e-4956-a0ee-f40b64078f8b";
    private static final String TEST_ASIN = "B00009MKQB";
    private static final String TEST_ARTIST_ID = "9a709693-b4f8-4da9-8cc1-038c911a61be";
    private static final String TEST_ARTIST_NAME = "Bonobo";
    private static final String TEST_ARTIST_SORT_NAME = "Bonobo";
    private static final String TEST_ARTIST_DISAMBIGUATION = "UK electro artist Simon Green";
    private static final int TEST_TAG_COUNT_VAL = 1;
    private static final String TEST_TAG_NAME = "british";
    private static final String TEST_RELEASE_DATE = "2003-06-09";
    private static final String TEST_RELEASE_COUNTRY = "GB";
    private static final String TEST_BARCODE = "5021392295121";


    @Test
    public void testGetRelease() throws Exception {
        List<MBRelease> releases = MusicBrainzClient.searchRelease("Who can you trust?");

        assertNotNull("Response not null.", releases);

        for (MBRelease release : releases) {
            assertNotNull("All releases have titles.", release.getTitle());
        }
    }

    @Test
    public void testFieldCompleteness() throws Exception {
        MBRelease release = MusicBrainzClient.lookupRelease(TEST_RELEASE_ID);

        assertNotNull("Release is found.", release);

        assertTrue("Expected title.", release.getTitle().equals(TEST_RELEASE_TITLE));
        assertTrue("Expected ASIN.", release.getAsin().equals(TEST_ASIN));
        assertNotNull("Release has an artist credit.", release.getArtistCredit());
        assertTrue("Expected joinphrase.", release.getArtistCredit().getJoinphrase() == null);
        assertNotNull("Release has an artist.", release.getArtistCredit().getArtist());
        assertTrue("Expected date.", release.getDate().equals(TEST_RELEASE_DATE));
        assertTrue("Expected country.", release.getCountry().equals(TEST_RELEASE_COUNTRY));
        assertTrue("Expected barcode.", release.getBarcode().equals(TEST_BARCODE));

        MBArtist artist = release.getArtistCredit().getArtist();
        assertTrue("Expected artist id.", artist.getId().equals(TEST_ARTIST_ID));
        assertTrue("Expected name", artist.getName().equals(TEST_ARTIST_NAME));
        assertTrue("Expected sort name", artist.getSortName().equals(TEST_ARTIST_SORT_NAME));
        assertTrue("Expected disambiguation.", artist.getDisambiguation().equals(TEST_ARTIST_DISAMBIGUATION));

        List<MBTag> tags = artist.getTagList();
        assertNotNull("Artist contains tags", tags);
        assertTrue("Expected tag count.", tags.size() == 8);

        for (MBTag tag : tags) {
            assertNotNull("Tag name not null.", tag.getName());
            assertTrue("Count value is positive.", tag.getCount() >= 0);
        }

        assertTrue("Expected tag value", tags.get(0).getName().equals(TEST_TAG_NAME));
        assertTrue("Non-zero tag value", tags.get(1).getCount() == TEST_TAG_COUNT_VAL);

        assertNotNull("Cover art archive", release.getCoverArtArchive());
        assertTrue("Expected cover art has artwork", release.getCoverArtArchive().hasArtwork());
        assertTrue("Expected cover art has front", release.getCoverArtArchive().hasFront());
        assertTrue("Expected cover art has back", release.getCoverArtArchive().hasBack());
        assertTrue("Expected cover art count", release.getCoverArtArchive().getCount() == 3);

        List<MBLabelInfo> labels = release.getLabelInfoList();
        assertNotNull("Release has labels.", labels);
        assertTrue("expected label count.", labels.size() == 1);
    }
}

package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;
import pl.qus.xenoamp.musicbrainz.model.MBRelease;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by kgilmer on 3/24/17.
 */
public class ReleaseTest {

    @Test
    public void testGetRelease() throws Exception {
        List<MBRelease> releases = MusicBrainzClient.searchRelease("Who can you trust?");

        assertNotNull("Response not null.", releases);

        for (MBRelease release : releases) {
            assertNotNull("All releases have titles.", release.getTitle());
        }
    }
}

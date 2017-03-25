package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;
import pl.qus.xenoamp.musicbrainz.model.MBRecording;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by kgilmer on 3/24/17.
 */
public class RecordingTest {

    @Test
    public void testGetRecording() throws Exception {
        List<MBRecording> recordingList = MusicBrainzClient.searchRecording("Noctuary");

        assertNotNull("MusicBrainzClient returns non-null response.", recordingList);
        assertFalse("Recording search returns results", recordingList.isEmpty());

        boolean tagsFound = false;
        for (MBRecording r : recordingList) {
            assertNotNull("recording has a title.", r.getTitle());

            if (!r.getTagList().isEmpty()) {
                tagsFound = true;
            }
        }

        assertTrue("At least one recording has tags.", tagsFound);
    }
}

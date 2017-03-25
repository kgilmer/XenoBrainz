package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kgilmer on 3/24/17.
 */
public class RecordingTest {

    @Test
    public void testGetRecording() throws Exception {
        MBRecordingList możliweUtwory = MBParser.searchRecording("Noctuary");

        assertNotNull("MBParser returns non-null response.", możliweUtwory);
        assertFalse("Recording search returns results", możliweUtwory.isEmpty());
    }
}

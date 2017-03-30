package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;
import pl.qus.xenoamp.musicbrainz.model.MBArtist;
import pl.qus.xenoamp.musicbrainz.model.MBArtistCredit;
import pl.qus.xenoamp.musicbrainz.model.MBRecording;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static pl.qus.xenoamp.musicbrainz.RecordingTerm.RecordingKey.*;

/**
 * Created by kgilmer on 3/24/17.
 */
public class RecordingTest {

    private static final String TEST_RECORDING_ID = "0beff006-2ceb-4a15-ad0f-6daf0976f005";
    private static final String TEST_RECORDING_TITLE = "Noctuary";
    private static final String TEST_ARTIST_NAME = "Bonobo";
    private static final String TEST_ARTIST_ID = "9a709693-b4f8-4da9-8cc1-038c911a61be";


    @Test
    public void testGetRecording() throws Exception {
        List<MBRecording> recordingList = (new MBClient()).searchRecording("Noctuary");

        assertNotNull("MBClient returns non-null response.", recordingList);
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

    /**
     <recording id="0beff006-2ceb-4a15-ad0f-6daf0976f005" ext:score="100">...</recording>
     <recording id="0cf7c199-1f10-4c15-a149-571e98df12be" ext:score="100">...</recording>
     <recording id="9b65ce09-6e14-4911-a462-7989ba586060" ext:score="100">...</recording>
     <recording id="c2a7a33f-681d-4c45-9e31-f13c83175477" ext:score="100">...</recording>
     <recording id="fb4276a8-7f8a-43cc-a1ea-afe5de1c487e" ext:score="100">...</recording>
     <recording id="649dd201-90b6-4030-9ade-309aed460b80" ext:score="100">...</recording>
     * @throws Exception
     */
    @Test
    public void testRecordingCompleteness() throws Exception {
        List<MBRecording> recordingList = (new MBClient()).searchRecording("Noctuary");

        assertTrue("Contains expected number of recordings.", recordingList.size() == 6);

        Set<String> recordingIdSet = new HashSet<>();
        for (MBRecording rec : recordingList) {
            recordingIdSet.add(rec.getId());
        }
        assertTrue("contains expected id", recordingIdSet.contains("0beff006-2ceb-4a15-ad0f-6daf0976f005"));
        assertTrue("contains expected id", recordingIdSet.contains("0cf7c199-1f10-4c15-a149-571e98df12be"));
        assertTrue("contains expected id", recordingIdSet.contains("9b65ce09-6e14-4911-a462-7989ba586060"));
        assertTrue("contains expected id", recordingIdSet.contains("c2a7a33f-681d-4c45-9e31-f13c83175477"));
        assertTrue("contains expected id", recordingIdSet.contains("fb4276a8-7f8a-43cc-a1ea-afe5de1c487e"));
        assertTrue("contains expected id", recordingIdSet.contains("649dd201-90b6-4030-9ade-309aed460b80"));

        MBRecording recording = recordingList.get(0);
        assertTrue("First recording has expected id.", recording.getId().equals(TEST_RECORDING_ID));

        assertTrue("Recording has expected id", recording.getTitle().equals(TEST_RECORDING_TITLE));
        assertTrue("Recording has expected length", recording.getLength() == 322373);
        assertNotNull("Recording has associated artist", recording.getArtistCredit());

        MBArtistCredit artistCredit = recording.getArtistCredit();
        assertNotNull("Artist Credit has non-null artist.", artistCredit.getArtist());
        assertNull("Artist credit has no join", artistCredit.getJoinphrase());
        MBArtist artist = artistCredit.getArtist();
        assertTrue("Artist has expected name", artist.getName().equals(TEST_ARTIST_NAME));
        assertTrue("Artist has expected id", artist.getId().equals(TEST_ARTIST_ID));
        
    }

    @Test
    public void testMultiTermQuery() throws Exception {
        MBClient mbClient = new MBClient();

        List<MBRecording> results = mbClient
                .searchRecording(
                        ARTIST.is("the police"),
                        RECORDING.is("mother"),
                        RELEASE.is("Synchronicity"));

        assertNotNull("Non-null return", results);
        assertTrue("Some results returned", results.size() > 0);
    }
}

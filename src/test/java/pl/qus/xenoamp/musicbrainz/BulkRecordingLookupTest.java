package pl.qus.xenoamp.musicbrainz;

import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import org.junit.Before;
import org.junit.Test;
import pl.qus.xenoamp.musicbrainz.model.MBRecording;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by kgilmer on 3/25/17.
 */
public class BulkRecordingLookupTest {

    private HashSet<String> noResultsTitleSet;

    @Before
    public void setup() {
        noResultsTitleSet = new HashSet<>();
        noResultsTitleSet.add("Nights in New York City");
        noResultsTitleSet.add("The Ballad of Sharpeville");
        noResultsTitleSet.add("Me and Giuliani Down By the Schoolyard (A True Story)");
        noResultsTitleSet.add("Kehma Hi Kya");
        noResultsTitleSet.add("Numbers/Computer World 2");
        noResultsTitleSet.add("Streets of Sorrow/Birmingham Six");
        noResultsTitleSet.add("Will the Circle Be Unbroken (By and By)");
        noResultsTitleSet.add("All the Blowing-Themselves-Up Motherfuckers (Will Realise the Minute They Die That They Were Suckers)");
        noResultsTitleSet.add("I Want My Cock");
        noResultsTitleSet.add("Think About It (What Is Wrong With the World Today)");
    }


    @Test
    public void top1000SongTest() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/songs.csv");
        assertNotNull("Load test resource", inputStream);
        List<String> lines = CharStreams.readLines(
                new InputStreamReader(
                        inputStream));

        assertNotNull("read input lines", lines);
        Random rnd = new Random();
        MBClient client = new MBClient();

        while (!lines.isEmpty()) {
            String line = lines.remove(rnd.nextInt(lines.size()));
            if (line.startsWith("#")) {
                continue;
            }

            Iterator<String> lineTokens = Splitter.on(',').split(line).iterator();

            // Love,The Look of Love,ABC,1982,http://open.spotify.com/track/78j3qTBdzcIiT3eS7XymoD
            String theme = lineTokens.next();
            String title = lineTokens.next();
            String artist = lineTokens.next();
            String year = lineTokens.next();

            try {
                List<MBRecording> recordings = client.searchRecording(title);

                assertNotNull("recordings not null", recordings);

                if (noResultsTitleSet.contains(title)) {
                    assertTrue("MusicBrainz has no data.", recordings.isEmpty());
                    continue;
                } else {
                    assertFalse("At least one recording returned per query", recordings.isEmpty());
                }

                for (MBRecording recording : recordings) {
                    assertNotNull("Recording has a title", recording.getTitle());
                }

            } catch (IllegalArgumentException e) {
                if (title == null || title.trim().length() == 0 || title.contains("\"")) {
                    //Expected error based in bad input.
                    continue;
                } else {
                    throw e;
                }
            }

            //Required to prevent throttling from MB servers.
            Thread.sleep(500);
        }
    }
}

package pl.qus.xenoamp.musicbrainz;

import org.junit.Test;

/**
 * Created by kgilmer on 3/25/17.
 */
public class ClientTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputCharactersTest() throws Exception {
        MBClient client = new MBClient();

        client.searchRecording("\"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInputTest() throws Exception {
        MBClient client = new MBClient();

        client.searchRecording((String) null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void emptyInputTest() throws Exception {
        MBClient client = new MBClient();

        client.searchRecording("");
    }

}

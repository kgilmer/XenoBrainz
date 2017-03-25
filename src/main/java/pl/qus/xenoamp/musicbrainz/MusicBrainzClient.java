package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import pl.qus.xenoamp.musicbrainz.model.MBRecording;
import pl.qus.xenoamp.musicbrainz.model.MBRelease;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MusicBrainzClient {

    private static InputStream getStream(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/xml");

        return con.getInputStream();
    }

    public static List<MBRelease> searchRelease(String name) {
        String query;
        try {
            query = "http://www.musicbrainz.org/ws/2/release?query="
                    + URLEncoder.encode("\"" + name + "\"", "UTF-8") + "";

            InputStream is = getStream(query);
            Element whole = MusicBrainzClient.inputStreamToElement(is);
            Element reclist = whole.getChild("release-list",
                    whole.getNamespace());

            List<Element> elementy = reclist.getChildren();
            List<MBRelease> releaseList = new ArrayList<>();

            for (Element child : elementy) {
                if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
            }

            return releaseList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<MBRelease> searchReleaseByBarcode(String name) throws IOException {
        String query;
        query = "http://www.musicbrainz.org/ws/2/release/?query=barcode:"
                + name;

        InputStream is = getStream(query);
        Element whole = MusicBrainzClient.inputStreamToElement(is);
        Element reclist = whole.getChild("release-list",
                whole.getNamespace());

        List<Element> elementy = reclist.getChildren();
        List<MBRelease> releaseList = new ArrayList<>();

        for (Element child : elementy) {
            if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
        }

        return releaseList;
    }

    public static List<MBRecording> searchRecording(String name) throws IOException {
        String query = "http://www.musicbrainz.org/ws/2/recording?query=" + URLEncoder.encode("\"" + name + "\"", "UTF-8");

        InputStream is = getStream(query);
        Element whole = MusicBrainzClient.inputStreamToElement(is);
        Element reclist = whole.getChild("recording-list",
                whole.getNamespace());

        List<Element> elementy = reclist.getChildren();
        List<MBRecording> recordingList = new ArrayList<>();
        for (Element child : elementy) {
            if (child.getName().equals("recording")) recordingList.add(new MBRecording(child));
        }

        return recordingList;
    }

    public static MBRelease lookupRelease(String id) throws IOException {
        String query = "http://www.musicbrainz.org/ws/2/release/" + id
                + "?inc=media+tags+artist-credits+labels+recordings";
        InputStream is = getStream(query);
        Element whole = MusicBrainzClient.inputStreamToElement(is);
        Element release = whole.getChild("release", whole.getNamespace());
        return new MBRelease(release);
    }

    public static MBRecording lookupRecording(String id) throws IOException {
        String query = "http://musicbrainz.org/ws/2/recording/" + id
                + "?inc=tags+artist-credits";
        InputStream is = getStream(query);
        Element whole = MusicBrainzClient.inputStreamToElement(is);
        Element recording = whole.getChild("recording", whole.getNamespace());
        return new MBRecording(recording);
    }

    // release with recording (zwraca release-list)
    // http://musicbrainz.org//ws/2/release?recording=0beff006-2ceb-4a15-ad0f-6daf0976f005

    public static List<MBRelease> findReleaseWithRecording(String id) throws IOException {
        String query = "http://musicbrainz.org//ws/2/release?recording=" + id;

        InputStream is = getStream(query);
        Element whole = MusicBrainzClient.inputStreamToElement(is);
        Element reclist = whole.getChild("release-list",
                whole.getNamespace());

        List<Element> elementy = reclist.getChildren();
        List<MBRelease> releaseList = new ArrayList<>();

        for (Element child : elementy) {
            if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
        }

        return releaseList;
    }

    // zwraca liste tracków na płycie, bezużyteczne (recording-list)
    // http://musicbrainz.org//ws/2/recording?release=54d3ad49-ce0e-4956-a0ee-f40b64078f8b

    public static Element inputStreamToElement(InputStream is) throws IOException {
        SAXBuilder builder = new SAXBuilder();

        Document doc;

        try {
            doc = builder.build(is);
            return doc.getRootElement();
        } catch (JDOMException e) {
            throw new IOException("Parse failure.", e);
        }
    }
}

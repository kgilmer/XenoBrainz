package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import pl.qus.xenoamp.musicbrainz.model.MBRecording;
import pl.qus.xenoamp.musicbrainz.model.MBRelease;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Client to access MusicBrainz music database.  See http://musicbrainz.org/ for details.
 */
public class MBClient {

    private final HTTPGetDataLoader httpUrlConnectionLoader;

    /**
     * Makes network requests to MB server.
     */
    public interface HTTPGetDataLoader {
        @Nonnull InputStream getStream(@Nonnull final String url) throws IOException;
    }

    public MBClient(@Nonnull final HTTPGetDataLoader httpUrlConnectionLoader) {
        this.httpUrlConnectionLoader = httpUrlConnectionLoader;
    }

    public MBClient() {
        this(HTTP_URL_CONNECTION_LOADER);
    }

    /**
     * Search releases.
     *
     * @param name must be non-null, non-empty.  Cannot contain " character.
     * @return List of 0 or more MBRecordings
     * @throws IOException and IllegalArgumentException
     */
    public @Nonnull List<MBRelease> searchRelease(@Nonnull final String name) throws IOException {
        guardInput(name);
        final String query = "http://www.musicbrainz.org/ws/2/release?query="
                + URLEncoder.encode("\"" + name + "\"", "UTF-8") + "";

        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);

        if (whole != null) {
            final Element releaseNode = whole.getChild("release-list",
                    whole.getNamespace());

            final List<Element> releaseChildren = releaseNode.getChildren();
            final List<MBRelease> releaseList = new ArrayList<>();

            for (final Element child : releaseChildren) {
                if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
            }

            return releaseList;
        }

        return Collections.emptyList();
    }

    /**
     * @param name must be non-null, non-empty.  Cannot contain " character.
     * @return List of 0 or more MBRecordings
     * @throws IOException and IllegalArgumentException
     */
    public @Nonnull List<MBRelease> searchReleaseByBarcode(@Nonnull final String name) throws IOException {
        guardInput(name);

        final String query = "http://www.musicbrainz.org/ws/2/release/?query=barcode:" + name;

        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);
        if (whole != null) {
            final Element reclist = whole.getChild("release-list",
                    whole.getNamespace());

            final List<Element> elementy = reclist.getChildren();
            final List<MBRelease> releaseList = new ArrayList<>();

            for (final Element child : elementy) {
                if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
            }

            return releaseList;
        }

        return Collections.emptyList();
    }

    /**
     * Search recordings.
     *
     * @param name must be non-null, non-empty.  Cannot contain " character.
     * @return List of 0 or more MBRecordings
     * @throws IOException and IllegalArgumentException
     */
    public @Nonnull List<MBRecording> searchRecording(@Nonnull final String name) throws IOException {
        guardInput(name);
        final String query = "http://www.musicbrainz.org/ws/2/recording?query=" + URLEncoder.encode("\"" + name + "\"", "UTF-8");

        System.out.println("query: " + query);
        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);
        if (whole != null) {
            final Element reclist = whole.getChild("recording-list",
                    whole.getNamespace());

            final List<Element> elementy = reclist.getChildren();
            final List<MBRecording> recordingList = new ArrayList<>();
            for (final Element child : elementy) {
                if (child.getName().equals("recording")) recordingList.add(new MBRecording(child));
            }

            return recordingList;
        }

        return Collections.emptyList();
    }

    /**
     * Guard input values.
     *
     * @param name input
     */
    private static void guardInput(@Nonnull final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        if (name.contains("\"")) {
            throw new IllegalArgumentException("Input contains an illegal character.");
        }
    }

    /**
     * Perform release lookup based on id
     *
     * @param id must be non-null, non-empty.  Cannot contain " character.
     * @return List of 0 or more MBRecordings
     * @throws IOException and IllegalArgumentException
     */
    public @Nullable MBRelease lookupRelease(@Nonnull final String id) throws IOException {
        final String query = "http://www.musicbrainz.org/ws/2/release/" + id
                + "?inc=media+tags+artist-credits+labels+recordings";
        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);
        if (whole != null && whole.getChild("release", whole.getNamespace()) != null) {
            final Element release = whole.getChild("release", whole.getNamespace());

            return new MBRelease(release);
        }

        return null;
    }

    /**
     * Lookup recording by id
     * @param id id of recording
     * @return MBRecording or null if not found.
     * @throws IOException and IllegalArgumentException
     */
    public @Nullable MBRecording lookupRecording(@Nonnull final String id) throws IOException {
        final String query = "http://musicbrainz.org/ws/2/recording/" + id
                + "?inc=tags+artist-credits";
        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);

        if (whole != null && whole.getChild("recording", whole.getNamespace()) != null) {
            final Element recording = whole.getChild("recording", whole.getNamespace());
            return new MBRecording(recording);
        }

        return null;
    }

    // release with recording (zwraca release-list)
    // http://musicbrainz.org//ws/2/release?recording=0beff006-2ceb-4a15-ad0f-6daf0976f005

    /**
     * Find releases with recording of input id
     * @param id id of recording
     * @return List of releases or empty list if none found.
     * @throws IOException and IllegalArgumentException
     */
    public @Nonnull List<MBRelease> findReleaseWithRecording(@Nonnull final String id) throws IOException {
        final String query = "http://musicbrainz.org//ws/2/release?recording=" + id;

        final InputStream is = httpUrlConnectionLoader.getStream(query);
        final Element whole = MBClient.inputStreamToElement(is);

        if (whole != null && whole.getChild("release-list", whole.getNamespace()) != null) {
            final Element reclist = whole.getChild("release-list",
                    whole.getNamespace());

            final List<Element> elementy = reclist.getChildren();
            final List<MBRelease> releaseList = new ArrayList<>();

            for (final Element child : elementy) {
                if (child.getName().equals("release")) releaseList.add(new MBRelease(child));
            }

            return releaseList;
        }

        return Collections.emptyList();
    }

    /**
     * Default {@link HTTPGetDataLoader} implementation using HttpURLConnection.
     */
    private static HTTPGetDataLoader HTTP_URL_CONNECTION_LOADER = new HTTPGetDataLoader() {
        @Nonnull
        @Override
        public InputStream getStream(@Nonnull final String url) throws IOException {
            final URL obj = new URL(url);
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/xml");

            return con.getInputStream();
        }
    };

    // zwraca liste tracków na płycie, bezużyteczne (recording-list)
    // http://musicbrainz.org//ws/2/recording?release=54d3ad49-ce0e-4956-a0ee-f40b64078f8b

    private static Element inputStreamToElement(final InputStream is) {
        final SAXBuilder builder = new SAXBuilder();

        //TODO: Add error listener callback facility.

        try {
            final Document doc = builder.build(is);
            return doc.getRootElement();
        } catch (final JDOMException e) {
            e.printStackTrace();
            //throw new IOException("Parse failure.", e);
            return null;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

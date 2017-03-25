package pl.qus.xenoamp.musicbrainz;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class MBParser {

	public static Namespace MBNamespace;

	private static InputStream getStream(String u) throws ClientProtocolException, IOException
	{
		URI url=URI.create(u);
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		response = httpclient.execute(httpGet);
		return response.getEntity().getContent();
	}
	
	public static MBReleaseList searchRelease(String name) {
		String query;
		try {
			query = "http://www.musicbrainz.org/ws/2/release?query="
					+ URLEncoder.encode("\""+name+"\"", "UTF-8")+"";

			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element reclist = whole.getChild("release-list",
					whole.getNamespace());
			return new MBReleaseList(reclist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MBReleaseList searchReleaseByBarcode(String name) {
		String query;
		try {
			query = "http://www.musicbrainz.org/ws/2/release/?query=barcode:"
					+ name;

			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element reclist = whole.getChild("release-list",
					whole.getNamespace());
			return new MBReleaseList(reclist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MBRecordingList searchRecording(String name) {
		String query;
		try {
			query = "http://www.musicbrainz.org/ws/2/recording?query=\""
					+ URLEncoder.encode(name, "UTF-8")+"\"";

			query = "http://www.musicbrainz.org/ws/2/recording?query="+URLEncoder.encode("\""+name+"\"", "UTF-8");
			
			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element reclist = whole.getChild("recording-list",
					whole.getNamespace());
			return new MBRecordingList(reclist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MBRelease lookupRelease(String id) {
		try {
			String query = "http://www.musicbrainz.org/ws/2/release/" + id
					+ "?inc=media+tags+artist-credits+labels+recordings";
			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element release = whole.getChild("release", whole.getNamespace());
			return new MBRelease(release);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static MBRecording lookupRecording(String id) {
		try {
			String query = "http://musicbrainz.org/ws/2/recording/" + id
					+ "?inc=tags+artist-credits";
			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element recording = whole.getChild("recording", whole.getNamespace());
			return new MBRecording(recording);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	// release with recording (zwraca release-list)
	// http://musicbrainz.org//ws/2/release?recording=0beff006-2ceb-4a15-ad0f-6daf0976f005

	public static MBReleaseList findReleaseWithRecording(String id)
	{
		String query;
		try {
			query = "http://musicbrainz.org//ws/2/release?recording="+id;

			InputStream is=getStream(query);
			Element whole = MBParser.inputStreamToElement(is);
			Element reclist = whole.getChild("release-list",
					whole.getNamespace());
			return new MBReleaseList(reclist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// zwraca liste tracków na płycie, bezużyteczne (recording-list)
	// http://musicbrainz.org//ws/2/recording?release=54d3ad49-ce0e-4956-a0ee-f40b64078f8b

	public static Element inputStreamToElement(InputStream is) {
		SAXBuilder builder = new SAXBuilder();

		Document doc;

		try {
			doc = builder.build(is);
			Element el = doc.getRootElement();
			MBNamespace = el.getNamespace();
			return el;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Element stringToElement(String xml) {
		SAXBuilder builder = new SAXBuilder();

		Document doc;
		try {
			doc = builder.build(new StringReader(xml));
			Element el = doc.getRootElement();
			MBNamespace = el.getNamespace();
			return el;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Vector<MBEzRecording> convertToEz(MBRecordingList l)
	{
		Vector<MBEzRecording> zwrotka=new Vector<MBEzRecording>();
		
		for(MBRecording r:l)
		{
			zwrotka.add(new MBEzRecording(r));
		}
		
		return zwrotka;
	}
	
	public static Vector<MBEzRelease> convertToEz(MBReleaseList l)
	{
		Vector<MBEzRelease> zwrotka=new Vector<MBEzRelease>();
		
		for(MBRelease r:l)
		{
			zwrotka.add(new MBEzRelease(r));
		}
		
		return zwrotka;		
	}
}

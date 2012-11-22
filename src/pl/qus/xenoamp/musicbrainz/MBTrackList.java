package pl.qus.xenoamp.musicbrainz;

import java.util.List;
import java.util.Vector;

import org.jdom2.Element;

// <medium><position>1</position><format>CD</format> [tracklist] </medium>

// pozycja danego medium, nie track

public class MBTrackList extends Vector<MBTrack> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4640620317036209121L;

	//List<MBTrack> tracks;
	
	public MBTrackList(Element e)
	{
		List<Element> elementy = e.getChildren();
		//tracks = new Vector<MBTrack>();
		for(Element child:elementy)
		{
			if(child.getName().equals("track")) add(new MBTrack(child));
		}
	}
	
}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBMedium {
	int position;
	String format;
	MBTrackList tracklist;
	MBDiscList discList;
	
	public MBMedium(Element e)
	{
		try{position = Integer.parseInt(e.getChild("position",e.getNamespace()).getValue());} catch(Exception ex) {};
		try{format = e.getChild("format",e.getNamespace()).getValue();} catch(Exception ex) {};
		try{tracklist = new MBTrackList(e.getChild("track-list",e.getNamespace()));} catch(Exception ex) {};
		try{discList = new MBDiscList(e.getChild("disc-list",e.getNamespace()));} catch(Exception ex) {};
		
	}
	
	@Override
	public String toString()
	{
		String zwrotka="[MEDIUM] Pos:"+position+" format:"+format+"\n"+tracklist.toString();
		return zwrotka;
	}
}

package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBTextRepresentation {
	String language;
	String script;
	
	public MBTextRepresentation(Element e)
	{
		try{language = e.getChild("language",e.getNamespace()).getValue();} catch(Exception ex) {};
		try{script = e.getChild("script",e.getNamespace()).getValue();} catch(Exception ex) {};
	}
	
	@Override 
	public String toString()
	{
		return "[TEXTREPRESENTATION] "+language+" "+script;
	}

}

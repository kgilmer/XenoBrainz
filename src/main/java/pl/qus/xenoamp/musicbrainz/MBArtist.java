package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBArtist {
	String name;
	String sortName;
	String disambiguation;
	String id;
	
	public MBArtist(Element e)
	{
		id=e.getAttributeValue("id");
		name=e.getChild("name",e.getNamespace()).getValue();
		try{sortName=e.getChild("sort-name",e.getNamespace()).getValue();} catch(Exception ex) {};
		try{disambiguation=e.getChild("disambiguation",e.getNamespace()).getValue();} catch(Exception ex) {};
	}
	
	@Override
	public String toString()
	{
		return "[ARTIST] "+id+" "+name+" "+disambiguation;
	}
}

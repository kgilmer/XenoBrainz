package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBNameCredit {
	String joinphrase;
	MBArtist artist;
	
	public MBNameCredit(Element e)
	{
		try{joinphrase=e.getAttributeValue("joinphrase",e.getNamespace());} catch(Exception ex) {};
		try{artist=new MBArtist(e.getChild("artist",e.getNamespace()));} catch(Exception ex) {};
	}
	
	@Override
	public String toString()
	{
		return "[NAMECREDIT] "+artist+" "+joinphrase;
	}
}

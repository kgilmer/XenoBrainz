package pl.qus.xenoamp.musicbrainz;

import org.jdom2.Element;

public class MBArtistCredit {
	MBNameCredit nameCredit;
	
	public MBArtistCredit(Element e)
	{
		try{nameCredit=new MBNameCredit(e.getChild("name-credit",e.getNamespace()));} catch(Exception ex) {};
	}
	
	@Override
	public String toString()
	{
		return "[ARTISTCREDIT] "+nameCredit.toString();
	}
}

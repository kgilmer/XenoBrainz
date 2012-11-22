package pl.qus.xenoamp.musicbrainz;

import java.util.List;
import java.util.Vector;

import org.jdom2.Element;

public class MBReleaseList extends Vector<MBRelease> {
	//List<MBRelease> releases;
	
	public MBReleaseList(Element e)
	{
		//releases=new Vector<MBRelease>();
		
		List<Element> elementy = e.getChildren();

		for(Element child:elementy)
		{
			if(child.getName().equals("release")) add(new MBRelease(child));
		}

	}
}

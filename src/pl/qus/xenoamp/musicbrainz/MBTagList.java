package pl.qus.xenoamp.musicbrainz;

import java.util.List;
import java.util.Vector;

import org.jdom2.Element;

public class MBTagList extends Vector<MBTag>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7347169552391078928L;

	public MBTagList(Element e)
	{
		List<Element> elementy = e.getChildren();

		for(Element child:elementy)
		{
			if(child.getName().equals("tag")) add(new MBTag(child));
		}
	}

}

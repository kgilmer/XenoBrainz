package pl.qus.xenoamp.musicbrainz;

import java.util.List;
import java.util.Vector;

import org.jdom2.Element;

public class MBRecordingList extends Vector<MBRecording> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1786132477381090831L;

	//List<MBRecording> recordings;
	
	public MBRecordingList(Element e)
	{
		//recordings=new Vector<MBRecording>();
		
		List<Element> elementy = e.getChildren();

		for(Element child:elementy)
		{
			if(child.getName().equals("recording")) add(new MBRecording(child));
		}

	}
}

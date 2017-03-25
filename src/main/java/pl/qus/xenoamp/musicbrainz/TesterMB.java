package pl.qus.xenoamp.musicbrainz;

import java.util.Vector;

public class TesterMB {

	public static void test() {

		Runnable ranejbl = new Runnable() {

			public void run() {
				MBRecordingList możliweUtwory=MBParser.searchRecording("Noctuary");
				Vector<MBEzRecording> płyty=MBParser.convertToEz(możliweUtwory);
				
				MBRelease wybrana=MBParser.lookupRelease(płyty.elementAt(0).getReleaseId());

				MBEzRelease moja=new MBEzRelease(wybrana);
				
				System.out.println("dwed"+moja);
			}
		};

		new Thread(ranejbl).start();

	}
}

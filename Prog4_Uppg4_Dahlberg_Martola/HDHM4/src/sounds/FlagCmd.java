package sounds;

import java.util.Iterator;
import java.util.List;

public class FlagCmd implements Command  {

	private MusicOrganizerController contr;
	private List<SoundClip> sc;
	
	public FlagCmd(List<SoundClip> sc,  MusicOrganizerController c) {
		this.sc = sc;
		this.contr = c;
	}
	
	public void undo() {
		//sc.setFlagged(!sc.getBoolflag());	
		Iterator<SoundClip>it = sc.iterator();
		while(it.hasNext()) {
			SoundClip w = it.next();
			w.setFlagged();	
		}
		contr.updateFlag(sc);
	}
	
	
	public void redo() {
		//sc.setFlagged(!sc.getBoolflag());	
		Iterator<SoundClip>it = sc.iterator();
		while(it.hasNext()) {
			SoundClip w = it.next();
			w.setFlagged();	
		}		
		contr.updateFlag(sc);

	}
	
	
	public void execute() {
		Iterator<SoundClip>it = sc.iterator();
		while(it.hasNext()) {
			SoundClip w = it.next();
			w.setFlagged();	
		}
		contr.updateFlag(this.sc);
	}





}


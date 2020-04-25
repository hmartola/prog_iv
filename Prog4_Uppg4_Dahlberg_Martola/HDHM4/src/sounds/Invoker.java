package sounds;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Invoker {
	
	private Stack<Command> undo;
	private Stack<Command> redo;
	private MusicOrganizerController contr;
	private SoundClip sc;
	
	public Invoker(MusicOrganizerController c) {
		contr = c;
		undo = new Stack<Command>();
		redo = new Stack<Command>();
	}
	
	public void exeUndo() {
		
		if (undo.isEmpty()) {
			System.out.println("Undo stack is empty");
		} else {
			Command cmd = undo.pop();
			cmd.undo();
			redo.push(cmd);
		}
	}
	
	public void exeRedo() {
		
		if (redo.isEmpty()) {
			System.out.println("Redo stack is empty");
		} else {
			Command cmd = redo.pop();
			cmd.redo();
			undo.push(cmd);
		}
	}
	
	public void addNewAlbum(String name) {
		
		Command cmd = new AddedAlbumCmd(contr,name);
		cmd.execute();
        undo.push(cmd);
        redo.clear();
	}
	
	public void deleteAlbum(Album album) {
		
		Command cmd = new RemoveAlbumCmd(contr,album);
		cmd.execute();
        undo.push(cmd);
        redo.clear();
	}
	
	public void addSoundClips() {
		
		Command cmd = new AddedSoundClipCmd(contr);
		cmd.execute();
        undo.push(cmd);
        redo.clear();
	}
	
	public void removeSoundClips() {
		
		Command cmd = new RemoveSoundClipCmd(contr);
		cmd.execute();
        undo.push(cmd);
        redo.clear();
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}

	public void flag(List<SoundClip> sc) {
		
		Command cmd = new FlagCmd(sc, contr); 
		cmd.execute();
		undo.push(cmd);
		redo.clear();
		
		}

	public void rate(List<SoundClip> sc, int x, Set<SoundClip> set) {
		Command cmd = new RateCmd(sc, contr,x,set); 
		cmd.execute();
		undo.push(cmd);
		redo.clear();
			
		
	}
	}


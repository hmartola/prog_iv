package sounds;

import java.util.List;

public class RemoveSoundClipCmd implements Command {
	
	private MusicOrganizerController contr;
	private List<SoundClip> sc;
	private AbstractAlbum album;
	
	public RemoveSoundClipCmd(MusicOrganizerController c) {
		this.contr = c;
		List<SoundClip> sc;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		contr.addSoundClips(album, sc);
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		contr.removeSoundClips(album, sc);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		sc = contr.removeSoundClips();
		album = contr.selectAlbum();
	}

}

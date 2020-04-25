package sounds;

public class AddedAlbumCmd implements Command {
	
	private MusicOrganizerController contr;
	private Album newAlbum;
	private Album currAlbum;
	private String name;
	
	public AddedAlbumCmd(MusicOrganizerController c, String name) {
		this.contr = c;
		this.name=name;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
		contr.deleteAlbum(newAlbum);
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
		contr.addNewAlbum(currAlbum, newAlbum);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		this.newAlbum = contr.addNewAlbum(name);
		currAlbum = newAlbum.getParentAlbum();
	}

	
}

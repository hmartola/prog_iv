package sounds;

public class RemoveAlbumCmd implements Command {
	
	private MusicOrganizerController contr;
    private Album currAlbum;
    private Album parentAlbum;

    public RemoveAlbumCmd (MusicOrganizerController c, Album album){
        this.contr = c;
        this.currAlbum=album;
        this.parentAlbum=album.getParentAlbum();
    }
    


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		contr.setKid(parentAlbum, currAlbum);
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		contr.deleteAlbum(currAlbum);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		contr.deleteAlbum();
	}

}

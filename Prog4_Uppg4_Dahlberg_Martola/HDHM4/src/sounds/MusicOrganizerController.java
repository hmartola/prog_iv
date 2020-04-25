 package sounds;
import java.util.List;
import java.util.Set;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private FlaggedAlbum flagged;
	private RateAlbum best;
	
	
	public MusicOrganizerController() {
		
		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		best = new RateAlbum("Great Sound Clips",root);
		flagged = new FlaggedAlbum("Flagged Sound Clips",root);
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		System.out.println(root);
		System.out.println(best);

		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
		view.onAlbumAdded(flagged);
		view.onAlbumAdded(best);
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album
		for (SoundClip s : clips) {
			root.addSound(s);
		}
		
		return clips;
	}
	
	/**
	 * Returns the root album
	 * @return 
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	public AbstractAlbum getBestAlbum() {
		return best;
	}
	
	public AbstractAlbum getFlaggedAlbum() {
		return flagged;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(Album a, Album b) {
		a.addAlbum(b);
		view.onAlbumAdded(b);
	}
	
	
	public Album addNewAlbum(String name){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		
			//String name = view.promptForAlbumName();
																	// Skapar ny album d� man trycker �ok�, oberoende p� inputen
			Album add = new Album(name, (Album)view.getSelectedAlbum());
			view.onAlbumAdded(add);
			return add;	
				
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public Album deleteAlbum(Album a) {
		view.onAlbumRemoved(a);
		view.onClipsUpdatedNull();
		Album alb = a;
		a.getParentAlbum().deleteAlbum(a);
		return alb;
	}
	
	
	public Album deleteAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here
		
						
		Album delete = (Album)view.getSelectedAlbum();
		
		if (delete.equals(root)) {								
			view.showMessage("Cannot remove root album");		// Kollar att roten inte raderas
		} else {
			
			delete.deleteAlbum(delete);
			view.onAlbumRemoved(delete);				// raderar albumet och uppdaterar tr�det
			view.onClipsUpdatedNull();
		}
		return delete;
	}
	
	public void setKid(Album par, Album kid) {
		par.addAlbum(kid);
		refreshGUI(kid);
	}
	
	public void refreshGUI(AbstractAlbum a) {
		view.onAlbumAdded(a);
		for (AbstractAlbum b : a.getAlbums()) {
			refreshGUI(b);
		}
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public List<SoundClip> addSoundClips(){ //TODO Update parameters if needed
	    	
		AbstractAlbum r = view.getSelectedAlbum();
	    List<SoundClip> x = view.getSelectedSoundClips();
	    
	    for(SoundClip y : x) {				// G�r igenom en eller flera soundclips och
	    	r.addSound(y);					// l�gger dem till det valda albumet
	    	
	    }
	    view.onClipsUpdated();				// Uppdaterar h�gra f�nstret
	    return x;
	   
	}
	
	public void addSoundClips(AbstractAlbum a, List<SoundClip> sc) {
		a.addSound(sc);
		view.onClipsUpdated();
	}
	  
	  /**
	   * Removes sound clips from an album
	 * @return 
	   */
	  public List<SoundClip> removeSoundClips(){ //TODO Update parameters if needed
		
		AbstractAlbum r = view.getSelectedAlbum();
	    List<SoundClip> x = view.getSelectedSoundClips();
	    
	    for(SoundClip y : x) {				// Samma princip som i addSoundClips men raderar ljudklipparna
	    	r.deleteSounds(y);
	    	
	    }
	    view.onClipsUpdated();				// Uppdaterar h�gra f�nstret 
	    return x;

	  }
	  
	  public void removeSoundClips(AbstractAlbum a, List<SoundClip> sc) {
		  
		  for (SoundClip s : sc) {
			  if (a.getSounds().contains(s)) {
				  a.deleteSounds(s);
			  }
		  }
		  view.onClipsUpdated();
	  }
	
	  public AbstractAlbum selectAlbum() {
		  return view.getSelectedAlbum();
	  }
	  
	  public void updateFlag(List<SoundClip> sc) {
		  flagged.checkSound(sc);
		  view.onClipsUpdated();
	  }
	  public void updateRate(List<SoundClip> sc) {
		  best.checkSound(sc);
		  view.onClipsUpdated();
	  }
	  
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}
	
	
}

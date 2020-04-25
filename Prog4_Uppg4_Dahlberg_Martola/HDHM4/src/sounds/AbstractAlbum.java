package sounds;

import java.util.List;
import java.util.Set;

public abstract class AbstractAlbum {
	
	
	 public  String name;
	  Set<SoundClip> sounds;
	  AbstractAlbum parent;
	
	
	AbstractAlbum(String name){
	this.name=name;
	}
	  
	public void addSound(SoundClip s) {
		
	}
	
	public void deleteSounds(SoundClip sound) {
	}
	
	public abstract void specialAlbums(SoundClip s);

	public Album getParentAlbum() {
		return (Album) this.parent;
	}

	protected abstract Set<SoundClip> getSounds();

	protected abstract void addSound(List<SoundClip> sc2);



	protected abstract Set<AbstractAlbum> getAlbums();

	protected abstract String getName();
	
	public String toString() {
		   return this.name;
		 }
	
}

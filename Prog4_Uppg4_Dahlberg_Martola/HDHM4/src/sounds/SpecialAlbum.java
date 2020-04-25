package sounds;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class SpecialAlbum extends AbstractAlbum{
	
	public SpecialAlbum(String name, Album root) {
		super(name);
		this.parent=root;
		this.sounds=new HashSet<>();//inne ocks� Sounds
		
	}

	@Override
	public void specialAlbums(SoundClip s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Set<SoundClip> getSounds() {
		return sounds;
		
	}
	 public Album getParentAlbum() {
		 System.out.println(parent);
		 return (Album) parent;
	 }

	@Override
	protected void addSound(List<SoundClip> sc) {
		
		Iterator<SoundClip>it = sc.iterator();
		while(it.hasNext()) {//
		SoundClip h = it.next();
		if (h.getRating() > 3)
		sounds.add(h);
		}
		 }

	@Override
	protected Set<AbstractAlbum> getAlbums() {
	
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}


	

}

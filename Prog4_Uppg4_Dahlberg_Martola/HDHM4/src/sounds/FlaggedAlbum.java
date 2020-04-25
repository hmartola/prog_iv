package sounds;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class FlaggedAlbum extends SpecialAlbum {

	public FlaggedAlbum(String name, Album root) {
		super(name, root);
		this.sounds=new HashSet<>();
	}

	public void checkSound(List<SoundClip> sc) {
		Iterator<SoundClip>it = sc.iterator();
		
		while(it.hasNext()) {
		SoundClip x=it.next();
		if(x.getBoolflag()==false)
		sounds.add(x);	
		
		if(x.getBoolflag()==true&&sounds.contains(x)) {
		sounds.remove(x);
		}
		}
	}

}

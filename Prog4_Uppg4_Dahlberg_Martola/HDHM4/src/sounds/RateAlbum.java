package sounds;

import java.util.Iterator;
import java.util.List;

public class RateAlbum extends SpecialAlbum {

	public RateAlbum(String name, Album root) {
		super(name, root);
	}

	public void checkSound(List<SoundClip> sc) {
		Iterator<SoundClip>it = sc.iterator();
		
		while(it.hasNext()) {
		SoundClip x=it.next();
		if(x.getRating()>3)
		sounds.add(x);	
		
		if(x.getRating()<4&&sounds.contains(x)) {
		sounds.remove(x);
		}
		}

}
}

package sounds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


 public class Album extends AbstractAlbum {
	
	private Set<AbstractAlbum> kids;					//Alla album har en hashSet av Album (sets kan inte ha duplikater)


 public Album(String name,Album parent) {//OM PARENT VETS
  super(name);
  this.parent=parent;
  this.kids=new HashSet<>();//SUBALBUMS
  this.sounds=new HashSet<>();//inne ocks� Sounds
  

}
 
public Album(String name) {//OM INGEN PARENT KLASS(ROOT)
  super(name);
  this.kids=new HashSet<>();
  this.sounds=new HashSet<>();
  
}
 
 public Album getParentAlbum() {
	 return (Album)parent;
 }

 public String getName() {//Ger namnet av albumet
	//return this.name;
	 return null;
}

 public void addSound(SoundClip sound) {	// �ndring fr�n urpsrungliga koden enligt feedbacken
	 
	 sounds.add(sound);
	 
	 if (parent != null) {
		 parent.addSound(sound);
	 }
}
 
 public void addSound(List<SoundClip> sc) {		// skapat f�r undo/redo funktionaliteten
	 sounds.addAll(sc);
	 if (parent != null) {
		 parent.addSound(sc);
	 }
 }

 public Set<AbstractAlbum> getAlbums() {//ENDAST F�R TEST
	 return this.kids;
}


 public ArrayList<String> getAlbumsNames() {//RETURNAR ALLA ALBUMS NAMN SOM �R SUB ALBUM TILL ALBUMET

	 ArrayList<String> x = new ArrayList<String>();

	for (AbstractAlbum kid : kids) {
	x.add(kid.getName());
	}
	return x;
 }

 public Set<SoundClip> getSounds() {//Returnerar alla soundclips 
	 return this.sounds;
}

 public void addAlbum(AbstractAlbum best) {		// �ndring fr�n urpsrungliga koden enligt feedbacken
  
	 kids.add(best);
	 
}
 
 
 public void deleteAlbum(Album album) {//Tar bort konektionen mellan deletade albumet och parenten
	
	 this.kids.remove(album);
	 //parent = null;
	    
	}

 
 public void deleteSounds(SoundClip sound) {//Rekursivt deletar alla instanser av SoundClipet
	
	sounds.remove(sound); 
	System.out.println("aaa");
	for(AbstractAlbum kid: kids) {
	  if(kid.getSounds().contains(sound)) {
		  System.out.println("test");
		  kid.deleteSounds(sound);  
	  }
  
	}
 }
 
 public boolean contains(Album a){		// Tillsatt metod enligt feedbacken

	   return kids.contains(a);

	}

 public Boolean contains(SoundClip s) {		// Tillsatt metod enligt feedbacken

	   return sounds.contains(s);

	}
 
 public String toString() {
	 return this.name;
 }

@Override
public void specialAlbums(SoundClip s) {
	// TODO Auto-generated method stub
	
}
}
package sounds;
import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

	private final File file;
	private int rating;
	private boolean flag;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
		this.rating = 0;
		this.flag = false;
	}

	public SoundClip(File file,int x) {
		assert file != null;
		this.file = file;
		this.rating = x;
		this.flag = false;
	}

	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	public String toString(){
		return file.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}

	public String getFlagged() {
		// TODO Auto-generated method stub
		if(this.flag == true) {
			return "F      ";
		} else {
			return "";
		}
	}

	public void setFlagged() {
		this.flag = !this.flag;
		// TODO Auto-generated method stub
		
	}

	public boolean getBoolflag() {
		System.out.print(this.flag);
		return !this.flag;
	}
	
	public int getRating() {

		return this.rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
}
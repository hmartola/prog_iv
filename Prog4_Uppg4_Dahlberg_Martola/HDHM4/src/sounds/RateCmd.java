package sounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RateCmd implements Command {
	
	private MusicOrganizerController contr;
	private List<SoundClip> oldsc=new ArrayList<>();//GAMLA VALUES
	private int rating;
	private List<SoundClip> sc;//DOM VALDA ALBUMEN MED NYA VALUES
	public RateCmd(List<SoundClip> sc, MusicOrganizerController c, int x, Set<SoundClip> set) {
		this.contr = c;
		this.rating = x;//1,2,3 och skall bli 0,0,0
		
		Iterator<SoundClip>it = sc.iterator();
		while(it.hasNext()) {//
		SoundClip h = it.next();
		SoundClip w = new SoundClip (h.getFile(),h.getRating());
		oldsc.add(w);
		System.out.println("oldsc rating är"+w.getRating());
		}
		Iterator<SoundClip>its = sc.iterator();
		while(its.hasNext()) {//new ottaa ne
		SoundClip w = its.next();
		w.setRating(rating);
		System.out.println("new rating är"+w.getRating());
		}
		this.sc=sc;
	
			
	}

	@Override
	public void undo() {//Gamla värdena som skall visas 1,2,3 nya 0,0,0
		
		Iterator<SoundClip>it = oldsc.iterator();
		Iterator<SoundClip>its = sc.iterator();

		while(it.hasNext()) {//new ottaa ne
		SoundClip w=it.next();//Gamla värdet altså 1
		SoundClip x=its.next();// nya värdet altså 0
		int a=w.getRating();//get 1
		w.setRating(x.getRating());//Värdet 1=>0
		x.setRating(a);//Värdet 0=>1
		//System.out.println("oldsc rating är"+x.getRating());
		}
		/*Iterator<SoundClip>its = sc.iterator();
		while(its.hasNext()) {//new ottaa ne
		SoundClip w = its.next();
		w.setRating(rating);
		System.out.println("new rating är"+w.getRating());
		}*/
		
			
			//System.out.println("Old rating is :"+oldsc.get(x).getRating()+ " Rating of new "+ w.getRating());
			
	/*	
		int x=0;
		Iterator<SoundClip>it = newsc.iterator();
		Iterator<SoundClip>its = newsc.iterator();
		while(it.hasNext()) {
		SoundClip w = it.next();
		SoundClip y = its.next();
		w.setRating(oldsc.get(x).getRating());
		y.setRating(newsc.get(x).getRating());
		System.out.println("Old rating is :"+oldsc.get(x).getRating()+ " Rating of new "+ w.getRating());
		x++;		
	}*/
	contr.updateRate(sc);		
	}

	@Override
	public void redo() {//Gamla värdena som skall visas 0,0,0 nya 1,2,3
		/*int x=0;
		Iterator<SoundClip>it = newsc.iterator();
		Iterator<SoundClip>its = newsc.iterator();
		while(it.hasNext()) {
		SoundClip w = it.next();
		SoundClip y = its.next();
		w.setRating(oldsc.get(x).getRating());
		y.setRating(newsc.get(x).getRating());
		System.out.println("Old rating is :"+oldsc.get(x).getRating()+ " Rating of new "+ w.getRating());
		x++;		*/
		Iterator<SoundClip>it = oldsc.iterator();//0,0,0
		Iterator<SoundClip>its = sc.iterator();//1,2,3
		while(it.hasNext()) {//new ottaa ne
		SoundClip w=it.next();//Gamla värdet altså 0
		SoundClip x=its.next();// nya värdet altså 1
		int a=w.getRating();//get 0
		w.setRating(x.getRating());//Värdet 0=>1
		x.setRating(a);//Värdet 1=>0
		System.out.println("oldsc rating är"+w.getRating());
		System.out.println("new rating är"+x.getRating());

		}
	
	contr.updateRate(sc);		

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	

	/*Iterator<SoundClip>it = newsc.iterator();
	int x=0;
		while(it.hasNext()) {
			SoundClip w = it.next();
		oldsc.get(x).setRating(w.getRating());
		w.setRating(this.rating);
		System.out.println("Old rating is :"+oldsc.get(x).getRating()+ " Rating of new "+ w.getRating());
		x++;*/
	
			//System.out.println("Old rating is :"+oldsc.get(x).getRating()+ " Rating of new "+ w.getRating());

	
	contr.updateRate(sc);
	}

}

package sounds;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.undo.UndoManager;

public class MusicOrganizerButtonPanel extends JPanel {

	private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	private Invoker invoker;
	
	public JButton newAlbumButton;
	public JButton deleteAlbumButton;
	public JButton addSoundClipsButton;
	public JButton removeSoundClipsButton;	
	private JButton playButton;
	public JButton undoButton;
	public JButton redoButton;
	private JButton ratingButton;
	private JButton flagButton;

	
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		invoker = new Invoker(contr);
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteAlbumButton = createDeleteAlbumButton();
		toolbar.add(deleteAlbumButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);
		
		flagButton = createFlagButton();
	    toolbar.add(flagButton);
		
		ratingButton = createRatingButton();
		toolbar.add(ratingButton);
		
		undoButton = createUndoButton();
        toolbar.add(undoButton);
        undoButton.setEnabled(false);
        
        redoButton = createRedoButton();
        toolbar.add(redoButton);
        redoButton.setEnabled(false);
		
		this.add(toolbar);

		
	}
	
	
	/**
	 * Note: You can replace the text strings in the instantiations of the JButtons
	 * below with ImageIcons if you prefer to have buttons with icons instead of
	 * buttons with text strings
	 * 
	 *  Example:
	 *  ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 *  JButton newAlbumButton = new JButton(newAlbumIcon);
	 *  
	 *  will put the imageIcon on the button, instead of the text "New Album", as 
	 *  done below
	 * 
	 */
	
	private JButton createNewAlbumButton() {
		//ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton("New Album");
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undoButton.setEnabled(true);
				String name = view.promptForAlbumName();
				//System.out.println(name);
				if(name == null) {			// cancel ger null, inget h�nder
					;
				} else if (controller.selectAlbum() == null) {		// undviker nullpointerexception
					view.showMessage("Click on the parent album before adding a subalbum");
				} else {
					invoker.addNewAlbum(name);
				}
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		//ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton("Remove Album");
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoker.deleteAlbum((Album )view.getSelectedAlbum());
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
	    //ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
	    JButton addSoundClipButton = new JButton("Add Sound Clips");
	    addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
	    addSoundClipButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	       
	    	List<SoundClip> x = view.getSelectedSoundClips();
	        if(x.isEmpty()!= true) {
	        	invoker.addSoundClips();
	        }

	      }
	    });
	    return addSoundClipButton;
	  }
	
	private JButton createRemoveSoundClipsButton() {
		//ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undoButton.setEnabled(true);
				List<SoundClip> x = view.getSelectedSoundClips();
		        if(x.isEmpty()!= true) {
		        	invoker.removeSoundClips();
		        	undoButton.setEnabled(true);
		        }
		      }
		    });
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		//ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton("Play");
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	private JButton createUndoButton() {
        
        ImageIcon undoIcon = new ImageIcon("icons/Actions-blue-arrow-undo-icon.png");
        JButton undoButton = new JButton(undoIcon);
        undoButton.setToolTipText("Undo last action");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	invoker.exeUndo();
            	redoButton.setEnabled(true);
            	if (invoker.isUndoEmpty()) {
            		undoButton.setEnabled(false);
            	} 
            	if (!invoker.isRedoEmpty()) {
            		redoButton.setEnabled(true);
            	}
            }
        });
        return undoButton;
        
    }
    
    private JButton createRedoButton() {
        
        ImageIcon redoIcon = new ImageIcon("icons/Actions-blue-arrow-redo-icon.png");
        JButton redoButton = new JButton(redoIcon);
        redoButton.setToolTipText("Redo last action");
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	invoker.exeRedo();
            	if (invoker.isRedoEmpty()) {
            		redoButton.setEnabled(false);
            	}
            	if (!invoker.isUndoEmpty()) {
            		undoButton.setEnabled(true);
            	}
            }
        });
        return redoButton;
    }
    
    private JButton createFlagButton() {
        JButton flagButton = new JButton("Flag");
        flagButton.setToolTipText("Flags Sound");
        flagButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
			List<SoundClip> x = view.getSelectedSoundClips();
			if(x.isEmpty()==false)
            invoker.flag(x);
			undoButton.setEnabled(true);
			
          }
        });
        return flagButton;
      }


      private JButton createRatingButton() {
        JButton ratingButton = new JButton("Rate");
        ratingButton.setToolTipText("Rate Button");
        ratingButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            System.out.println(view.getSelectedSoundClips());
            int x;
			if(view.getSelectedSoundClips().isEmpty()!=true) {
            //view.promptForRatingSound();
            x=view.promptForRatingSound();
            invoker.rate(view.getSelectedSoundClips(),x,view.getSelectedAlbum().getSounds());
            undoButton.setEnabled(true);
			}
            
            //controller.playSoundClips();
          }
        });
        return ratingButton;
      }
      
      public void changeEnable(boolean b) {
    	    newAlbumButton.setEnabled(b);
    	    deleteAlbumButton.setEnabled(b);
    	    addSoundClipsButton.setEnabled(b);
    	    removeSoundClipsButton.setEnabled(b);
    	    
    	  }

}

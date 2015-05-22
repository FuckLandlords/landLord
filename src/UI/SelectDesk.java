package UI;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.naming.InitialContext;
import javax.swing.JPanel;

public class SelectDesk extends JPanel{
	client p;
	String str_topbar = "res/common/topBar.png";
	String str_return = "res/common/return.png";
	String str_start = "res/selectDesk/start.png";
	String str_back = "res/selectDesk/back.png";
	String str_classic = "res/selectDesk/classic.png";
	String str_desk = "res/selectDesk/desk.png";
	
	Image image_topbar;
	Image image_return;
	Image image_start;
	Image image_back;
	Image image_classic;
	Image image_desk;
	
	MediaTracker mt;
	
	public SelectDesk(client p){
		this.p = p;
		loadMedia();
		init();
	}
	public void init() {
		
	}
	private void loadMedia() {
		// TODO Auto-generated method stub
		mt = new MediaTracker(this);
		image_back = getToolkit().getImage(str_back);
		image_classic = getToolkit().getImage(str_classic);
		image_desk = getToolkit().getImage(str_desk);
		image_return = getToolkit().getImage(str_return);
		image_start = getToolkit().getImage(str_start);
		image_topbar = getToolkit().getImage(str_topbar);
		
		mt.addImage(image_back, 0);
		mt.addImage(image_classic, 0);
		mt.addImage(image_desk, 0);
		mt.addImage(image_return, 0);
		mt.addImage(image_start, 0);
		mt.addImage(image_topbar, 0);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

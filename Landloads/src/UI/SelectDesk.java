package UI;

import java.awt.Graphics;
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
	
	MyJpanel topBar;
	JPanel bottomBar;
	MyJpanel classicJpanel;
	MyJpanel returnJpanel;
	
	MyJpanel firstTen;
	MyJpanel secondTen;
	MyJpanel[] firstTenDesk;
	MyJpanel[] secondTenDesk;
	MyJpanel start;
	public SelectDesk(client p){
		this.p = p;
		//setOpaque(false);
		loadMedia();
		init();
	}
	public void init() {
		topBar = new MyJpanel(image_topbar);
		returnJpanel = new MyJpanel(image_return);
		classicJpanel = new MyJpanel(image_classic);
		topBar.add(returnJpanel);
		topBar.add(classicJpanel);
		topBar.setOpaque(false);
		add(topBar);
		
		bottomBar = new JPanel();
		bottomBar.setOpaque(false);
		
		firstTen = new MyJpanel(image_back);
		firstTen.setOpaque(false);
		firstTenDesk = new MyJpanel[10];
		for(int i = 0; i < 10; i++){
			firstTenDesk[i] = new MyJpanel(image_desk);
			firstTen.add(firstTenDesk[i]);
		}
		bottomBar.add(firstTen);
		
		secondTen = new MyJpanel(image_back);
		secondTen.setOpaque(false);
		secondTenDesk = new MyJpanel[10];
		for(int i = 0; i < 10; i++){
			secondTenDesk[i] = new MyJpanel(image_desk);
			secondTen.add(firstTenDesk[i]);
		}
		bottomBar.add(secondTen);
		
		start = new MyJpanel(image_start);
		
		bottomBar.add(start);
	}
	
	public void paintComponent(Graphics g) {
		returnJpanel.setLocation(20, 10);
		returnJpanel.setSize(106, 82);
		
		classicJpanel.setSize(237, 57);
		classicJpanel.setLocation(361, 22);
		
		topBar.setLocation(0, 0);
		topBar.setSize(960,102);
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

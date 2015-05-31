package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.naming.InitialContext;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	
	int[] numOfPerDesk;
	
	JTextArea[] infoOfPerDesk;
	
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
		setOpaque(false);
		loadMedia();
		init();
	}
	public void init() {
		topBar = new MyJpanel(image_topbar);
		returnJpanel = new MyJpanel(image_return);
		returnJpanel.addMouseListener(new Click_Return());
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
			//firstTenDesk[i].addMouseListener(new Click_Desk(i+1));
			firstTen.add(firstTenDesk[i]);
		}
		bottomBar.add(firstTen);
		
		secondTen = new MyJpanel(image_back);
		secondTen.setOpaque(false);
		secondTenDesk = new MyJpanel[10];
		for(int i = 0; i < 10; i++){
			secondTenDesk[i] = new MyJpanel(image_desk);
			//secondTenDesk[i].addMouseListener(new Click_Desk(i+11));
			secondTen.add(secondTenDesk[i]);
		}
		bottomBar.add(secondTen);
		
		start = new MyJpanel(image_start);
		start.addMouseListener(new Click_Start());
		
		bottomBar.add(start);
		
		add(bottomBar);
		
		numOfPerDesk = new int[20];
		infoOfPerDesk = new JTextArea[20];
		
		for(int j = 0; j < 20; j++){
			numOfPerDesk[j] = 0;
			String s = numOfPerDesk[j] + "/3\n" + (j+1) +"桌";
			infoOfPerDesk[j] = new JTextArea(s);
			infoOfPerDesk[j].setForeground(new Color(255, 255, 255));//字体颜色
			infoOfPerDesk[j].setFont(new Font ("微软雅黑", Font.BOLD, 15));
			infoOfPerDesk[j].setEditable(false);
			infoOfPerDesk[j].setBackground(new Color(255, 255, 255, 0));
			infoOfPerDesk[j].setOpaque(false);
			infoOfPerDesk[j].addMouseListener(new Click_Desk(j+1));
			if(j < 10){
				firstTenDesk[j].add(infoOfPerDesk[j]);
			}
			else{
				secondTenDesk[j-10].add(infoOfPerDesk[j]);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		returnJpanel.setLocation(20, 10);
		returnJpanel.setSize(106, 82);
		
		classicJpanel.setSize(237, 57);
		classicJpanel.setLocation(361, 22);
		
		topBar.setLocation(0, 0);
		topBar.setSize(960,102);
		
		bottomBar.setLocation(0, 102);
		bottomBar.setSize(960, 538);
		
		firstTen.setLocation(0, 100);
		firstTen.setSize(960, 100);
		
		secondTen.setLocation(0, 250);
		secondTen.setSize(960, 100);
		for(int i = 0; i<10; i++){
			firstTenDesk[i].setLocation(67+ 25*i+60*i, 20);
			secondTenDesk[i].setLocation(67 + 25*i+60*i, 20);
			firstTenDesk[i].setSize(60, 60);
			secondTenDesk[i].setSize(60, 60);
		}
		
		start.setLocation(376, 400);
		start.setSize(208, 94);
		
		for(int j = 0; j < 20; j++){
			infoOfPerDesk[j].setLocation(15, 10);
			infoOfPerDesk[j].setSize(40, 40);
		}
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
	
	class Click_Return implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			p.showSelectPlay();
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	class Click_Desk implements MouseListener{
		
		int num;
		public Click_Desk(int num){
			this.num = num;
		}
		public void mouseClicked(MouseEvent e) {
			p.showGame();
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}
	
	class Click_Start implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}
}

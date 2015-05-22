package UI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyData extends JPanel{
	client p;
	String str_back = "res/common/back.jpg";
	String str_return = "res/common/return.png";
	String str_mydata = "res/data/mydata.png";
	String str_bottomback = "res/common/helpback.png";
	String str_long = "res/common/common.png";
	String str_wide = "res/common/widebar.png";
	String str_square = "res/common/square.png";
	String str_change = "res/data/change.png";
	String str_female = "res/data/female.png";
	String str_headframe = "res/data/headframe.png";
	String str_input = "res/data/input.png";
	String str_male = "res/data/male.png";
	String str_password = "res/data/password.png";
	String str_select = "res/data/select.png";
	String str_username = "res/data/username.png";
	String[] str_headpicture;
	
	int photoNum;
	
	MediaTracker mk;
	
	Image image_back;
	Image image_return;
	Image image_mydata;
	Image image_bottomback;
	Image image_long;
	Image image_widebar;
	Image image_square;
	Image image_change;
	Image image_female;
	Image image_headframe;
	Image image_input;
	Image image_male;
	Image image_password;
	Image image_select;
	Image image_username;
	Image[] image_headpicture;
	
	JPanel topBar;
	JPanel returnJPanel;
	JPanel bottomBar;
	
	MyJpanel[] headPicture;
	MyJpanel headframe;
	JPanel bg;
	MyJpanel bean;
	MyJpanel headMyJpanel;
	MyJpanel femaleJpanel;
	MyJpanel maleJpanel;
	MyJpanel selectJpanel;
	MyJpanel passwordJpanel;
	MyJpanel usernameJpanel;
	MyJpanel password;
	MyJpanel username;
	MyJpanel passwordinput;
	MyJpanel usernameinput;
	MyJpanel usernamechange;
	MyJpanel passwordchange;
	SelectHead selectHead;
	public MyData(client p){
		this.p = p;
		//setLayout(null);
		loadMedia();
		photoNum = 16;
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		selectHead = new SelectHead(this);
		add(selectHead);
		
		returnJPanel = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_return, 0, 0, d.width, d.height, null);
			}
		};
		returnJPanel.setOpaque(false);
		returnJPanel.addMouseListener(new ReturnListener());
		//returnJPanel.setLayout(null);
		
		topBar = new JPanel(){
			protected void paintComponent(Graphics g) {
				g.drawImage(image_mydata, 361, 22, 237, 57, null);
			}
		};
		topBar.setOpaque(false);
		//topBar.setLayout(null);
		
		topBar.add(returnJPanel);
		add(topBar);
		
		bottomBar = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d  = getSize();
				g.drawImage(image_bottomback, 0, 0, d.width, d.height, null);
			}
		};
		bean = new MyJpanel(image_widebar);
		//bean.setLayout(null);
		usernameJpanel = new MyJpanel(image_widebar);
		//usernameJpanel.setLayout(null);
		passwordJpanel = new MyJpanel(image_widebar);
		//passwordJpanel.setLayout(null);
		
		headMyJpanel = new MyJpanel(image_square);
		maleJpanel = new MyJpanel(image_male);
		maleJpanel.addMouseListener(new MaleListener());
		femaleJpanel = new MyJpanel(image_female);
		femaleJpanel.addMouseListener(new FemaleListener());
		selectJpanel = new MyJpanel(image_select);
		headPicture = new MyJpanel[25];
		for(int i = 1; i < 25; i++){
			headPicture[i] = new MyJpanel(image_headpicture[i]);
			headPicture[i].addMouseListener(new HeadListener());
			headMyJpanel.add(headPicture[i]);
		}
		headframe = new MyJpanel(image_headframe);
		headMyJpanel.add(headframe);
		headMyJpanel.add(maleJpanel);
		headMyJpanel.add(femaleJpanel);
		headMyJpanel.add(selectJpanel);
		
		password = new MyJpanel(image_password);
		passwordinput = new MyJpanel(image_input);
		passwordchange = new MyJpanel(image_change);
		
		passwordJpanel.add(password);
		passwordJpanel.add(passwordchange);
		passwordJpanel.add(passwordinput);
		
		username = new MyJpanel(image_username);
		usernamechange = new MyJpanel(image_change);
		usernameinput = new MyJpanel(image_input);
		
		usernameJpanel.add(username);
		usernameJpanel.add(usernamechange);
		usernameJpanel.add(usernameinput);
		
		selectHead.setVisible(false);
		
		bottomBar.add(headMyJpanel);
		bottomBar.add(passwordJpanel);
		bottomBar.add(usernameJpanel);
		bottomBar.add(bean);
		//bottomBar.add(bean);
		//bottomBar.setLayout(null);
		add(bottomBar);
		
		bg = new JPanel(){
			protected void paintComponent(Graphics g) {
				g.drawImage(image_back, 0, 0, 960, 640, null);
			};
		};
		add(bg);
	}
	private void loadMedia() {
		// TODO Auto-generated method stub
		mk = new MediaTracker(this);
		str_headpicture = new String[25];
		image_headpicture = new Image[25];
		for(int i = 1; i < 25; i++){
			str_headpicture[i] = "res/headportrait/head"+i+".png";
			image_headpicture[i] = getToolkit().getImage(str_headpicture[i]);
		}
		image_back = getToolkit().getImage(str_back);
		image_return = getToolkit().getImage(str_return);
		image_mydata = getToolkit().getImage(str_mydata);
		image_bottomback = getToolkit().getImage(str_bottomback);
		image_long = getToolkit().getImage(str_long);
		image_widebar = getToolkit().getImage(str_wide);
		image_square = getToolkit().getImage(str_square);
		image_change = getToolkit().getImage(str_change);
		image_female = getToolkit().getImage(str_female);
		image_headframe = getToolkit().getImage(str_headframe);
		image_input = getToolkit().getImage(str_input);
		image_male = getToolkit().getImage(str_male);
		image_password = getToolkit().getImage(str_password);
		image_select = getToolkit().getImage(str_select);
		image_username = getToolkit().getImage(str_username);
		//image_headpicture = getToolkit().getImage(str_headpicture);
		
		
		mk.addImage(image_back, 0);
		mk.addImage(image_return, 0);
		mk.addImage(image_mydata, 0);
		mk.addImage(image_bottomback, 0);
		mk.addImage(image_long, 0);
		mk.addImage(image_widebar, 0);
		mk.addImage(image_square, 0);
		mk.addImage(image_change, 0);
		mk.addImage(image_female, 0);
		mk.addImage(image_headframe, 0);
		mk.addImage(image_input, 0);
		mk.addImage(image_male, 0);
		mk.addImage(image_password, 0);
		mk.addImage(image_select, 0);
		mk.addImage(image_username, 0);
		for(int i = 1;i<25;i++){
			mk.addImage(image_headpicture[i], 0);
		}
		
		try {
			mk.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		//Dimension d = getSize();
		//g.drawImage(image_back, 0, 0, d.width, d.height, null);
		bg.setLocation(0, 0);
		bg.setSize(960, 640);
		topBar.setLocation(0, 0);
		topBar.setSize(960, 102);
		
		returnJPanel.setLocation(20, 10);
		returnJPanel.setSize(106, 82);
		
		bottomBar.setLocation(20, 130);
		bottomBar.setSize(920, 470);
		
		bean.setLocation(400, 70);
		bean.setSize(470, 80);
		
		passwordJpanel.setLocation(400, 290);
		passwordJpanel.setSize(470, 80);
		
		password.setLocation(20, 25);
		password.setSize(82, 34);
		
		passwordinput.setLocation(120, 15);
		passwordinput.setSize(260, 50);
		
		passwordchange.setLocation(400, 15);
		passwordchange.setSize(50, 50);
		
		usernameJpanel.setLocation(400, 180);
		usernameJpanel.setSize(470, 80);
		
		username.setLocation(20, 25);
		username.setSize(82, 34);
		
		usernamechange.setLocation(400, 15);
		usernamechange.setSize(50, 50);
		
		usernameinput.setLocation(120, 15);
		usernameinput.setSize(260, 50);
		
		headMyJpanel.setLocation(50, 50);
		headMyJpanel.setSize(300, 350);
		
		maleJpanel.setLocation(80, 25);
		maleJpanel.setSize(40, 38);
		
		selectJpanel.setLocation(80, 20);
		selectJpanel.setSize(48, 48);
		
		femaleJpanel.setLocation(200, 20);
		femaleJpanel.setSize(28, 48);
		
		headframe.setLocation(20,80);
		headframe.setSize(246, 246);
		for(int i = 1; i<25; i++){
			headPicture[i].setLocation(30, 90);
			headPicture[i].setSize(226, 226);
			if(i != photoNum)
				headPicture[i].setVisible(false);
			else {
				headPicture[i].setVisible(true);
			}
		}
		
		selectHead.setLocation(0, 0);
		selectHead.setSize(960, 640);
	}
	
	class ReturnListener implements MouseListener{

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
	
	class MaleListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			selectJpanel.setLocation(80, 20);
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
	class FemaleListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			selectJpanel.setLocation(190, 20);
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
	class HeadListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			selectHead.setVisible(true);
			topBar.setVisible(false);
			bottomBar.setVisible(false);
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
	public void showMain() {
		// TODO Auto-generated method stub
		selectHead.setVisible(false);
		bottomBar.setVisible(true);
		topBar.setVisible(true);
	}
}

class MyJpanel extends JPanel{
	Image i;
	public MyJpanel(Image i){
		this.i = i;
		setOpaque(false);
	}
	protected void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(i, 0, 0, d.width, d.height, null);
	}
}

class SelectHead extends JPanel{
	Jpanel[] arr_jpanel;
	MyData m;
	public SelectHead(MyData m){
		this.m = m;
		//setLayout(null);
		setOpaque(false);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		arr_jpanel = new Jpanel[25];
		for(int i = 1; i < 25;i++){
			arr_jpanel[i] = new Jpanel(m.image_headpicture[i]);
			arr_jpanel[i].setOpaque(false);
			arr_jpanel[i].addMouseListener(new ClickPicture());
			//arr_jpanel[i].setLayout(null);
			add(arr_jpanel[i]);
		}	

	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		int locationX = 180;
		int locationY = 150;
		int disX = 0;
		int disY = 0;
		for(int i = 1; i < 25;i++){
			arr_jpanel[i].setLocation(locationX+disX, locationY+disY);
			arr_jpanel[i].setSize(100, 100);
			disX = disX + 100;
			if(disX >= 600)
			{
				disY = (disY + 100)%400;
				disX = 0;
			}
		}	

	}
	class Jpanel extends JPanel{
		Image e;
		public Jpanel(Image e) {
			// TODO Auto-generated constructor stub
			this.e = e;
		}
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			//super.paintComponent(g);
			g.drawImage(e, 0, 0, 90, 90, null);
		}
	}
	class ClickPicture implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			Jpanel c1 = (Jpanel) e.getComponent();
			int x = c1.getX();
			int y = c1.getY();
			int disX = x - 180;
			int disY = y - 150;
			int num = (disY/100 * 6) + disX/100 + 1;
			m.photoNum = num;
			m.showMain();
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
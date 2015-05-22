package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import org.omg.CORBA.PUBLIC_MEMBER;

public class LogPanel extends JPanel{
	client p;
	private final int DELAY = 30;
	private Timer timer;
	private int x;
	private int y;
	private int moveX;
	private int moveY;
	private int count;
	private int width;
	private int height;
	private int Aheight;
	private Announce logMain;
	
	public LogPanel(client p){
		this.p = p;
		timer = new Timer(DELAY, new TimerListener());
		count = 0;
		width = 496;
		height = 265;
		x = 118;
		y = 190;
		moveX = 0;
		moveY = -8;
		Aheight = 640;
		logMain = new Announce(p);
		//logMain.setVisible(true);
		timer.start();
		this.add(logMain);
		setOpaque(false);
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(p.logo, x, y, width, height, null);
		//logMain.setVisible(true);
		logMain.setLocation(0, Aheight);
		logMain.setSize(732, 358);
		//logMain.setBackground(Color.black);
		//validate();
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(count > 15)
			{
				x += moveX;
				y += moveY;
				width -= 4;
				height -= 2;
				x += 2;
				y += 1;
				Aheight -= 16;

			if( count <= 40)
				repaint();
			else {
				timer.stop();
				Aheight += 16;
			}
			}
			count++;
		}
	}
	
}

class Announce extends JPanel{
	
	 String str_signin = "res/logUI/signin.png";
	 String str_signup = "res/logUI/signup.png";
	 
	 Image image_signin;
	 Image image_signup;
	 
	 client p;
	 String content = "1.乳鸽是逗比。\n2.黄队长！！！";
	 JTextArea jTextArea;
	 JPanel signin;
	 JPanel signup;
	 
	 public Announce(client p){
		 this.p = p;
		 image_signin = getToolkit().getImage(str_signin);
		 image_signup = getToolkit().getImage(str_signup);
		 
		 setLayout(null);
		 setOpaque(false);
		 jTextArea = new JTextArea(content, 2, 1);
		 init();
	 }
	 private void init() {
		// TODO Auto-generated method stub
		 jTextArea.setForeground(new Color(255, 255, 255));//字体颜色
		 jTextArea.setFont(new Font ("微软雅黑", Font.BOLD, 20));
		 jTextArea.setEditable(false);

		 jTextArea.setOpaque(false);
		 jTextArea.setLocation(40, 90);
		 jTextArea.setSize(200, 100);
		 add(jTextArea);
		 
		 signin = new JPanel(){
			protected void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(image_signin, 0, 0,d.width,d.height,  null);
			}
		 };
		 signin.setLocation(410, 80);
		 signin.setSize(250,100);
		 signin.setOpaque(false);
		 signin.addMouseListener(new signinMouse());
		 add(signin);
		 
		 signup = new JPanel(){
			 protected void paintComponent(Graphics g) {
				 Dimension d = getSize();
				 g.drawImage(image_signup, 0, 0, d.width, d.height, null);
			}
		 };
		 signup.setLocation(410, 200);
		 signup.setSize(250, 100);
		 signup.setOpaque(false);
		 signup.addMouseListener(new signupMouse());
		 add(signup);
	}
	protected void paintComponent(Graphics g){
		 g.drawImage(p.announce, 0, 0, 732, 358, null);
		 
	}
	
	class signinMouse implements MouseListener{
		public void mouseEntered(MouseEvent e) {
			signin.setSize(240,90);
			signin.setLocation(415, 85);
		}
		public void mouseExited(MouseEvent e) {
			signin.setLocation(410, 80);
			signin.setSize(250,100);
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			signin.setVisible(false);
			signup.setVisible(false);
			p.showSelectPlay();
		}

 	}
	
	class signupMouse implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			signup.setVisible(false);
			signin.setVisible(false);
			p.showSelectPlay();
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			signup.setLocation(415, 205);
			signup.setSize(240, 90);
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			 signup.setLocation(410, 200);
			 signup.setSize(250, 100);
		}
		
	}
}
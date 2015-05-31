package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

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
	 JPanel signinJPanel;
	 JPanel signupJPanel;
	 MyJpanel signin_signin;
	 MyJpanel signin_signup;
	 JTextField username;
	 JPasswordField password;
	 
	 JTextField signup_username;
	 JPasswordField signup_password;
	 JPasswordField signup_password2;
	 MyJpanel signup_signin;
	 MyJpanel signup_signup;
	 
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
		 
		 signinJPanel = new JPanel(){
			 protected void paintComponent(Graphics g){
			g.setColor(new Color(255, 255, 255));
			//字体颜色
			g.setFont(new Font ("微软雅黑", Font.BOLD, 20));
			 g.drawString("用户名", 30, 30);
			 g.drawString("密码", 30, 80);
			 }
		 };
		 signinJPanel.setLayout(null);
		 username = new JTextField();
		 username.setLocation(120, 10);
		 username.setSize(200, 30);
		 username.setOpaque(false);
		 username.setBackground(new Color(255,255,255,0));
		 username.setBorder(new LineBorder(Color.white));
		 password = new JPasswordField();
		 password.setLocation(120, 60);
		 password.setSize(200, 30);
		 password.setOpaque(false);
		 password.setBackground(new Color(255,255,255,0));
		 password.setBorder(new LineBorder(Color.WHITE));
		 signin_signin = new MyJpanel(image_signin);
		 signin_signin.addMouseListener(new Click_Signin_Signin());
		 signin_signup = new MyJpanel(image_signup);
		 signin_signup.addMouseListener(new Click_Signin_Signup());
		 signin_signin.setLocation(50, 120);
		 signin_signin.setSize(100, 50);
		 signin_signup.setLocation(200, 120);
		 signin_signup.setSize(100, 50);
		 signinJPanel.add(signin_signin);
		 signinJPanel.add(signin_signup);
		 
		 signinJPanel.add(username);
		 signinJPanel.add(password);
		 signinJPanel.setLocation(360, 100);
		 signinJPanel.setSize(400, 300);
		 add(signinJPanel);
		 signinJPanel.setVisible(false);
		 signinJPanel.setOpaque(false);
		 
		 signupJPanel = new JPanel(){
			 protected void paintComponent(Graphics g){
			g.setColor(new Color(255, 255, 255));
			//字体颜色
			g.setFont(new Font ("微软雅黑", Font.BOLD, 20));
			 g.drawString("用户名", 30, 30);
			 g.drawString("密码", 30, 80);
			 g.drawString("确认密码", 30, 130);
			 }
		 };
		 
		 signupJPanel.setLayout(null);
		 signup_username = new JTextField();
		 signup_username.setLocation(120, 10);
		 signup_username.setSize(200, 30);
		 signup_username.setOpaque(false);
		 signup_username.setBackground(new Color(255,255,255,0));
		 signup_username.setBorder(new LineBorder(Color.white));
		 signup_password = new JPasswordField();
		 signup_password.setLocation(120, 60);
		 signup_password.setSize(200, 30);
		 signup_password.setOpaque(false);
		 signup_password.setBackground(new Color(255,255,255,0));
		 signup_password.setBorder(new LineBorder(Color.WHITE));
		 
		 signup_password2 = new JPasswordField();
		 signup_password2.setLocation(120, 110);
		 signup_password2.setSize(200, 30);
		 signup_password2.setOpaque(false);
		 signup_password2.setBackground(new Color(255,255,255,0));
		 signup_password2.setBorder(new LineBorder(Color.WHITE));
		 
		 signup_signin = new MyJpanel(image_signin);
		 signup_signin.addMouseListener(new Click_Signup_Signin());
		 signup_signup = new MyJpanel(image_signup);
		 signup_signup.addMouseListener(new Click_Signup_Signup());
		 
		 signup_signin.setLocation(200, 170);
		 signup_signin.setSize(100, 50);
		 signup_signup.setLocation(50, 170);
		 signup_signup.setSize(100, 50);
		 signupJPanel.add(signup_signin);
		 signupJPanel.add(signup_signup);
		 
		 signupJPanel.add(signup_username);
		 signupJPanel.add(signup_password);
		 signupJPanel.add(signup_password2);
		 
		 signupJPanel.setLocation(360, 100);
		 signupJPanel.setSize(400, 300);
		 add(signupJPanel);
		 signupJPanel.setOpaque(false);
		 signupJPanel.setVisible(false);
	 }
	protected void paintComponent(Graphics g){
		 g.drawImage(p.announce, 0, 0, 732, 358, null);
		 
	}
	public void infoDialog(String mesg)  
    {  
        JOptionPane.showMessageDialog(null,  
            "<html><font color=\"green\"  style=\"font-weight:bold;\" >" + mesg  
                + "</font></html>", "消息",  
            JOptionPane.INFORMATION_MESSAGE);  
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
			signinJPanel.setVisible(true);
			
		}
		

 	}
	
	class signupMouse implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			signup.setVisible(false);
			signin.setVisible(false);
			signupJPanel.setVisible(true);
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			signup.setLocation(415, 205);
			signup.setSize(240, 90);
		}
		public void mouseExited(MouseEvent e) {
			 signup.setLocation(410, 200);
			 signup.setSize(250, 100);
		}
		
	}
	
	class Click_Signin_Signin implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//登陆
			String username2 = username.getText();
			String password2 = password.getText();
			if(username2.equals(""))
			{
				infoDialog("用户名不能为空");
				return;
			}
			if(password2.equals(""))
			{
				infoDialog("密码不能为空");
				return;
			}
			//p.os.write("login "+ username2 + " " + password2 + "\r\n");
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
	
	class Click_Signin_Signup implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//添加判断
			signinJPanel.setVisible(false);
			signupJPanel.setVisible(true);
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
	
	class Click_Signup_Signin implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//添加判断
			signupJPanel.setVisible(false);
			signinJPanel.setVisible(true);
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
	class Click_Signup_Signup implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//注册
			String u = signup_username.getText();
			String p1 = signup_password.getText();
			String p2 = signup_password2.getText();
			if(u.equals("")){
				infoDialog("用户名不能为空");
				return;
			}
			if(p1.equals("") || p2.equals("")){
				infoDialog("密码不能为空");
				return;
			}
			
			if(!p1.equals(p2)){
				infoDialog("两次密码不一致，不能注册");
				return;
			}
			infoDialog("注册成功~");
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

}
package UI;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectPlay extends JPanel {
	client p;
	int headNum;
	MediaTracker mt;
	String str_topbar = "res/common/topBar.png";
	String str_return = "res/common/return.png";
	String str_select = "res/selectPlay/selectPlay.png";
	String str_free = "res/selectPlay/free.png";
	String str_message = "res/selectPlay/message.png";
	String str_tradition = "res/selectPlay/tradition.png";
	String str_laizi = "res/selectPlay/laizi.png";
	String str_head = "res/headportrait/head";
	String str_headframe = "res/selectPlay/pictureframe.png";
	String str_headmessage = "res/selectPlay/headmessage.png";
	String str_honor = "res/selectPlay/honor.png";
	String str_help = "res/selectPlay/help.png";
	String str_beans = "res/common/beans.png";
	
	Image image_topbar;
	Image image_return;
	Image image_select;
	Image image_free;
	Image image_message;
	Image image_tradition;
	Image image_laizi;
	Image image_head;
	Image image_headframe;
	Image image_headmessage;
	Image image_honor;
	Image image_help;
	Image image_beans;
	
	JPanel topbar;
	JPanel returnJPanel;
	JPanel name;
	JPanel free;
	//JPanel message;
	
	JPanel middleBar;
	JPanel middleLeft;
	JPanel middleRight;
	
	JPanel bottomBar;
	JPanel headJPanel;
	JPanel userMessage;
	//JPanel honor;
	JPanel help;
	
	
	public SelectPlay(client p){
		this.p = p;
		headNum = 10;
		str_head  = str_head + headNum + ".png";
		loadMedia();
		setLayout(null);
		setOpaque(false);
		init();
		
	}
	 public void infoDialog(String mesg)  
	    {  
	        JOptionPane.showMessageDialog(null,  
	            "<html><font color=\"green\"  style=\"font-weight:bold;\" >" + mesg  
	                + "</font></html>", "消息",  
	            JOptionPane.INFORMATION_MESSAGE);  
	    }  

	private void init() {
		// TODO Auto-generated method stub
		returnJPanel = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_return, 0, 0, d.width, d.height, null);
			}
		};
		returnJPanel.addMouseListener(new Click_return());
		returnJPanel.setOpaque(false);
		
		name = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_select, 0, 0, d.width, d.height, null);
				}
		};
		name.setOpaque(false);
		
		free = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_free, 0, 0, d.width, d.height, null);
			}
		};
		free.setOpaque(false);
		free.addMouseListener(new Click_free());

		
		topbar = new JPanel(){
			protected void paintComponent(Graphics g) {
				g.drawImage(image_topbar, 0, 0, 960, 102, null);
			}
		};
		topbar.setOpaque(false);
		topbar.add(returnJPanel);
		topbar.add(name);
		//topbar.add(free);
		//topbar.add(message);
		add(topbar);
		
		middleBar = new JPanel();
		middleBar.setOpaque(false);
		
		middleLeft = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_tradition, 0, 0, d.width, d.height, null);
			}
		};
		middleLeft.setOpaque(false);
		middleLeft.addMouseListener(new Click_Classic());
		
		middleRight = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_laizi, 0, 0, d.width, d.height, null);
			}
		};
		middleRight.setOpaque(false);

		middleBar.add(middleLeft);
		middleBar.add(middleRight);
		add(middleBar);
		
		bottomBar = new JPanel();
		bottomBar.setOpaque(false);
		
		headJPanel = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_head, 10, 10, (int)d.getWidth()-20, (int)d.getHeight()-20, null);
				g.drawImage(image_headframe, 0, 0, (int)d.getWidth(), (int)d.getHeight(), null);
			}	
		};
		headJPanel.setOpaque(false);
		headJPanel.addMouseListener(new Click_mydata());
		
		userMessage = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_headmessage, 0, 0, d.width, d.height, null);
				g.drawImage(image_beans, 20, 60, 44, 42, null);
				}
		};
		userMessage.setOpaque(false);
		
		help = new JPanel(){
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(image_help, 0, 0, d.width, d.height, null);
				}
		};
		help.setOpaque(false);
		help.addMouseListener(new Click_help());
		
		bottomBar.add(headJPanel);
		bottomBar.add(userMessage);
		//bottomBar.add(honor);
		bottomBar.add(free);
		//bottomBar.add(message);
		bottomBar.add(help);
		add(bottomBar);
		
	}
	
	protected void paintComponent(Graphics g){
		returnJPanel.setLocation(20, 10);
		returnJPanel.setSize(106, 82);
		
		name.setSize(237, 57);
		name.setLocation(361, 22);
		
		free.setSize(118, 116);
		free.setLocation(650, 10);
		
		//message.setLocation(650, 10);
		//message.setSize(118, 116);
		
		topbar.setLocation(0, 0);
		topbar.setSize(960,102);
		
		middleBar.setLocation(0, 122);
		middleBar.setSize(960, 350);
		
		middleLeft.setLocation(210, 20);
		middleLeft.setSize(240, 300);
		
		middleRight.setLocation(540, 20);
		middleRight.setSize(240, 300);
		
		bottomBar.setLocation(0, 472);
		bottomBar.setSize(960, 168);
		
		headJPanel.setLocation(20, 15);
		headJPanel.setSize(115, 115);
		
		userMessage.setLocation(210, 15);
		userMessage.setSize(220, 118);
		
		//honor.setLocation(650, 15);
		//honor.setSize(118, 116);
		
		help.setLocation(830, 15);
		help.setSize(118, 116);
		//validate();
	}
	private void loadMedia() {
		// TODO Auto-generated method stub
		mt = new MediaTracker(this);
		image_topbar = getToolkit().getImage(str_topbar);
		image_return = getToolkit().getImage(str_return);
		image_select = getToolkit().getImage(str_select);
		image_free = getToolkit().getImage(str_free);
		image_message = getToolkit().getImage(str_message);
		image_tradition = getToolkit().getImage(str_tradition);
		image_laizi = getToolkit().getImage(str_laizi);
		image_head = getToolkit().getImage(str_head);
		image_headframe = getToolkit().getImage(str_headframe);
		image_headmessage = getToolkit().getImage(str_headmessage);
		image_help = getToolkit().getImage(str_help);
		image_honor = getToolkit().getImage(str_honor);
		image_beans = getToolkit().getImage(str_beans);
		
		
		mt.addImage(image_free, 0);
		mt.addImage(image_head, 0);
		mt.addImage(image_headframe, 0);
		mt.addImage(image_headmessage, 0);
		mt.addImage(image_help, 0);
		mt.addImage(image_honor, 0);
		mt.addImage(image_laizi, 0);
		mt.addImage(image_message, 0);
		mt.addImage(image_return, 0);
		mt.addImage(image_select, 0);
		mt.addImage(image_topbar, 0);
		mt.addImage(image_tradition, 0);
		mt.addImage(image_beans, 0);
		
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class Click_mydata implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			p.showMyData();
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
	
	class Click_help implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			p.showHelp();
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
	
	class Click_return implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//p.showHelp();
			p.info.logout_send(p.player, p.password);
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

	
	class Click_free implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			infoDialog("成功领取1000免费豆");
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
	
	class Click_Classic implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			p.showSelectDesk();
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

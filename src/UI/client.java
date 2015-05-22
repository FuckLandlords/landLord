package UI;

import com.bruceeckel.swing.Console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;

import UI.BackgroundPanel;

public class client extends JApplet{
	int flag;
	String str_background = "res/logUI/back.jpg";
	String str_logo = "res/logUI/logo.png";
	String str_announce = "res/logUI/announce.png";
	
	Image back;
	Image logo;
	Image announce;
	
	MediaTracker mt;
	BackgroundPanel bgp;
	LogPanel log;
	SelectPlay selectPlay;
	MyData myData;
	Help help;
	//SelectLevel selectLevel;
	
	int width = 960;
	int height = 640;
	int screenWidth;
	int screenHeight;
	int localX;
	int localY;

	public client() {
		// TODO Auto-generated constructor
		screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().height;
		localX = (screenWidth-width)/2;
		localY = (screenHeight-height)/2;
		flag = 0;
		//setLayout(new BorderLayout(100, 100));
		setLayout(null);
	}
	public void init() {
		loadMedia();
		
		bgp = new BackgroundPanel(this);
		bgp.setSize(960, 640);
		bgp.setLocation(0, 0);
		
		log = new LogPanel(this);
		log.setLocation(114, 0);
		log.setSize(732, 640);
		
		selectPlay = new SelectPlay(this);
		selectPlay.setSize(960,640);
		selectPlay.setLocation(0, 0);
		
		myData = new MyData(this);
		myData.setLocation(0, 0);
		myData.setSize(960, 640);
		
		help = new Help(this);
		help.setLocation(0, 0);
		help.setSize(960, 640);
		
		Container p = getContentPane();
		p.add(log);
		p.add(selectPlay);
		p.add(bgp);
		p.add(myData);
		p.add(help);
		selectPlay.setVisible(false);
		myData.setVisible(false);
		help.setVisible(false);
	}
	
	private void loadMedia() {
		// TODO Auto-generated method stub
		mt = new MediaTracker(this);
		back = getToolkit().getImage(str_background);
		logo = getToolkit().getImage(str_logo);
		announce = getToolkit().getImage(str_announce);
		
		mt.addImage(back, 0);
		mt.addImage(logo, 0);
		mt.addImage(announce, 0);
		
		try
		{
			mt.waitForAll();
		}catch(InterruptedException mye){System.out.println(mye);}

	}
	public void paint(Graphics g)
	{
		super.paint(g);
		//bgp.paintComponent(g);
		if(flag == 0)
		{
			//log.setVisible(true);
			//selectPlay.setVisible(false);
		}
		else if(flag == 1)
		{
			//log.setVisible(false);
			//selectPlay.setVisible(true);
		}
	}
	
	public void showSelectPlay() {
		log.setVisible(false);
		selectPlay.setVisible(true);
		myData.setVisible(false);
		bgp.setVisible(true);
		help.setVisible(false);
	}
	
	public void showMyData() {
		selectPlay.setVisible(false);
		myData.setVisible(true);
		bgp.setVisible(false);
		log.setVisible(false);
		help.setVisible(false);
	}
	
	public void showHelp() {
		selectPlay.setVisible(false);
		help.setVisible(true);
		bgp.setVisible(false);
	}
	public static void main(String[] args) {
		int width = 960;
		int height = 640;
		int screenWidth;
		int screenHeight;
		int localX;
		int localY;
		screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().height;
		localX = (screenWidth-width)/2;
		localY = (screenHeight-height)/2;

		 Console.run(new client(),width,height,localX,localY);

	}

}

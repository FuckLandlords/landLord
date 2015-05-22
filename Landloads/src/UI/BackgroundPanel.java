package UI;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	private int width = 960;
	private int height = 640;
	public int screenWidth;
	public int screenHeight;
	public int localX;
	public int localY;
	private client p;
	public BackgroundPanel (client p) {
		this.p = p;
		screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().height;
		localX = (screenWidth-width)/2;
		localY = (screenHeight-height)/2;
		//this.setLocation(localX, localY);
		//this.setSize(width,height);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	    g.drawImage(p.back, 0, 0, width, height, this);
		}
	
}

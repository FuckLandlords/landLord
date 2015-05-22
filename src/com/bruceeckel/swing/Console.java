
package com.bruceeckel.swing;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class Console
{//Create a title string from the class name;
	public static String title(Object o)
	{
		String t = o.getClass().toString();
		//Remove the word "class":
		if(t.indexOf("class") != -1)
			t = t.substring(6);
		return t;
	}
	
	public static void setupClosing(JFrame frame)
	{
		//The JDK .2 Solution as an
		//anonymors inner class:
		frame.addWindowListener(new WindowAdapter(){
									public void windowClosing(WindowEvent e)
									{
										System.exit(0);
									}
													});				
	}
	
	public static void run(JFrame frame,int width,int height)
	{
		setupClosing(frame);
		frame.setSize(width,height);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void run(JApplet applet,int width,int height,int localX, int localY)
	{
		JFrame frame = new JFrame(title(applet));
		setupClosing(frame);
		frame.getContentPane().add(applet);
		frame.setSize(width,height);
		frame.setLocation(localX, localY);
		frame.setTitle("斗地主");
		//frame.setBackground(Color.BLACK);
		applet.init();
		applet.start();
		//frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public static void run(JPanel panel,int width,int height, int localX, int localY)
	{
		JFrame frame = new JFrame(title(panel));
		setupClosing(frame);
		panel.setSize(width,height);
		panel.setLocation(localX, localY);
		panel.setBackground(Color.BLACK);
		panel.setVisible(true);
		frame.getContentPane().add(panel);
		frame.validate();
		frame.setSize(width,height);
		frame.setLocation(localX, localY);
		frame.setTitle("斗地主");
		//frame.setBackground(Color.black);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
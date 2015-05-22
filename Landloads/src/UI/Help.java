package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Help extends JPanel{
	client p;
	int flag;   //0表示在帮助主界面，1表示在帮助子界面
	String str_back = "res/common/back.jpg";
	String str_return = "res/common/return.png";
	String str_helpback = "res/common/helpback.png";
	String str_common = "res/common/common.png";
	String str_compare = "res/help/compare.png";
	String str_feedback = "res/help/feedback.png";
	String str_gameproblem = "res/help/gameproblem.png";
	String str_help = "res/help/help.png";
	String str_introduction = "res/help/introduction.png";
	String str_problem = "res/help/problem.png";
	String str_rule = "res/help/rule.png";
	
	Image image_back;
	Image image_return;
	Image image_helpback;
	Image image_common;
	Image image_compare;
	Image image_feedback;
	Image image_gameproblem;
	Image image_help;
	Image image_introduction;
	Image image_problem;
	Image image_rule;
	
	MediaTracker mk;
	
	JPanel topbar;
	MyJpanel returnJpanel;
	MyJpanel bg;
	MyJpanel main;
	
	MyJpanel long_compare;
	MyJpanel compare;
	MyJpanel long_feedback;
	MyJpanel feedback;
	MyJpanel long_gameproblem;
	MyJpanel gameproblem;
	MyJpanel long_introdcution;
	MyJpanel introduction;
	MyJpanel long_problem;
	MyJpanel problem;
	MyJpanel long_rule;
	MyJpanel rule;
	JPanel textJPanel;
	JScrollPane textJScrollPane;
	JTextArea textArea;
	
	public Help(client p){
		this.p = p;
		flag = 0;
		textArea = new JTextArea();
		textJScrollPane = new JScrollPane(textArea);
		setOpaque(false);
		loadMedia();
		init();
	}
	
	public void init() {
		bg = new MyJpanel(image_back);

		topbar = new JPanel(){
			protected void paintComponent(Graphics g) {
				g.drawImage(image_help, 361, 22, 237, 57, null);
			}
		};
		returnJpanel = new MyJpanel(image_return);
		returnJpanel.addMouseListener(new ClickHelpReturn());
		topbar.add(returnJpanel);
		add(topbar);
		
		main = new MyJpanel(image_helpback);
		long_compare = new MyJpanel(image_common);
		compare = new MyJpanel(image_compare);
		compare.addMouseListener(new ClickTitle(3));
		long_compare.add(compare);
		main.add(long_compare);
		
		long_feedback = new MyJpanel(image_common);
		feedback = new MyJpanel(image_feedback);
		feedback.addMouseListener(new ClickTitle(6));
		long_feedback.add(feedback);
		main.add(long_feedback);
		
		long_gameproblem = new MyJpanel(image_common);
		gameproblem = new MyJpanel(image_gameproblem);
		gameproblem.addMouseListener(new ClickTitle(5));
		long_gameproblem.add(gameproblem);
		main.add(long_gameproblem);
		
		long_introdcution = new MyJpanel(image_common);
		introduction = new MyJpanel(image_introduction);
		introduction.addMouseListener(new ClickTitle(1));
		long_introdcution.add(introduction);
		main.add(long_introdcution);
		
		long_problem = new MyJpanel(image_common);
		problem = new MyJpanel(image_problem);
		problem.addMouseListener(new ClickTitle(4));
		long_problem.add(problem);
		main.add(long_problem);
		
		long_rule = new MyJpanel(image_common);
		rule = new MyJpanel(image_rule);
		rule.addMouseListener(new ClickTitle(2));
		long_rule.add(rule);
		main.add(long_rule);
		
		textJPanel = new JPanel();
		textJPanel.add(textJScrollPane);
		main.add(textJPanel);
		textJPanel.setVisible(false);
		textJPanel.setOpaque(false);
		textArea.setOpaque(false);
		textJScrollPane.setOpaque(false);
		textJScrollPane.getViewport().setOpaque(false);
		textJScrollPane.setViewportBorder(null);
		textJScrollPane.setBorder(null);
		textJScrollPane.setBackground(new Color(255,255,255,0));
		textArea.setForeground(new Color(255, 255, 255));//字体颜色
		textArea.setFont(new Font ("微软雅黑", Font.BOLD, 20));
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 255, 255, 0));
		main.setOpaque(false);
		add(main);
		add(bg);
	}
	
	protected void paintComponent(Graphics g) {
		bg.setLocation(0, 0);
		bg.setSize(960, 640);
		topbar.setLocation(0, 0);
		topbar.setSize(960, 102);
		
		returnJpanel.setLocation(20, 10);
		returnJpanel.setSize(106, 82);
		
		main.setLocation(20, 130);
		main.setSize(920, 470);
		
		textJPanel.setLocation(35, 40);
		textJPanel.setSize(850, 390);
		long_introdcution.setLocation(35, 40);
		long_introdcution.setSize(850, 65);
		introduction.setLocation(332, 12);
		introduction.setSize(186, 40);
		
		long_rule.setLocation(35, 105);
		long_rule.setSize(850, 65);
		rule.setLocation(332, 12);
		rule.setSize(186, 40);
		
		long_compare.setLocation(35, 170);
		long_compare.setSize(850, 65);
		compare.setLocation(332, 12);
		compare.setSize(186, 40);
		
		long_problem.setLocation(35, 235);
		long_problem.setSize(850, 65);
		problem.setLocation(332, 12);
		problem.setSize(186, 40);
		
		long_gameproblem.setLocation(35, 300);
		long_gameproblem.setSize(850, 65);
		gameproblem.setLocation(332, 12);
		gameproblem.setSize(186, 40);
		
		long_feedback.setLocation(35, 365);
		long_feedback.setSize(850, 65);
		feedback.setLocation(332, 12);
		feedback.setSize(186, 40);
	}
	
	private void loadMedia() {
		// TODO Auto-generated method stub
		mk = new MediaTracker(this);
		
		image_back = getToolkit().getImage(str_back);
		image_common = getToolkit().getImage(str_common);
		image_compare = getToolkit().getImage(str_compare);
		image_feedback = getToolkit().getImage(str_feedback);
		image_gameproblem = getToolkit().getImage(str_gameproblem);
		image_help = getToolkit().getImage(str_help);
		image_helpback = getToolkit().getImage(str_helpback);
		image_introduction = getToolkit().getImage(str_introduction);
		image_problem = getToolkit().getImage(str_problem);
		image_return = getToolkit().getImage(str_return);
		image_rule = getToolkit().getImage(str_rule);
		
		mk.addImage(image_back, 0);
		mk.addImage(image_common, 0);
		mk.addImage(image_compare, 0);
		mk.addImage(image_feedback, 0);
		mk.addImage(image_gameproblem, 0);
		mk.addImage(image_help, 0);
		mk.addImage(image_helpback, 0);
		mk.addImage(image_introduction, 0);
		mk.addImage(image_problem, 0);
		mk.addImage(image_return, 0);
		mk.addImage(image_rule, 0);
		
		try {
			mk.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class ClickHelpReturn implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			if(flag == 0)
				p.showSelectPlay();
			else {
				flag = 0;
				long_compare.setVisible(true);
				long_feedback.setVisible(true);
				long_gameproblem.setVisible(true);
				long_introdcution.setVisible(true);
				long_problem.setVisible(true);
				long_rule.setVisible(true);
				textJPanel.setVisible(false);

			}
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
	class ClickTitle implements MouseListener{
		int num;
		String str_introduction = "  欢乐斗地主是一种三人为局的扑克争先型的牌类游戏，每局都有一个玩家称之为“地主”。地主\n"
								+ "一人将对抗另两位玩家组成的农民。地主的目标是将手中的牌以正常的方式最先出完手中所有的\n"
								+ "牌，而两位农民的目标是在地主出完牌之前，两人中任何一人比地主先出完手中的牌为胜。";
		String str_rule			= "  欢乐斗地主使用的一副牌为54张，包括两张王，大王和小王。\n"
								+ "  一个地主和两个农民分别先抓17张牌，最后剩余最底下的三张牌则属于地主，即地主有20张牌。\n"
								+ "这三张牌均为亮牌。\n"
								+ "  系统自动随机一人开始叫地主，可以选择不叫或是叫地主。\n"
								+ "  如果第一个玩家叫了地主，轮流被叫第二和第三的玩家都选择不叫，那么第一位玩家将成为地主\n"
								+ "  如果第二位或者第三位玩家都叫地主，而首先叫地主的玩家选择放弃，那么最后一个叫地主的玩\n"
								+ "家将是本局的地主。\n"
								+ "  如果三个玩家都选择放弃“叫地主”，则系统重新发牌。";
		String str_compare      = "火箭:   即双王（大王和小王），最大的牌。\n"
								+ "炸弹：  四张同数值牌（如4个3）。\n"
								+ "单牌：  单个牌（如黑桃9）。\n"
								+ "对牌：  数值相同的两张牌。\n"
								+ "三张牌： 数值相同的三张牌。\n"
								+ "三带一： 数值相同的三张牌+一张单牌。\n"
								+ "单顺：  五张或更多的连续单牌。不包括2和双王。\n"
								+ "连对：  三对或者更多的连续对牌。不包括2和双王。\n"
								+ "飞机：  2个或者更多的连续三张牌。\n"
								+ "飞机带翅膀： 三顺+同数量的单牌。\n"
								+ "四带二： 四张牌+两个单牌。";
		String str_problem      = "还没有。";
		String str_gameproblem  = "还没有。";
		String str_feedback     = "还没有。";
		public ClickTitle(int num){
			this.num = num;
		}
		public void mouseClicked(MouseEvent e) {
			flag = 1;
			long_compare.setVisible(false);
			long_feedback.setVisible(false);
			long_gameproblem.setVisible(false);
			long_introdcution.setVisible(false);
			long_problem.setVisible(false);
			long_rule.setVisible(false);
			textJPanel.setVisible(true);
			if(num == 1)
				textArea.setText(this.str_introduction);
			else if(num == 2)
				textArea.setText(this.str_rule);
			else if(num == 3)
				textArea.setText(this.str_compare);
			else if(num == 4)
				textArea.setText(this.str_problem);
			else if(num == 5)
				textArea.setText(this.str_gameproblem);
			else {
				textArea.setText(this.str_feedback);
			}
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

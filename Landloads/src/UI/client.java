package UI;

import com.bruceeckel.swing.Console;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JApplet;
import javax.swing.JPanel;

import UI.BackgroundPanel;

public class client extends JApplet{
	
	static int MALE = 0;
	static int FEMALE = 1;
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
	SelectDesk selectDesk;
	Game game;
	Container p;
	//SelectLevel selectLevel;
	
	int width = 960;
	int height = 640;
	int screenWidth;
	int screenHeight;
	int localX;
	int localY;
	
/*
 * 以下是乳鸽的
 */
	Socket socket;
	PrintWriter os;
	BufferedReader is;
//需要跟服务器一致
	String serverIP = "127.0.0.1";
	int serverPort = 4700;
	
	//游戏信息
	int tableIndex = 0;			//所在桌号
	int playerCounter = 0;		//所在桌玩家数量
	String player = "me";		//用户名称，具体信息在注册时补充
	String player1 = "游客1";		//左边玩家名称，获取
	String player2 = "游客2";		//右边玩家名称，获取
	
	int[]  headNum = {17,16,18};                 //用户玩家头像
                       							//右边玩家头像，获取
	
	boolean hasleft = true;    //是否有左边玩家
	boolean hasRight = true;   //是否有右边玩家
	
	int[] gender = {MALE,FEMALE,MALE};     //0表示男性，1表示女性
	
	int landlord = 0;           //0 表示用户， 1表示左玩家，2表示右玩家  

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
		
		selectDesk = new SelectDesk(this);
		selectDesk.setLocation(0, 0);
		selectDesk.setSize(960, 640);
		
		game = new Game(this);
		game.setLocation(0, 0);
		game.setSize(960, 640);
		
		p = getContentPane();
		p.add(log);
		p.add(selectDesk);
		p.add(selectPlay);
		p.add(game);
		p.add(bgp);
		p.add(myData);
		p.add(help);
	
		selectPlay.setVisible(false);
		myData.setVisible(false);
		help.setVisible(false);
		selectDesk.setVisible(false);
		game.setVisible(false);
//以下是乳鸽的
		try
		{
			socket = new Socket(serverIP, serverPort);
			os = new PrintWriter(socket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		}
		catch(Exception e)
		{
			System.out.println("Connection failed!");
		}
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
		selectDesk.setVisible(false);
		
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
		bgp.setVisible(false);
		help.setVisible(true);
	}
	
	public void showSelectDesk(){
		selectPlay.setVisible(false);
		game.setVisible(false);
		bgp.setVisible(true);
		selectDesk.setVisible(true);
	}
	public void showGame() {
		selectDesk.setVisible(false);
		bgp.setVisible(true);
		game.setVisible(true);
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


//----------------------客户端线程开始------------------------------------------
class ClientThread extends Thread	//客户端线程，用来接收服务机发送过来的消息
{
	client p;						//此引用指向客户端界面	

	ClientThread(client p)
	{
		this.p = p;
	}

	public void run()				//客户端线程运行
	{
		while(true)
		{
			String info = "";
			try {
				info = p.is.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			//线程不断接收服务器端的消息
			String[] temp;						//辅助数组，解析命令
			info = info.split("\r")[0];
			//登陆
			if (info.length() > 6 && info.substring(0, 4).equals("login")) 		
			{
				if (info.split(" ")[1].equals("success"))
				{
					//登陆成功，界面切换，待补充
					p.showSelectPlay();
				}
				if (info.split(" ")[1].equals("fail"))
				{
					//登陆失败，弹出消息，待补充
				}
			}
			//登出
			if (info.length() > 7 && info.substring(0, 4).equals("logout")) 		
			{
				if (info.split(" ")[1].equals("success"))
				{
					//登出成功，界面切换，待补充
				}
				if (info.split(" ")[1].equals("fail"))
				{
					//登出失败，弹出消息，待补充
				}
			}
			//开始游戏配对
			if (info.length() > 14 && info.substring(0, 12).equals("startMatching"))
			{
				if (info.split(" ")[1].equals("check"))
				{
					//游戏配对成功，界面切换，待补充
				}
				if (info.split(" ")[1].equals("fail"))
				{
					//游戏配对失败，弹出消息，待补充
				}
			}
			//获取当前所在桌的游戏状态
			if (info.length() > 12 && info.substring(0, 10).equals("tableStatus"))
			{
				temp = info.split(" ");
				p.tableIndex = Integer.parseInt(temp[1]);
				p.playerCounter = Integer.parseInt(temp[2]);
				for(int i = 0; i < p.playerCounter; i++)
				{
				}
			}
		}
	}
}

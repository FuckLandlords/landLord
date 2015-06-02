package UI;

import com.bruceeckel.swing.Console;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

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
	
	int[] deskNum;
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
	String password = "";
	String player1 = "";		//宸﹁竟鐜╁鍚嶇О锛岃幏鍙�
	String player2 = "";		//鍙宠竟鐜╁鍚嶇О锛岃幏鍙�
	int playerIndex = 0;
	int roomIndex;

	
	int[]  headNum = {17,16,18};                 //用户玩家头像
                       							//右边玩家头像，获取

	boolean hasleft = false;    //鏄惁鏈夊乏杈圭帺瀹�
	boolean hasRight = false;   //鏄惁鏈夊彸杈圭帺瀹�

	
	int[] gender = {MALE,FEMALE,MALE};     //0表示男性，1表示女性
	
	int landlord = 0;           //0 表示用户， 1表示左玩家，2表示右玩家  
	
	public ClientThread info;
	
	boolean isFirstEnterDesk = true;

	public client() {
		// TODO Auto-generated constructor
		screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().height;
		localX = (screenWidth-width)/2;
		localY = (screenHeight-height)/2;
		flag = 0;
		//setLayout(new BorderLayout(100, 100));
		setLayout(null);
		deskNum = new int[20];
		info = new ClientThread(this);
		info.start();
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
		//p.setComponentZOrder(bgp, 1);
		//p.setComponentZOrder(game, 0);
	
		selectPlay.setVisible(false);
		myData.setVisible(false);
		help.setVisible(false);
		selectDesk.setVisible(false);
		game.setVisible(false);
//以下是乳鸽的
//		try
//		{
//			socket = new Socket(serverIP, serverPort);
//			os = new PrintWriter(socket.getOutputStream());
//			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
//		}
//		catch(Exception e)
//		{
//			System.out.println("Connection failed!");
//		}
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
	public void paintComponent(Graphics g)
	{
		//bgp = new BackgroundPanel(this);
		bgp.setSize(960, 640);
		bgp.setLocation(0, 0);
		
		//log = new LogPanel(this);
		log.setLocation(114, 0);
		log.setSize(732, 640);
		
		//selectPlay = new SelectPlay(this);
		selectPlay.setSize(960,640);
		selectPlay.setLocation(0, 0);
		
		//myData = new MyData(this);
		myData.setLocation(0, 0);
		myData.setSize(960, 640);
		
		//help = new Help(this);
		help.setLocation(0, 0);
		help.setSize(960, 640);
		
		//selectDesk = new SelectDesk(this);
		selectDesk.setLocation(0, 0);
		selectDesk.setSize(960, 640);
		
		//game = new Game(this);
		game.setLocation(0, 0);
		game.setSize(960, 640);

	}
	
	public void showSelectPlay() {
		log.setVisible(false);
		selectPlay.setVisible(true);
		myData.setVisible(false);
		bgp.setVisible(true);
		help.setVisible(false);
		selectDesk.setVisible(false);
		game.setVisible(false);
		
	}
	
	public void showLogJpanel() {
		log.setVisible(true);
		selectPlay.setVisible(false);
		myData.setVisible(false);
		bgp.setVisible(true);
		help.setVisible(false);
		selectDesk.setVisible(false);
		game.setVisible(false);
		
	}

	
	public void showMyData() {
		selectPlay.setVisible(false);
		myData.setVisible(true);
		bgp.setVisible(false);
		log.setVisible(false);
		help.setVisible(false);
		game.setVisible(false);
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
		//selectDesk
		selectDesk.initDesk();
		selectDesk.setVisible(true);
	}
	public void showGame() {
		selectDesk.setVisible(false);
		bgp.setVisible(true);
		//remove(game);
		//game = new Game(this);
		//p.add(game);
		//p.setComponentZOrder(game, 0);
		game.initPlayer();
		game.setVisible(true);
	}
	 public void infoDialog(String mesg)  
	    {  
	        JOptionPane.showMessageDialog(null,  
	            "<html><font color=\"green\"  style=\"font-weight:bold;\" >" + mesg  
	                + "</font></html>", "消息",  
	            JOptionPane.INFORMATION_MESSAGE);  
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
class ClientThread extends Thread
{
//<<<<<<< Updated upstream
	Socket ClientSoc;

	BufferedReader sin;
	DataInputStream din;
	DataOutputStream dout;
	BufferedReader br;
	client god;

	ClientThread(client p)
	{
		try
		{
			String ip = "127.0.0.1";
			ClientSoc=new Socket(ip,2015);
			din=new DataInputStream(ClientSoc.getInputStream());
			dout=new DataOutputStream(ClientSoc.getOutputStream());
			br=new BufferedReader(new InputStreamReader(System.in));

			InputStreamReader isr = new InputStreamReader(ClientSoc.getInputStream());
			sin = new BufferedReader(isr);

			god = p;

			System.out.println(sin.readLine());
		}
		catch(Exception ex)
		{
			System.out.println("Haha");
		}
	}

	public void run()
    {
        try{
            while(true)
            {
                String choice;
                choice=sin.readLine();
                System.out.println(choice);
                if(choice.startsWith("login")){
                    login_rec(choice);
                } else if(choice.startsWith("logout")){
                    logout_rec(choice);
                } else if(choice.startsWith("startMatching")){
                    startMatching_rec(choice);
                } else if(choice.startsWith("tableStatus")){
                    tableStatus_rec(choice);
                } else if(choice.startsWith("openNewRoom")){
                    openNewRoom_rec(choice);
                } else if(choice.startsWith("tablesStatus")){
                    tablesStatus_rec(choice);
                } else if(choice.startsWith("joinRoom")){
                    joinRoom_rec(choice);
                } else if(choice.startsWith("quitRoom")){
                    quitRoom_rec(choice);
                } else if(choice.startsWith("readyStatus")){
                    readyStatus_rec(choice);
                } else if(choice.startsWith("ready")){
                    ready_rec(choice);
                } else if(choice.startsWith("notReady")){
                    notReady_rec(choice);
                } else{
                    
                }
            }
        }catch (Exception ex){
            System.out.println("LoLo");
            return;
        }
    }

	public String receiveMessage()
	{
		try {
			String message = sin.readLine();
			System.out.println(message);
			return message;
		}
		catch (Exception ex)
		{
			return "-1";
		}

	}

	public void login_send(String userName, String password)
	{
		try{
			//your turn
			//String userName = ;
			//String password = "";
			//my stuff
			String toSend;
			toSend = "login " + userName + ' ' + password + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("login_send");
			return;
		}
	}

	public void login_rec(String clientMessage)
	{
		clientMessage = clientMessage.substring(clientMessage.indexOf(' ')+1);
		if(clientMessage.startsWith("s"))
		{
			god.showSelectPlay();
			//return true;
		}
		return;
		//return false;
	}

	public void logout_send(String userName, String password)
	{
		try{
			//your turn
			//String userName = "";
			//String password = "";
			//my stuff
			String toSend;
			toSend = "logout " + userName + ' ' + password + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("logout_send");
			return;
		}
	}

	public void logout_rec(String clientMessage)
	{
		clientMessage = clientMessage.substring(clientMessage.indexOf(' ')+1);
		if(clientMessage.startsWith("s"))
		{
			//god.sh
			god.showLogJpanel();
		}
		return;
			//return true;
		//return false;
	}

	public void startMatching_send()
	{
		try{
			//your turn
			String userName = god.player;
			//my stuff
			String toSend;
			toSend = "startMatching " + userName + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("startMatching_send");
			return;
		}
	}

	public void startMatching_rec(String clientMessage)
	{
		int roomIndex = -1, playerCounter = 0;
		String[] playerList = new String[3];
		int[] readyList = new int[3];
		//my stuff
		int startElementIndex = clientMessage.indexOf(' ') + 1;
		int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		roomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		startElementIndex = endElementIndex + 1;
		endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		playerCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		for(int i=0;i<playerCounter;i++){
			startElementIndex = endElementIndex + 1;
			endElementIndex = clientMessage.indexOf(' ', startElementIndex);
			playerList[i] = clientMessage.substring(startElementIndex, endElementIndex);
			startElementIndex = endElementIndex + 1;
			endElementIndex = clientMessage.indexOf(' ', startElementIndex);
			if(endElementIndex == -1)
				endElementIndex = clientMessage.length();
			readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		}
		//your turn
		if(playerCounter == 1){
			god.hasleft = false;
			god.hasRight = false;
			god.playerIndex = 0;
		}
		else if(playerCounter == 2){
			if(playerList[0] == god.player){
				god.hasRight = true;
				god.player2 = playerList[1];
				god.playerIndex = 0;
			}
			else{
				god.hasleft = true;
				god.player1 = playerList[0];
				god.playerIndex=1;
			}
		}
		else{
			if(playerList[0] == god.player){
				god.hasRight = true;
				god.player2 = playerList[1];
				god.hasleft = true;
				god.player1 = playerList[2];
				god.playerIndex = 0;
			}
			else if (playerList[1] == god.player) {
				god.hasleft = true;
				god.player1 = playerList[0];
				god.hasRight = true;
				god.player2 = playerList[2];
				god.playerIndex = 1;
			}
			else{
				god.hasleft = true;
				god.player1 = playerList[1];
				god.hasRight = true;
				god.player2 = playerList[0];
				god.playerIndex = 2;
			}
		}
		god.showGame();

	}

	public void openNewRoom_send()
	{
		try{
			//your turn
			String userName = "";
			//my stuff
			String toSend;
			toSend = "openNewRoom " + userName + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("openNewRoom_send");
			return;
		}
	}

	public void openNewRoom_rec(String clientMessage)
	{
		int newRoomIndex = -1;
		//my stuff
		int startElementIndex = clientMessage.indexOf(' ') + 1;
		int endElementIndex = clientMessage.length();
		newRoomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		//your turn

	}

	public void tablesStatus_send()
	{
		try{
			//your turn
			String userName = god.player;
			//my stuff
			String toSend = "tablesStatus " + userName + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("tablesStatus_send");
			return;
		}
	}

	public void tablesStatus_rec(String clientMessage)
	{
		int roomCounter = 0;
		int[] roomIndex = null, playerCounter = {};
		String[] playerList;
		int[] readyList;
		//my stuff
		int startElementIndex = clientMessage.indexOf(' ') + 1;
		int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		if(endElementIndex == -1)
			endElementIndex = clientMessage.length();
		roomCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		if (roomCounter != 0) {
			roomIndex = new int[roomCounter];
			playerCounter = new int[roomCounter];
			playerList = new String[roomCounter * 3];
			readyList = new int[roomCounter * 3];
			for (int i = 0; i < roomCounter; i++) {
				startElementIndex = endElementIndex + 1;
				endElementIndex = clientMessage.indexOf(' ', startElementIndex);
				roomIndex[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
				startElementIndex = endElementIndex + 1;
				endElementIndex = clientMessage.indexOf(' ', startElementIndex);
				playerCounter[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
				for (int j = 0; j < playerCounter[i]; j++) {
					startElementIndex = endElementIndex + 1;
					endElementIndex = clientMessage.indexOf(' ', startElementIndex);
					playerList[i * 3 + j] = clientMessage.substring(startElementIndex, endElementIndex);
					startElementIndex = endElementIndex + 1;
					endElementIndex = clientMessage.indexOf(' ', startElementIndex);
					if (endElementIndex == -1)
						endElementIndex = clientMessage.length();
					readyList[i * 3 + j] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
				}
			}
		}
		// your turn
		for(int j = 0; j < 20; j++)
			god.deskNum[j] = 0;
		for(int j = 0; j < roomCounter; j++){
			god.deskNum[roomIndex[j]-1] = playerCounter[j];
		}
		god.showSelectDesk();

	}

	public void joinUser_send()
	{
		try {
			//your turn
			String targetUserName = "";
			String userName = "";
			//my stuff
			String toSend = "joinUser " + targetUserName + ' ' + userName + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("joinUser_send");
			return;
		}
	}

	public void joinUser_rec(String clientMessage)
	{
		boolean successOrNot;
		int roomIndex = -1, playerCounter = 0;
		String[] playerList = new String[3];
		int[] readyList = new int[3];
		//my stuff
		int startElementIndex = clientMessage.indexOf(' ') + 1;
		int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		if(endElementIndex == -1)
			endElementIndex = clientMessage.length();
		if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("c"))
			successOrNot = true;
		else
			successOrNot = false;
		if(successOrNot == true) {
			roomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
			startElementIndex = endElementIndex + 1;
			endElementIndex = clientMessage.indexOf(' ', startElementIndex);
			playerCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
			for (int i = 0; i < playerCounter; i++) {
				startElementIndex = endElementIndex + 1;
				endElementIndex = clientMessage.indexOf(' ', startElementIndex);
				playerList[i] = clientMessage.substring(startElementIndex, endElementIndex);
				startElementIndex = endElementIndex + 1;
				endElementIndex = clientMessage.indexOf(' ', startElementIndex);
				if (endElementIndex == -1)
					endElementIndex = clientMessage.length();
				readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
			}
		}
		//your turn

	}

	public void tableStatus_send(String userName)
	{
		try {
			//my stuff
			String toSend = "tableStatus " + userName + "\r\n";
			dout.write(toSend.getBytes("UTF-8"));
		} catch (Exception ex){
			System.out.println("tableStatus_send");
			return;
		}
	}

	public void tableStatus_rec(String clientMessage)
	{
		int roomIndex = -1, playerCounter = 0;
		String[] playerList = new String[3];
		int[] readyList = new int[3];
		//my stuff
		int startElementIndex = clientMessage.indexOf(' ') + 1;
		int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		roomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		startElementIndex = endElementIndex + 1;
		endElementIndex = clientMessage.indexOf(' ', startElementIndex);
		playerCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		for(int i=0;i<playerCounter;i++){
			startElementIndex = endElementIndex + 1;
			endElementIndex = clientMessage.indexOf(' ', startElementIndex);
			playerList[i] = clientMessage.substring(startElementIndex, endElementIndex);
			startElementIndex = endElementIndex + 1;
			endElementIndex = clientMessage.indexOf(' ', startElementIndex);
			if(endElementIndex == -1)
				endElementIndex = clientMessage.length();
			readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
		}
		//your turn
	}

	public void joinRoom_send(int roomIndex, String userName)
    {
        try {
            String toSend = "joinRoom "+ roomIndex + " " + userName + "\r\n";
            dout.write(toSend.getBytes());
        } catch (Exception e) {
            System.out.println("joinRoom_send");
            return;
        }
    }

    public void joinRoom_rec(String clientMessage)
    {
        boolean successOrNot;
        int roomIndex = -1, playerCounter = 0;
        String[] playerList = new String[3];
        int[] readyList = new int[3];
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("c"))
            successOrNot = true;
        else
            successOrNot = false;
        if(successOrNot) {
        	startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            roomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            playerCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            for (int i = 0; i < playerCounter; i++) {
                startElementIndex = endElementIndex + 1;
                endElementIndex = clientMessage.indexOf(' ', startElementIndex);
                playerList[i] = clientMessage.substring(startElementIndex, endElementIndex);
                startElementIndex = endElementIndex + 1;
                endElementIndex = clientMessage.indexOf(' ', startElementIndex);
                if (endElementIndex == -1)
                    endElementIndex = clientMessage.length();
                readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            }
        }
        //your turn
        if(successOrNot == false){
        	god.infoDialog("本桌已满，请选择其他桌子");
        }
        else{
        	god.roomIndex = roomIndex;
        	if(god.isFirstEnterDesk == true){
        		god.isFirstEnterDesk = false;
		    	if(playerCounter == 1){
					god.hasleft = false;
					god.hasRight = false;
					god.playerIndex = 0;
				}
				else if(playerCounter == 2){
					if(playerList[0] == god.player){
						god.hasRight = true;
						god.player2 = playerList[1];
						god.playerIndex = 0;
					}
					else{
						god.hasleft = true;
						god.player1 = playerList[0];
						god.playerIndex=1;
					}
				}
				else{
					if(playerList[0] == god.player){
						god.hasRight = true;
						god.player2 = playerList[1];
						god.hasleft = true;
						god.player1 = playerList[2];
						god.playerIndex = 0;
					}
					else if (playerList[1] == god.player) {
						god.hasleft = true;
						god.player1 = playerList[0];
						god.hasRight = true;
						god.player2 = playerList[2];
						god.playerIndex = 1;
					}
					else{
						god.hasleft = true;
						god.player1 = playerList[1];
						god.hasRight = true;
						god.player2 = playerList[0];
						god.playerIndex = 2;
					}
				}
				god.showGame();
        	}
        	else{
        		if(playerCounter == 2){
        			god.hasRight = true;
        			god.player2 = playerList[1];
        			god.game.rightJoin();
        		}
        		else{
        			if(god.hasleft == false){
        				god.hasleft = true;
        				god.player1 = playerList[2];
        				god.game.leftJoin();
        			}
        			else {
						god.hasRight = true;
						god.player2 = playerList[2];
						god.game.rightJoin();
					}
        		}
        	}

        }
        
    }

    public void quitRoom_send(String userName, int roomIndex)
    {
        try {
            String toSend = "quitRoom "+ userName + " " + roomIndex + "\r\n";
            dout.write(toSend.getBytes());
        } catch (Exception e) {
            System.out.println("quitRoom_send");
            return;
        }
    }

    public void quitRoom_rec(String clientMessage)
    {
        boolean successOrNot;
        String quitterName = null;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("c"))
            successOrNot = true;
        else
            successOrNot = false;
        if(successOrNot) {
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            if(endElementIndex == -1)
                endElementIndex = clientMessage.length();
            quitterName = clientMessage.substring(startElementIndex, endElementIndex);
        }
        //your turn
        if(successOrNot == false){
        	return;
        }
        if(quitterName == god.player){
        	god.showSelectDesk();
        }
        else if (quitterName == god.player1) {
			god.game.leftQuit();
		}
        else {
			god.game.rightQuit();
		}
    }
   

    public void ready_send()
    {
        try {
            String toSend = "ready " + "\r\n";
            dout.write(toSend.getBytes());
        } catch (Exception e) {
            System.out.println("ready_send");
            return;
        }
    }

    public void ready_rec(String clientMessage)
    {
        boolean successOrNot;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("c"))
            successOrNot = true;
        else
            successOrNot = false;
        //your turn
    }

    public void notReady_send()
    {
        try {
            String toSend = "notReady " + "\r\n";
            dout.write(toSend.getBytes());
        } catch (Exception e) {
            System.out.println("notReady_send");
            return;
        }
    }

    public void notReady_rec(String clientMessage)
    {
        boolean successOrNot;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("c"))
            successOrNot = true;
        else
            successOrNot = false;
        //your turn
    }

    public void readyStatus_send(String userName)
    {
        try {
            String toSend = "readyStatus "+ userName + "\r\n";
            dout.write(toSend.getBytes());
        } catch (Exception e) {
            System.out.println("readyStatus_send");
            return;
        }
    }

    public void readyStatus_rec(String clientMessage)
    {
        int playerCounter = -1;
        int[] readyList = new int[3];
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        playerCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));//must > 0 since you are there
        for(int i=0;i<playerCounter;i++){
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            if(endElementIndex == -1)
                endElementIndex = clientMessage.length();
            readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        }
        //your turn
    }

}

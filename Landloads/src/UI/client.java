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
import javax.swing.Timer;

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

	
	int[]  headNum = {5,5,5};                 //用户玩家头像
                       							//右边玩家头像，获取

	boolean hasleft = false;    //鏄惁鏈夊乏杈圭帺瀹�
	boolean hasRight = false;   //鏄惁鏈夊彸杈圭帺瀹�
	
	boolean leftReady = false;  //是否准备好
	boolean rightReady = false;
	boolean ready = false;

	
	int[] gender = {MALE,MALE,MALE};     //0表示男性，1表示女性
	
	int landlord = 0;           //0 表示用户， 1表示左玩家，2表示右玩家  
	
	public ClientThread info;
	
	boolean isFirstEnterDesk = true;

	boolean freePlay = true;
	
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
		selectPlay.initInfo();
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
	
	public int getIndex(int index) {
		if(playerIndex == 0){
			if(index == 0){
				return 0;
			}
			else if (index == 1) {
				return 2;
			}
			else {
				return 1;
			}
		}
		else if (playerIndex == 1) {
			if(index == 0)
				return 1;
			else if (index == 1) {
				return 0;
			}
			else {
				return 2;
			}
		}
		else{
			if(index == 0)
				return 2;
			else if (index == 1) {
				return 1;
			}
			else {
				return 0;
			}
		}
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
	int passCounter;

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
			passCounter = 0;

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
                } else if(choice.startsWith("gameStart")){
                    gameStart_rec(choice);
                } else if(choice.startsWith("landLordCall")){
                    landLordCall_rec(choice);
                } else if(choice.startsWith("landLordIs")){
                    landLordIs_rec(choice);
                } else if(choice.startsWith("landLord")){
                    landLord_rec(choice);
                } else if(choice.startsWith("XTurn")){
                    XTurn_rec(choice);
                } else if(choice.startsWith("XTimeOut")){
                    XTimeOut_rec(choice);
                } else if(choice.startsWith("XTime")){
                    XTime_rec(choice);
                } else if(choice.startsWith("cardOut")){
                    cardOut_rec(choice);
                } else if(choice.startsWith("gameOver")){
                	gameOver_rec(choice);
                }
                else {
					
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
						if(readyList[1] == 1)
							god.rightReady = true;
					}
					else{
						god.hasleft = true;
						god.player1 = playerList[0];
						god.playerIndex=1;
						if(readyList[0] == 1)
							god.leftReady = true;
					}
				}
				else{
					if(playerList[0] == god.player){
						god.hasRight = true;
						god.player2 = playerList[1];
						god.hasleft = true;
						if(readyList[1] == 1)
							god.rightReady = true;
						god.player1 = playerList[2];
						if(readyList[2] == 1)
							god.leftReady = true;
						god.playerIndex = 0;
						
					}
					else if (playerList[1] == god.player) {
						god.hasleft = true;
						god.player1 = playerList[0];
						if(readyList[0] == 1)
							god.leftReady = true;
						god.hasRight = true;
						god.player2 = playerList[2];
						if(readyList[2] == 1)
							god.rightReady = true;
						god.playerIndex = 1;
					}
					else{
						god.hasleft = true;
						god.player1 = playerList[1];
						if(readyList[1] == 1)
							god.leftReady = true;
						god.hasRight = true;
						god.player2 = playerList[0];
						if(readyList[0] == 1)
							god.rightReady = true;
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
        			god.playerIndex = 0;
        		}
        		else{
        			if(god.hasleft == false){
        				god.hasleft = true;
        				god.player1 = playerList[2];
        				god.game.leftJoin();
        				god.playerIndex = 0;
        			}
        			else {
						god.hasRight = true;
						god.player2 = playerList[2];
						god.game.rightJoin();
						god.playerIndex = 1;
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
        if(quitterName.equals(god.player)){
        	//god.showSelectDesk();
        	god.isFirstEnterDesk = true;
        	god.hasleft = false;
        	god.hasRight = false;
        	god.leftReady = false;
        	god.rightReady = false;
        	god.landlord = -1;
        	god.game.ready[0].setVisible(false);
        	god.game.ready[3].setVisible(true);
        	god.game.ready[1].setVisible(false);
        	god.game.ready[2].setVisible(false);
        	god.player1 = "";
        	god.player2 = "";
        	god.playerCounter = -1;
        	tablesStatus_send();
        	
        }
        else if (quitterName.equals(god.player1)) {
			god.game.leftQuit();
			god.hasleft = false;
			god.leftReady = false;
			god.player1 = "";
			god.game.ready[1].setVisible(false);
		}
        else {
			god.game.rightQuit();
			god.hasRight = false;
			god.rightReady = false;
			god.player2 = "";
			god.game.ready[2].setVisible(false);
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
        String userName = null;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        userName = clientMessage.substring(startElementIndex, endElementIndex);
        //your turn
        if(userName.equals(god.player)){
        	god.game.ready[3].setVisible(false);
        	god.game.ready[0].setVisible(true);
        }
        else if (userName.equals(god.player1)) {
			god.game.beReady(1);
		}
        else{
        	god.game.beReady(2);
        }
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

    //there is no need for gameStart_send
    public void gameStart_rec(String clientMessage)
    {
        String[] playerList  = new String [3]; // player name, according to the order of play
        int[] yourCardsColor = new int[17];
        int[] yourCardsValue = new int[17];
        int[] unfoldedCardsColor = new int[3];
        int[] unfoldedCardsValue = new int[3];
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        for(int i=0;i<3;i++){
            playerList[i] = clientMessage.substring(startElementIndex, endElementIndex);
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        }
        for(int i=0;i<17;i++){
            int card = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            if(card == 57){
                yourCardsColor[i] = 5;
                yourCardsValue[i] = 1;
            } else if(card == 58){
                yourCardsColor[i] = 5;
                yourCardsValue[i] = 2;
            }else{
                card--;
                yourCardsColor[i] = (card%4) + 1;
                yourCardsValue[i] = card/4;
            }
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        }
        for(int i=0;i<3;i++){
            int card = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            if(card == 57){
                unfoldedCardsColor[i] = 5;
                unfoldedCardsValue[i] = 1;
            } else if(card == 58){
                unfoldedCardsColor[i] = 5;
                unfoldedCardsValue[i] = 2;
            }else{
                card--;
                unfoldedCardsColor[i] = (card%4) + 1;
                unfoldedCardsValue[i] = card/4;
            }
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            if(endElementIndex == -1)
                endElementIndex = clientMessage.length();
        }
        System.out.println("here");
        //your turn
        for(int j = 0; j < 17; j++){
        	god.game.myCard[j].color = yourCardsColor[j];
        	god.game.myCard[j].num = yourCardsValue[j];
        }
        for(int j = 0; j < 3; j++){
        	god.game.three_cards[j].num = unfoldedCardsValue[j];
        	god.game.three_cards[j].color = unfoldedCardsColor[j];
        	god.game.myCard[j+17].num = unfoldedCardsValue[j];
        	god.game.myCard[j+17].color = unfoldedCardsColor[j];
        }
        god.game.startGame();
    }
    

    //i ignore gameInfo

    //there is no need for landLord_send
    public void landLord_rec(String clientMessage)
    {
        int userIndex = -1;// the user that is requested to answer landlord call
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        //your turn
        
        god.game.startTimer(god.getIndex(userIndex), 0);
        if(god.getIndex(userIndex) == 0)
        	god.game.poker_out.setVisible(false);
        else if (god.getIndex(userIndex) == 1) {
			god.game.left_poker_out.setVisible(false);
		}
        else{
        	god.game.right_poker_out.setVisible(false);
        }
        if(userIndex == god.playerIndex)
        	god.game.userCallOrOut(0);
    }

    public void landLordCall_send(int userIndex, String answer)//your answer to landlord call, should be yes or no
    {
        try{
            String toSend = "landLordCall " + userIndex + " " + answer + "\r\n";
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("landLordCall_send");
            return;
        }
    }

    public void landLordCall_rec(String clientMessage)
    {
        int userIndex = -1;
        boolean agreeOrNot = false; //answer
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        startElementIndex = endElementIndex + 1;
        endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        if(clientMessage.substring(startElementIndex, endElementIndex).startsWith("y"))
            agreeOrNot = true;
        else
            agreeOrNot = false;
        //your turn
        if(agreeOrNot)
        	god.game.notOutOrCallLandlord(2, god.getIndex(userIndex));
        else{
        	god.game.notOutOrCallLandlord(1, god.getIndex(userIndex));
        }
        if(god.game.timer != null && god.game.timer.isRunning())
        	god.game.timer.stop();
    }

    //there is no need for landLordIs_send
    public void landLordIs_rec(String clientMessage)
    {
        int landLordIndex = -1;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        landLordIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        passCounter = 2;
        //your turn
        god.landlord = god.getIndex(landLordIndex);
        god.game.setLandlord(god.getIndex(landLordIndex));
    }

    //there is no need for XTurn_send
    public void XTurn_rec(String clientMessage)
    {
        int userIndex = -1; //the user that should play
        boolean freeToPlay = false; // whether player of userIndex (not necessarily you) can use any card you want to play
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        if(passCounter == 2) {
            freeToPlay = true;
            passCounter = 0;
        }
        //your turn
        if(god.game.timer != null && god.game.timer.isRunning())
        	god.game.timer.stop();
        god.game.startTimer(god.getIndex(userIndex), 1);
        if(god.getIndex(userIndex) == 0){
        	god.game.outbuttonJpanel.setVisible(true);
        	god.game.notoutbuttonJpanel.setVisible(true);
        	god.game.poker_out.setVisible(false);
        	if(freeToPlay){
        		god.game.notoutgrayJpanel.setVisible(true);
        		god.game.notoutbuttonJpanel.setVisible(false);
        	}
        }
        god.freePlay = freeToPlay;
        
    }

    //there is no need for XTime_send
    public void XTime_rec(String clientMessage)
    {
        int userIndex = -1; //the user that is facing timeout
        int remainingTime = -1; //time remaining, in second (should be 10)
        // my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        startElementIndex = endElementIndex + 1;
        endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        remainingTime = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        //your turn
    }

    //there is no need for XTimeOut_send
    public void XTimeOut_rec(String clientMessage)
    {
        int userIndex = -1; // the user that triggered timeout
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
    }

    public void cardOut_send(int listSize,int[] valueList, int[] colorList)
    {
        int[] cardList = new int[listSize];
        String cardOutString = "cardOut";
        try{
            if(listSize != 0) {
                for (int i = 0; i < listSize; i++) {
                    if (colorList[i] != 5) {
                        cardList[i] = valueList[i] * 4 + colorList[i];
                    } else {
                        if (valueList[i] == 1)
                            cardList[i] = 57;
                        else
                            cardList[i] = 58;
                    }
                    cardOutString += " " + cardList[i];
                }
            } else {
                cardOutString += " -1";
            }
            cardOutString += "\r\n";
            dout.write(cardOutString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("cardOut(int[] valueList, int[] colorList)");
            return;
        }
    }

    public void cardOut_rec(String clientMessage)
    {
        int userIndex = -1;
        int cardCounter = 0; // listSize
        int[] cardListColor = null;
        int[] cardListValue = null;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        userIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        startElementIndex = endElementIndex + 1;
        endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        cardCounter = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        cardListColor = new int[cardCounter];
        cardListValue = new int[cardCounter];
        int i = 0;
        while(true){
            startElementIndex = endElementIndex + 1;
            endElementIndex = clientMessage.indexOf(' ', startElementIndex);
            if(endElementIndex == -1)
                break;
            int card = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
            if(card == 57){
                cardListColor[i] = 5;
                cardListValue[i] = 1;
            } else if(card == 58){
                cardListColor[i] = 5;
                cardListValue[i] = 2;
            }else{
                card--;
                cardListColor[i] = (card%4) + 1;
                cardListValue[i] = card/4;
            }
            i++;
        }
        int card = Integer.parseInt(clientMessage.substring(startElementIndex, clientMessage.length()));
        if(card == -1){
            cardCounter = 0;
            passCounter ++;
        } else if(card == 57){
            cardListColor[i] = 5;
            cardListValue[i] = 1;
            passCounter = passCounter>0?passCounter-1:0;
        } else if(card == 58){
            cardListColor[i] = 5;
            cardListValue[i] = 2;
            passCounter = passCounter>0?passCounter-1:0;
        } else{
            card--;
            cardListColor[i] = (card%4) + 1;
            cardListValue[i] = card/4;
            passCounter = passCounter>0?passCounter-1:0;
        }
        i++;
        //your turn
        int index = god.getIndex(userIndex);
        if(cardCounter == 0){
        	if(index == 1)
        		god.game.notOutOrCallLandlord(0, 1);
        	else if (index == 2) {
				god.game.notOutOrCallLandlord(0, 2);
			}
        	else {
				god.game.notOutOrCallLandlord(0, 0);
			}
        	if(god.game.timer!=null && god.game.timer.isRunning())
        		god.game.timer.stop();
        	return;
        }
        Card[] c = new Card[cardCounter];
        god.game.lastCards = new Card[cardCounter];
        for(int j = 0; j < cardCounter; j++){
        	c[j] = new Card(cardListColor[j], cardListValue[j]);
        	god.game.lastCards[j] = new Card(cardListColor[j], cardListValue[j]);
        }
        if(god.getIndex(userIndex) == 0)
        {
        	god.game.poker_remain_num -= c.length;
        	System.out.println(god.game.poker_remain_num);
        	god.game.getUserPoker(c);
			god.game.setPokerLocation();
        }
        else if (god.getIndex(userIndex) == 1) {
    		god.game.left_poker_remain_num -= c.length;
    		//if(god.game.left_poker_remain_num != 0)
    		god.game.getLeftPoker(c);
		}
        else{
        	god.game.right_poker_remain_num -= c.length;
        	//if(god.game.right_poker_remain_num == 0)
        		//god.game.right_poker_remain_num = 17;
        	god.game.getRightPoker(c);
        }
    }

    //i ignore cardInfo

    //there is no need for gameOver_rec
    public void gameOver_rec(String clientMessage)
    {
        int winnerIndex = -1;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
        if(endElementIndex == -1)
            endElementIndex = clientMessage.length();
        winnerIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        //your turn
        System.out.println("restart");
        god.game.restart();
        System.out.println("restart");
    }

}

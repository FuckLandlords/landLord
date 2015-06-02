package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game extends JPanel{
	
	//一些常量 
	static int SMOKE = 1;
	static int TIMER_NUM = 8;
	static int USER = 0;
	static int LEFT_USER = 1;
	static int RIGHT_USER = 2;
	
	client p;
	String str_frame = "res/mainView/frame.png";
	String str_bigDesk = "res/mainView/bigdesk.png";
	String str_chair = "res/mainView/chair.png";
	String str_menuback = "res/mainView/menuback.png";
	String str_return = "res/common/return.png";
	String str_head = "res/headportrait/head";
	String str_changedesk = "res/mainView/changedesk.png";
	String str_refersh = "res/mainView/refresh.png";
	String str_setting = "res/mainView/setting.png";
	String str_ready = "res/mainView/ready.png";
	String str_man = "res/mainView/man.jpg";
	String str_female = "res/mainView/female.jpg";
	String str_manlandlord = "res/mainView/manlandlord.jpg";
	String str_femalelandlord = "res/mainView/femalelandlord.jpg";
	String str_poker = "res/poker/poker";
	String str_poker_right = "res/poker/poker_right_";
	String str_poker_left = "res/poker/poker_left_";
	String str_start = "res/mainView/start.png";
	String str_transparent = "res/common/transparent.png";
	String str_clock = "res/mainView/clock.png";
	String str_poker_back = "res/poker/poker.png";
	String str_notout = "res/mainView/notout.png";
	String str_notcall = "res/mainView/notcall.png";
	String str_call = "res/mainView/call.png";
	String str_callbutton = "res/mainView/callbutton.png";
	String str_notcallbutton = "res/mainView/notcallbutton.png";
	String str_outbutton = "res/mainView/outbutton.png";
	String str_notoutbutton = "res/mainView/notoutbutton.png";
	//烟雾效果
	String str_smoke = "res/poker/smoke";
	Image[] image_smoke;
	
	Image image_frame;
	Image image_bigDesk;
	Image image_chair;
	Image image_menuback;
	Image image_return;
	Image[] image_head;
	Image image_changedesk;
	Image image_refersh;
	Image image_setting;
	Image image_ready;
	Image image_man;
	Image[][] image_poker;
	Image[] image_poker_right;
	Image[] image_poker_left;
	Image image_start;
	Image image_transparent;
	Image image_clock;
	Image image_female;
	Image image_manlandlord;
	Image image_femalelandlord;
	Image image_poker_back;
	Image image_notout;
	Image image_call;
	Image image_notcall;
	Image image_callbutton;
	Image image_notcallbutton;
	Image image_outbutton;
	Image image_notoutbutton;
	
	int menuflag;
	MediaTracker mt;
	
	MyJpanel returnJpanel;
	MyJpanel maindesk;
	MyJpanel menuback;
	HeadPicture[] headPicture;
	Graphics g;
	JPanel bottomOfMenu;
	MyJpanel changedesk;
	MyJpanel refersh;
	MyJpanel setting;
	MyJpanel[] ready;
	
	int menuLocationX;
	int menuLocationY;
	
	//计时
	Timer timer;
	int count;
	MyJpanel clock;
	int clock_x;
	int clock_y;
	JLabel currentJLabel;
	
	//动画Timer
	Timer[] animationTimer;
	
	//玩家牌的信息
	Card[] myCard;
	CardJPanel[] myCardJPanels;
	int poker_remain_num = 17;
	JPanel poker_out;
	int poker_out_x;
	int poker_out_y;
	int poker_out_width;
	int poker_out_height;
	JLabel[] textInfo;
	
	MyJpanel callbuttonJpanel;
	MyJpanel notcallbuttonJpanel;
	
	MyJpanel outbuttonJpanel;
	MyJpanel notoutbuttonJpanel;
	//左边玩家出的牌和剩下的牌数
	int left_poker_remain_num = 17;
	MyJpanel left_poker_remain;
	JPanel left_poker_out;
	int left_poker_out_x;
	int left_poker_out_y;
	int left_poker_out_width;
	int left_poker_out_height;
	Card[] left_poker_card;
	JLabel left_poker_remain_jlabel;
	
	//右边玩家出的牌和剩下的牌
	int right_poker_remain_num = 17;
	MyJpanel right_poker_remain;
	JPanel right_poker_out;
	int right_poker_out_x;
	int right_poker_out_y;
	int right_poker_out_width;
	int right_poker_out_height;
	Card[] right_poker_card;
	JLabel right_poker_remain_jlabel;
	
	//底牌
	JPanel three_cards_jpanel;
	Card[] three_cards;
	
	//
	Card[] lastCards = {};
	
	
	public Game(client p){
		this.p = p;
		loadMedia();
		myCardJPanels = new CardJPanel[20];
		three_cards = new Card[3];
		animationTimer = new Timer[TIMER_NUM];
		initCard();
		init();
		setOpaque(false);
		menuflag = 1;
		menuLocationX = 200;
		menuLocationY = -120;
		clock_x = 0;
		clock_y = 0;
	}
	
	/*
	 * 初始化我的牌
	 */
	public void initCard(){
		myCard = new Card[20];
//		for(int j = 0;j < 20; j++){
//			myCard[j] = new Card(j%4+1, j%13+1);
//		}
		myCard[0] = new Card(2, 9);
		myCard[1] = new Card(3, 10);
		myCard[2] = new Card(1, 7);
		myCard[3] = new Card(3, 7);
		myCard[4] = new Card(2, 7);
		myCard[5] = new Card(4, 7);
		myCard[6] = new Card(1, 8);
		myCard[7] = new Card(2, 8);
		myCard[8] = new Card(3, 8);
		myCard[9] = new Card(1, 6);
		myCard[10] = new Card(2, 6);
		myCard[11] = new Card(3, 6);
		myCard[12] = new Card(1, 2);
		myCard[13] = new Card(2, 2);
		myCard[14] = new Card(1, 1);
		myCard[15] = new Card(2, 1);
		myCard[16] = new Card(1, 9);
		myCard[17] = new Card(2, 3);
		myCard[18] = new Card(1, 4);
		myCard[19] = new Card(1, 5);


		sortCard(17);
		for(int j = 19 ; j >= 0; j-- ){
			myCardJPanels[j] = new CardJPanel(myCard[j], image_poker[myCard[j].color-1][myCard[j].num-1]);
			add(myCardJPanels[j]);
			myCardJPanels[j].setVisible(false);
		}
		for(int j = 0;j < 3; j++){
			three_cards[j] = new Card(myCard[j+17].color, myCard[j+17].num);
		}
	}

	/*
	 * 设置我的牌
	 */
	
	public void setMyCardJpanel(){
		for(int j = poker_remain_num-1; j >= 0; j-- ){
			remove(myCardJPanels[j]);
			myCardJPanels[j] = new CardJPanel(myCard[j], image_poker[myCard[j].color-1][myCard[j].num-1]);
			add(myCardJPanels[j]);
			myCardJPanels[j].setVisible(false);
		}
	}
	/*
	 * 左边玩家进桌
	 */
	public void initLeft(){
		remove(headPicture[1]);
		headPicture[1] = new HeadPicture(image_frame, image_head[p.headNum[1]-1]);
		add(headPicture[1]);
		textInfo[0].setText(p.player1);
		repaint();
	}
	
	/*
	 * 右边玩家进桌
	 */
	public void initRight(){
		remove(headPicture[2]);
		headPicture[2] = new HeadPicture(image_frame, image_head[p.headNum[2]-1]);
		add(headPicture[2]);
		textInfo[1].setText(p.player2);
		repaint();
	}
	
	/*
	 * 左边玩家进桌--有烟雾效果
	 */
	public void leftJoin(){
		getSmoke(1);
	}
	
	/*
	 * 右边玩家进桌，有烟雾效果
	 */
	public void rightJoin(){
		getSmoke(2);
	}
	
	/*
	 * 选完地主之后换头像
	 */
	public void changeHeadAll(){
		getSmoke(3);
		getSmoke(4);
		getSmoke(5);
	}
	
	public void leftQuit() {
		getSmoke(7);
	}
	
	public void rightQuit() {
		getSmoke(8);
	}
	/*
	 * num = 1 表示左边玩家进桌
	 * num = 2 表示右边玩家进桌
	 * num  = 3 表示用户换地主或者农民头像
	 * num  = 4 表示左玩家换地主或者农民头像
	 * num = 5 表示右玩家换地主或者农民头像
	 */
	
	public void getSmoke(int num) {
		if(num == 1){
			animationTimer[0] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[0].start();
		}
		else if (num == 2){
			animationTimer[1] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[1].start();
		}
		else if(num == 3){
			animationTimer[2] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[2].start();
		}
		else if(num == 4){
			animationTimer[3] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[3].start();
		}
		else if(num == 5){
			animationTimer[4] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[4].start();
		}
		else if(num == 6){
			animationTimer[5] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[5].start();
		}//左玩家退出
		else if(num == 7){
			animationTimer[6] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[6].start();
		}//右玩家退出
		else if(num == 8){
			animationTimer[7] = new Timer(100, new StartAnimationTimerListener(SMOKE, num));
			animationTimer[7].start();
		}



		
	}
	
	/*
	 * 左边玩家出牌
	 */
	public void getLeftPoker(Card[] left_out) {
		clock.setVisible(false);
		if(timer != null && timer.isRunning())
			timer.stop();
		
		if(left_out!=null){
			left_poker_remain_num -= left_out.length;
			left_poker_out_x = 278;
			left_poker_out_y = 300;
			if(left_out.length > 7){
				left_poker_out_width = 20*6+61;
				left_poker_out_height = 40*left_out.length/7+82;
			}
			else{
				left_poker_out_width = 20*(left_out.length-1)+61;
				left_poker_out_height = 82;
			}
			left_poker_out = new Poker_Out(left_out);
			add(left_poker_out);
			left_poker_out.setOpaque(false);
		}
		if(left_poker_remain_num == 0){
			setWinner(LEFT_USER);
		}
		remove(left_poker_remain);
		left_poker_remain = new MyJpanel(image_poker_left[left_poker_remain_num-1]);
		left_poker_remain.setOpaque(false);
		add(left_poker_remain);
		left_poker_remain_jlabel.setText(left_poker_remain_num+"");
		left_poker_remain.add(left_poker_remain_jlabel);
		repaint();
	}
	/*
	 * 右边玩家出牌
	 */
	public void getRightPoker(Card[] left_out) {
		clock.setVisible(false);
		if(timer!= null && timer.isRunning())
			timer.stop();
		

		if(left_out!=null){
			right_poker_remain_num -= left_out.length;
			right_poker_out_x = 500;
			right_poker_out_y = 300;
			if(left_out.length > 7){
				right_poker_out_width = 20*6+61;
				right_poker_out_height = 40*left_out.length/7+82;
			}
			else{
				right_poker_out_width = 20*(left_out.length-1)+61;
				right_poker_out_height = 82;
			}
			right_poker_out = new Poker_Out(left_out);
			add(right_poker_out);
			right_poker_out.setOpaque(false);
		}
		
		remove(right_poker_remain);
		right_poker_remain = new MyJpanel(image_poker_right[right_poker_remain_num-1]);
		right_poker_remain.setOpaque(false);
		add(right_poker_remain);
		right_poker_remain_jlabel.setText(right_poker_remain_num+"");
		right_poker_remain.add(right_poker_remain_jlabel);
		repaint();
	}
	
	/*
	 * 重新开始新的一局
	 */
	
	public void restart() {
		p.hasleft = true;
		p.hasRight = true;
		//init();
		//repaint();
		ready[3].setVisible(true);
		poker_out.setVisible(false);
		
		callbuttonJpanel.setVisible(false);
		notcallbuttonJpanel.setVisible(false);
		
		clock.setVisible(false);
		
		outbuttonJpanel.setVisible(false);
		notoutbuttonJpanel.setVisible(false);
		
		three_cards_jpanel.setVisible(false);
		
		for(int j = 0;j < 20; j++){
			myCardJPanels[j].setVisible(false);
		}
		left_poker_out.setVisible(false);
		
		left_poker_remain.setVisible(false);
		left_poker_out.setVisible(false);
		
		right_poker_out.setVisible(false);
		right_poker_remain.setVisible(false);
		
		getSmoke(6);
		
		
	}
	
	/*
	 * 结束一局之后恢复原来的头像
	 */
	
	public void changeToMyheadpicture() {
		for(int j = 0; j < 3; j++){
			remove(headPicture[j]);
			headPicture[j] = new HeadPicture(image_frame, image_head[p.headNum[j]-1]);
			add(headPicture[j]);
		}
		repaint();
	}
	/*
	 * 牌局结束后
	 * num = 0 表示玩家赢
	 * num = 1 表示左边玩家赢
	 * num = 2 表示右边玩家赢
	 */
	public void  setWinner(int num) {
		
	}
	/*
	 * 不出牌或者不叫地主或者叫地主
	 * type = 0 表示不出牌
	 * type = 1 表示不叫地主
	 * type = 2 表示叫地主
	 * num = 0 表示用户
	 * num = 1 表示左玩家
	 * num = 2 表示右玩家
	 */
	public void notOutOrCallLandlord(int type, int num){
		switch (num) {
		case 0:
			poker_out_x = 438;
			poker_out_y = 400;
			poker_out_width = 84;
			poker_out_height = 44;
			switch (type) {
			case 0:
				remove(poker_out);
				poker_out = new MyJpanel(image_notout);
				add(poker_out);
				break;
			case 1:
				remove(poker_out);
				poker_out = new MyJpanel(image_notcall);
				add(poker_out);
				break;
			case 2:
				remove(poker_out);
				poker_out = new MyJpanel(image_call);
				add(poker_out);
				break;
			default:
				break;
			}
			break;
		
		case 1:
			left_poker_out_x = 278;
			left_poker_out_y = 300;
			left_poker_out_width = 84;
			left_poker_out_height = 44;
			switch (type) {
			case 0:
				remove(left_poker_out);
				left_poker_out = new MyJpanel(image_notout);
				add(left_poker_out);
				break;
			case 1:
				remove(left_poker_out);
				left_poker_out = new MyJpanel(image_notcall);
				add(left_poker_out);
				break;
			case 2:
				remove(left_poker_out);
				left_poker_out = new MyJpanel(image_call);
				add(left_poker_out);
				break;
			default:
				break;
			}
			break;
		case 2:
			right_poker_out_x = 578;
			right_poker_out_y = 300;
			right_poker_out_width = 84;
			right_poker_out_height = 44;
			switch (type) {
			case 0:
				remove(right_poker_out);
				right_poker_out = new MyJpanel(image_notout);
				add(right_poker_out);
				break;
			case 1:
				remove(right_poker_out);
				right_poker_out = new MyJpanel(image_notcall);
				add(right_poker_out);
				break;
			case 2:
				remove(right_poker_out);
				right_poker_out = new MyJpanel(image_call);
				add(right_poker_out);
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
		repaint();
	}
	
	/*
	 * type = 0 表示出现叫地主按钮
	 * type = 1 表示出现出牌按钮
	 */
	public void userCallOrOut(int type){
		if(type == 0){
			callbuttonJpanel.setVisible(true);
			notcallbuttonJpanel.setVisible(true);
		}
		else{
			outbuttonJpanel.setVisible(true);
			notoutbuttonJpanel.setVisible(true);
		}
	}

	/*
	 * 翻转底牌
	 */
	public void reverseCards(){
//		for(int j = 17; j < 20; j++){
//			remove(myCardJPanels[j]);
//			myCardJPanels[j] = new CardJPanel(myCard[j], image_poker[myCard[j].color-1][myCard[j].num-1]);
//			myCardJPanels[j].localX = 730 + (j-17) * 70;
//			myCardJPanels[j].localY = 10;
//			//add(myCardJPanels[j]);
//		}
			three_cards_jpanel = new JPanel(){
			protected void paintComponent(Graphics g) {
				g.drawImage(image_poker[three_cards[0].color-1][three_cards[0].num-1], 0, 0, 61, 82, null);
				g.drawImage(image_poker[three_cards[1].color-1][three_cards[1].num-1], 70, 0, 61, 82, null);
				g.drawImage(image_poker[three_cards[2].color-1][three_cards[2].num-1], 140, 0, 61, 82, null);
				}
			};
			add(three_cards_jpanel);
			
	}
	
	/*
	 * 用户是地主
	 */
	public void setUserLandlord() {
		poker_remain_num += 3;
		sortCard(20);
		setMyCardJpanel();
		setPokerLocation();
	}
	
	/*
	 * 设置剩下的牌的位置
	 */
	public void setPokerLocation() {
		int k = 0;
		for(int j = 0; j < 20; j++){
			if(myCardJPanels[j].out)
			{
				myCardJPanels[j].setVisible(false);
				continue;
			}
				
			myCardJPanels[j].localX = 480 - (30*poker_remain_num+52)/2 + 30 *k;
			myCardJPanels[j].localY = 500;
			myCardJPanels[j].setVisible(true);
			k++;
		}
	}
	/*
	 * 确定地主
	 * num = 0 表示用户是地主
	 * num = 1 表示左边玩家是地主
	 * num = 2 表示右边玩家是地主
	 */
	public void setLandlord(int num) {
		reverseCards();
		changeHeadAll();
		startTimer(num, 1);
		if (num == 0) {
			setUserLandlord();
			outbuttonJpanel.setVisible(true);
			notoutbuttonJpanel.setVisible(true);
		}
		else if (num == 1) {
			left_poker_remain_num += 3;
			myCardJPanels[17].setVisible(false);
			myCardJPanels[18].setVisible(false);
			myCardJPanels[19].setVisible(false);
			getLeftPoker(null);
			//startTimer(1, 1);
		}
		else {
			right_poker_remain_num += 3;
			myCardJPanels[17].setVisible(false);
			myCardJPanels[18].setVisible(false);
			myCardJPanels[19].setVisible(false);
			getRightPoker(null);
			//startTimer(2, 1);
		}
		for(int j = 0; j < poker_remain_num; j++){
			myCardJPanels[j].addMouseListener(new Click_Poker(j));
		}
		repaint();
	}
	
	/*
	 * 对牌进行排序
	 */
	public void  sortCard(int num) {
		for(int j = 0; j < num -1 ; j++){
			for(int k = 0; k < num - j - 1; k++){
				if(myCard[k].getNumOfCard() < myCard[k+1].getNumOfCard()){
					int color;
					int num1;
					color = myCard[k].color;
					num1 = myCard[k].num;
					myCard[k].color = myCard[k+1].color;
					myCard[k].num = myCard[k+1].num;
					myCard[k+1].color = color;
					myCard[k+1].num = num1;
				}
			}
		}
	}
	/*
	 * 初始化基础变量
	 */
	
	public void init() {
		returnJpanel = new MyJpanel(image_return);
		returnJpanel.addMouseListener(new Click_Return());
		maindesk = new MyJpanel(image_bigDesk);
		menuback = new MyJpanel(image_menuback);
		headPicture = new HeadPicture[3];
		headPicture[0] = new HeadPicture(image_frame, image_head[p.headNum[0]-1]);
		headPicture[0].setOpaque(false);
		add(headPicture[0]);
		//setComponentZOrder(headPicture[0], 0);
		if(p.hasleft)
			headPicture[1] = new HeadPicture(image_frame, image_head[p.headNum[1]-1]);
		else
			headPicture[1] = new HeadPicture(image_transparent, image_transparent);
		headPicture[1].setOpaque(false);
		add(headPicture[1]);
		//setComponentZOrder(headPicture[1], 0);
		if(p.hasRight)
			headPicture[2] = new HeadPicture(image_frame, image_head[p.headNum[2]-1]);
		else
			headPicture[2] = new HeadPicture(image_transparent, image_transparent);
		headPicture[2].setOpaque(false);
		add(headPicture[2]);
		//setComponentZOrder(headPicture[2], 0);
		
		bottomOfMenu = new JPanel();
		bottomOfMenu.setOpaque(false);
		bottomOfMenu.addMouseListener(new Click_Menu());
		changedesk = new MyJpanel(image_changedesk);
		refersh = new MyJpanel(image_refersh);
		setting = new MyJpanel(image_setting);
		
		menuback.add(changedesk);
		menuback.add(refersh);
		menuback.add(setting);
		menuback.add(bottomOfMenu);
		
		ready = new MyJpanel[4];
		for(int j = 0;j < 3; j++){
			ready[j] = new MyJpanel(image_ready);
			add(ready[j]);
			//setComponentZOrder(ready[j], 0);
			ready[j].setVisible(false);
		}
		ready[3] = new MyJpanel(image_start);
		ready[3].addMouseListener(new Click_Start());
		add(ready[3]);
		//setComponentZOrder(ready[3], 0);
		
		textInfo = new JLabel[5];
		for(int j = 0; j< 5; j++){
			textInfo[j] = new JLabel();
			textInfo[j].setFont(new Font ("微软雅黑", Font.BOLD, 20));
			textInfo[j].setForeground(new Color(255,255,255));
			add(textInfo[j]);
			//setComponentZOrder(textInfo[j], 0);
		}
		if(p.hasleft){
			textInfo[0].setText(p.player1);;
		}
		else
			textInfo[0].setText("");
		if(p.hasRight)
			textInfo[1].setText(p.player2);
		else
			textInfo[1].setText("");
		textInfo[2].setText("豆:  10000");
		textInfo[3].setText("底分: "+ "75");
		textInfo[4].setText("倍数: " + "4");
		
		clock = new MyJpanel(image_clock);
		currentJLabel = new JLabel();
		currentJLabel.setFont(new Font ("微软雅黑", Font.PLAIN, 15));
		//currentJLabel.setForeground(new Color(255,255,255));
		currentJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clock.add(currentJLabel);
		add(clock);
		clock.setVisible(false);
		//setComponentZOrder(clock, 0);
		
		left_poker_remain = new MyJpanel(image_poker_left[left_poker_remain_num-1]);
		left_poker_remain.setOpaque(false);
		add(left_poker_remain);
		left_poker_remain.setVisible(false);
		
		left_poker_remain_jlabel = new JLabel();
		left_poker_remain_jlabel.setFont(new Font ("微软雅黑", Font.BOLD, 30));
		left_poker_remain_jlabel.setForeground(new Color(0,0,0));
		left_poker_remain.add(left_poker_remain_jlabel);
		
		right_poker_remain = new MyJpanel(image_poker_right[right_poker_remain_num-1]);
		right_poker_remain.setOpaque(false);
		add(right_poker_remain);
		right_poker_remain.setVisible(false);
		
		right_poker_remain_jlabel = new JLabel();
		right_poker_remain_jlabel.setFont(new Font ("微软雅黑", Font.BOLD, 30));
		right_poker_remain_jlabel.setForeground(new Color(0,0,0));
		right_poker_remain.add(right_poker_remain_jlabel);
		
		three_cards_jpanel = new JPanel(){
		protected void paintComponent(Graphics g) {
				g.drawImage(image_poker_back, 0, 0, 61, 82, null);
				g.drawImage(image_poker_back, 70, 0, 61, 82, null);
				g.drawImage(image_poker_back, 140, 0, 61, 82, null);
			}
		};
		add(three_cards_jpanel);
		three_cards_jpanel.setVisible(false);
		//three_cards_jpanel = new CardJPanel[3];
		for(int j = 17; j<20;j++){
			myCardJPanels[j] = new CardJPanel(myCard[j], image_poker_back);
			myCardJPanels[j].localX = 730 + (j-17) * 70;
			myCardJPanels[j].localY = 10;
			add(myCardJPanels[j]);
			myCardJPanels[j].setVisible(false);
		}
		
		poker_out = new JPanel();
		poker_out.setVisible(false);
		
		left_poker_out = new JPanel();
		left_poker_out.setVisible(false);
		
		right_poker_out = new JPanel();
		right_poker_out.setVisible(false);
		
		add(right_poker_out);
		add(left_poker_out);
		add(poker_out);
		
		callbuttonJpanel = new MyJpanel(image_callbutton);
		callbuttonJpanel.setVisible(false);
		callbuttonJpanel.setOpaque(false);
		callbuttonJpanel.addMouseListener(new Click_OutOrCall(1));
		add(callbuttonJpanel);
		
		notcallbuttonJpanel = new MyJpanel(image_notcallbutton);
		notcallbuttonJpanel.setVisible(false);
		notcallbuttonJpanel.setOpaque(false);
		notcallbuttonJpanel.addMouseListener(new Click_OutOrCall(0));
		add(notcallbuttonJpanel);
		
		outbuttonJpanel = new MyJpanel(image_outbutton);
		outbuttonJpanel.setVisible(false);
		outbuttonJpanel.setOpaque(false);
		outbuttonJpanel.addMouseListener(new Click_OutOrCall(3));
		add(outbuttonJpanel);
		
		notoutbuttonJpanel = new MyJpanel(image_notoutbutton);
		notoutbuttonJpanel.setVisible(false);
		notoutbuttonJpanel.setOpaque(false);
		notoutbuttonJpanel.addMouseListener(new Click_OutOrCall(2));
		add(notoutbuttonJpanel);
		

		add(returnJpanel);
		//add(maindesk);
		//setComponentZOrder(maindesk, 1);
		add(menuback);
				
	}
	/*
	 * 初始化左边玩家和右边玩家
	 */
	public void initPlayer() {
		if(p.hasleft){
			textInfo[0].setText(p.player1);;
		}
		else
			textInfo[0].setText("");
		if(p.hasRight)
			textInfo[1].setText(p.player2);
		else
			textInfo[1].setText("");
		if(p.hasleft)
		{
			remove(headPicture[1]);
			headPicture[1] = new HeadPicture(image_frame, image_head[p.headNum[1]-1]);
			add(headPicture[1]);
			headPicture[1].setOpaque(false);
		}
		if(p.hasRight){
			remove(headPicture[2]);
			headPicture[2] = new HeadPicture(image_frame, image_head[p.headNum[2]-1]);
			add(headPicture[2]);
			headPicture[2].setOpaque(false);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	
	protected void paintComponent(Graphics g) {
		returnJpanel.setLocation(20, 10);
		returnJpanel.setSize(106, 82);
		
		g.drawImage(image_bigDesk, 10, 250, 940, 390, null);
		//maindesk.setLocation(10, 250);
		//maindesk.setSize(940, 390);
		
		menuback.setLocation(menuLocationX, menuLocationY);
		menuback.setSize(560, 160);
		
		changedesk.setLocation(80, 20);
		changedesk.setSize(80, 80);
		
		refersh.setLocation(240, 20);
		refersh.setSize(80, 80);
		setting.setLocation(400, 20);
		setting.setSize(80, 80);
		
		bottomOfMenu.setLocation(200, 120);
		bottomOfMenu.setSize(160, 40);
		
		headPicture[0].setLocation(10, 520);
		headPicture[0].setSize(100, 100);
		
		headPicture[1].setLocation(10, 290);
		headPicture[1].setSize(100, 100);
		
		headPicture[2].setLocation(850, 290);
		headPicture[2].setSize(100, 100);
		
		ready[0].setLocation(450, 500);
		ready[0].setSize(86, 44);
		ready[3].setLocation(410, 450);
		ready[3].setSize(100, 50);
		ready[1].setLocation(200, 340);
		ready[1].setSize(86, 44);
		ready[2].setLocation(674, 340);
		ready[2].setSize(86, 44);
		
		for(int j = 0; j < 20; j++){
			myCardJPanels[j].setLocation(myCardJPanels[j].localX, myCardJPanels[j].localY);
			myCardJPanels[j].setSize(61, 82);
		}
		
		three_cards_jpanel.setLocation(730, 10);
		three_cards_jpanel.setSize(201, 82);
		
		textInfo[0].setLocation(15, 400);
		textInfo[0].setSize(95, 20);
		textInfo[1].setLocation(855, 400);
		textInfo[1].setSize(95, 20);
		textInfo[2].setLocation(120, 590);
		textInfo[3].setLocation(700, 590);
		textInfo[4].setLocation(800, 590);
		
		clock.setLocation(clock_x, clock_y);
		clock.setSize(40, 40);
		currentJLabel.setLocation(10, 13);
		currentJLabel.setSize(20, 15);
		
		left_poker_remain.setLocation(130+(20-left_poker_remain_num), 270+(20-left_poker_remain_num)*3);
		left_poker_remain.setSize(image_poker_left[left_poker_remain_num-1].getWidth(null), image_poker_left[left_poker_remain_num-1].getHeight(null));
		
		left_poker_remain_jlabel.setLocation(left_poker_remain.getWidth()/2-20, left_poker_remain.getHeight()/2-20);
		left_poker_remain_jlabel.setSize(50, 40);
		right_poker_remain.setLocation(680 + (20-right_poker_remain_num), 270+(20-right_poker_remain_num)*3);
		right_poker_remain.setSize(image_poker_right[right_poker_remain_num-1].getWidth(null), image_poker_right[right_poker_remain_num-1].getHeight(null));
	
		right_poker_remain_jlabel.setLocation(right_poker_remain.getWidth()/2-20, right_poker_remain.getHeight()/2-20);
		right_poker_remain_jlabel.setSize(50, 40);
				
		poker_out.setLocation(poker_out_x, poker_out_y);
		poker_out.setSize(poker_out_width, poker_out_height);
		
		left_poker_out.setLocation(left_poker_out_x, left_poker_out_y);
		left_poker_out.setSize(left_poker_out_width, left_poker_out_height);
		
		right_poker_out.setLocation(right_poker_out_x, right_poker_out_y);
		right_poker_out.setSize(right_poker_out_width, right_poker_out_height);
		
		callbuttonJpanel.setLocation(500, 400);
		callbuttonJpanel.setSize(80, 40);
		
		notcallbuttonJpanel.setLocation(400, 400);
		notcallbuttonJpanel.setSize(80, 40);
		
		outbuttonJpanel.setLocation(500, 400);
		outbuttonJpanel.setSize(80, 40);
		
		notoutbuttonJpanel.setLocation(400, 400);
		notoutbuttonJpanel.setSize(80, 40);
	}
	
	/*
	 * 当三个玩家都准备好之后，初始化界面
	 */
	
	public void startGame(){
		for(int j = 0; j < 3; j++){
			ready[j].setVisible(false);
			//three_cards_jpanel[j].setVisible(true);
		}
		for(int j = 17; j < 20; j++){
			//ready[j].setVisible(false);
			myCardJPanels[j].setVisible(true);
		}
		//初始化玩家的牌
		
		setMyCardJpanel();
		for(int j = 0 ;j < 17; j++){
			myCardJPanels[j].localX = 800;
			myCardJPanels[j].localY = 500;
			myCardJPanels[j].setVisible(true);
		}
		Timer timer = new Timer(60, new StartGameListener());
		timer.start();
		
		getLeftPoker(null);
		getRightPoker(null);
	}
	
	/*
	 * 获取选中的牌
	 */
	public Card[] getSelectedCards(){
		int k = 0;
		for(int j = 0; j < poker_remain_num;j ++){
			if(myCardJPanels[j].selected)
				k++;
		}
		Card[] c = new Card[k];
		k = 0;
		for(int j = 0; j < poker_remain_num;j ++){
			if(myCardJPanels[j].selected)
			{
				c[k] = new Card(myCardJPanels[j].card.color, myCardJPanels[j].card.num);
				k++;
			}
		}
		return c;
	}
	
	public void getUserPoker(Card[] c) {
		poker_out = new User_Poker_Out(c);
		add(poker_out);
		poker_out_x = 480-(c.length/2)*30;
		poker_out_y = 400;
		poker_out_height = 82;
		poker_out_width = 30*(c.length-1)+61;
		poker_out.setOpaque(false);
		repaint();
	}
	/*
	 * type = 0 表示选地主时计时
	 * type = 1 表示出牌时计时
	 * num = 0 表示轮到用户
	 * num = 1 表示轮到左边玩家
	 * num = 2 表示轮到右边玩家
	 */
	public void startTimer(int num, int type) {
		if(num == 1)
		{
			clock_x = 270;
			clock_y = 300;
		}
		else if(num == 2){
			clock_x = 590;
			clock_y = 300;
		}
		else {
			clock_x = 460;
			clock_y = 230;
		}
		if(type == 0)
			count = 10;
		else {
			count = 20;
		}
		clock.setVisible(true);
		currentJLabel.setText(count + "");
		count--;
		timer = new Timer(1000, new StartTimerListener());
		timer.start();

		
	}
	
	/*
	 * 玩家退出房间时头像消失
	 * num = 1 表示左玩家
	 * num = 2 表示右玩家
	 */
	public void changeHeadForQuit(int num){
		remove(headPicture[num]);
		headPicture[num] = new HeadPicture(image_transparent, image_transparent);
		add(headPicture[num]);
		headPicture[num].setOpaque(false);
		repaint();
	}
	/*
	 *  num = 1 表示左玩家准备好
	 *  num = 2 表示右玩家准备好
	 */
	public void beReady(int num) {
		ready[num].setVisible(true);
	}
	public void loadMedia() {
		mt = new MediaTracker(this);
		
		image_bigDesk = getToolkit().getImage(str_bigDesk);
		image_chair = getToolkit().getImage(str_chair);
		image_frame = getToolkit().getImage(str_frame);
		image_menuback =  getToolkit().getImage(str_menuback);
		image_return = getToolkit().getImage(str_return);
		image_changedesk = getToolkit().getImage(str_changedesk);
		image_refersh = getToolkit().getImage(str_refersh);
		image_setting = getToolkit().getImage(str_setting);
		image_ready = getToolkit().getImage(str_ready);
		image_man = getToolkit().getImage(str_man);
		image_start = getToolkit().getImage(str_start);
		image_clock = getToolkit().getImage(str_clock);
		image_female = getToolkit().getImage(str_female);
		image_manlandlord = getToolkit().getImage(str_manlandlord);
		image_femalelandlord = getToolkit().getImage(str_femalelandlord);
		image_poker_back = getToolkit().getImage(str_poker_back);
		image_call = getToolkit().getImage(str_call);
		image_notcall = getToolkit().getImage(str_notcall);
		image_notout = getToolkit().getImage(str_notout);
		image_callbutton = getToolkit().getImage(str_callbutton);
		image_notcallbutton = getToolkit().getImage(str_notcallbutton);
		image_outbutton = getToolkit().getImage(str_outbutton);
		image_notoutbutton = getToolkit().getImage(str_notoutbutton);
		
		image_head = new Image[24];
		for(int j = 0; j < 24; j++){
			image_head[j] = getToolkit().getImage(str_head+ (j+1) + ".png");
			mt.addImage(image_head[j], 0);
		}
		
		image_poker = new Image[5][13];
		for(int i = 0;i<4;i++){
			for(int j = 0; j< 13;j++){
				image_poker[i][j] = getToolkit().getImage(str_poker + (i+1) +"_" + (j+1) +".png");
				mt.addImage(image_poker[i][j], 0);
			}
		}
		image_poker[4][0] = getToolkit().getImage(str_poker + 5 + "_" + 1 + ".png");
		image_poker[4][1] = getToolkit().getImage(str_poker + 5 + "_" + 2 + ".png");
		
		mt.addImage(image_poker[4][0], 0);
		mt.addImage(image_poker[4][1], 0);
		
		image_smoke = new Image[6];
		for(int j = 0; j < 6; j++){
			image_smoke[j] = getToolkit().getImage(str_smoke + (j+1) +".png");
			mt.addImage(image_smoke[j], 0);
		}
		image_poker_left = new Image[20];
		image_poker_right = new Image[20];
		for(int j = 0; j < 20;j++){
			image_poker_left[j] = getToolkit().getImage(str_poker_left + (j+1) + ".png");
			image_poker_right[j] = getToolkit().getImage(str_poker_right + (j+1) + ".png");
			mt.addImage(image_poker_left[j], 0);
			mt.addImage(image_poker_right[j], 0);
		}
		
		mt.addImage(image_bigDesk, 0);
		mt.addImage(image_chair, 0);
		mt.addImage(image_frame, 0);
		mt.addImage(image_menuback, 0);
		mt.addImage(image_return, 0);
		mt.addImage(image_changedesk, 0);
		mt.addImage(image_refersh, 0);
		mt.addImage(image_setting, 0);
		mt.addImage(image_ready, 0);
		mt.addImage(image_man, 0);
		mt.addImage(image_start, 0);
		mt.addImage(image_clock, 0);
		mt.addImage(image_female, 0);
		mt.addImage(image_manlandlord, 0);
		mt.addImage(image_femalelandlord, 0);
		mt.addImage(image_poker_back, 0);
		mt.addImage(image_call, 0);
		mt.addImage(image_notcall, 0);
		mt.addImage(image_notout, 0);
		mt.addImage(image_callbutton, 0);
		mt.addImage(image_notcallbutton, 0);
		mt.addImage(image_outbutton, 0);
		mt.addImage(image_notoutbutton, 0);
		
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * num = 0 表示用户自己
	 * num = 1  表示左玩家
	 * num = 2 表示右玩家
	 */
	public void changeHead(int num){
		remove(headPicture[num]);
		if(num == p.landlord){
			if(p.gender[num] == p.MALE)
				headPicture[num] = new HeadPicture(image_frame, image_manlandlord);
			else {
				headPicture[num] = new HeadPicture(image_frame, image_femalelandlord);
			}
		}
		else{
			if(p.gender[num] == p.MALE)
				headPicture[num] = new HeadPicture(image_frame, image_man);
			else {
				headPicture[num] = new HeadPicture(image_frame, image_female);
			}
		}
		add(headPicture[num]);
		//setComponentZOrder(headPicture[num], 0);
		repaint();

	}
	
	class Click_Return implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			p.info.quitRoom_send(p.player, p.roomIndex);
						
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			startGame();
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}
	
	class Click_Start implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			//p.showSelectDesk();
			//changeHead();
			ready[3].setVisible(false);
			ready[0].setVisible(true);
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
	
	class Click_Poker implements MouseListener{
		int num;
		public Click_Poker(int num){
			this.num = num;
		}
		public void mouseClicked(MouseEvent e) {
			//p.showSelectDesk();
			//changeHead();
			//ready[3].setVisible(false);
			//ready[0].setVisible(true);
			if(myCardJPanels[num].selected){
				myCardJPanels[num].localY += 40;
				myCardJPanels[num].selected = false;
			}
			else {
				myCardJPanels[num].localY -= 40;
				myCardJPanels[num].selected = true;
			}
			repaint();
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


	class Click_Menu implements MouseListener{
		int count;
		Timer timer;
		public void mouseClicked(MouseEvent e) {
			menuflag = 1-menuflag;
			count = 0;
			timer = new Timer(20, new MenuListener());
			timer.start();
			
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		public class MenuListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				count++;
				if(count <= 38){
					if(menuflag == 0)
						menuLocationY += 3;
					else {
						menuLocationY -=3;
					}
					repaint();
				}
				else {
					timer.stop();
				}
			}
		}
		
	}
	
	public class StartGameListener implements ActionListener{
		int count = 0;
		//Timer timer;
		int localX = 400;
		int localY = 500;
		public StartGameListener(){
			//this.timer = timer;
		}
		public void actionPerformed(ActionEvent e){
			count++;
			if(count <= 17){
				for(int j = 0;j < 17; j++){
					if(j < count){
						myCardJPanels[j].localX -= 35;
					}
				}
				repaint();
			}
			else {
				count = 0;
				Timer timer = (Timer)e.getSource();
				timer.stop();
			}
		}
	}
	
	public class StartTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			currentJLabel.setText(count + "");
			count --;
			
			if(count == -1){
				//向服务器发送信息
				clock.setVisible(false);
				timer.stop();
			}
		}
	}

	public class StartAnimationTimerListener implements ActionListener{
		int count;
		int type;
		int num;
		public StartAnimationTimerListener(int type, int num){
			count = 0;
			this.type = type;
			this.num= num;
		}
		public void actionPerformed(ActionEvent e){
			if(type == SMOKE && num == 1){
				if(count >= 6){
					animationTimer[0].stop();
					count = 0;
					initLeft();
				}
				else {
					remove(headPicture[1]);
					headPicture[1] = new HeadPicture(image_smoke[count], null);
					add(headPicture[1]);
					repaint();
				}
				count++;
			}
			else if (type == SMOKE && num == 2) {
				if(count >= 6){
					animationTimer[1].stop();
					count = 0;
					initRight();
				}
				else {
					remove(headPicture[2]);
					headPicture[2] = new HeadPicture(image_smoke[count], null);
					add(headPicture[2]);
					repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 3) {
				if(count >= 6){
					animationTimer[2].stop();
					count = 0;
					changeHead(0);
				}
				else {
					remove(headPicture[0]);
					headPicture[0] = new HeadPicture(image_smoke[count], null);
					add(headPicture[0]);
					headPicture[0].setOpaque(false);
					//setComponentZOrder(headPicture[0], 0);
					repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 4) {
				if(count >= 6){
					animationTimer[3].stop();
					count = 0;
					changeHead(1);
				}
				else {
					remove(headPicture[1]);
					headPicture[1] = new HeadPicture(image_smoke[count], null);
					add(headPicture[1]);
					repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 5) {
				if(count >= 6){
					animationTimer[4].stop();
					count = 0;
					changeHead(2);
				}
				else {
					remove(headPicture[2]);
					headPicture[2] = new HeadPicture(image_smoke[count], null);
					add(headPicture[2]);
					repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 6) {
				if(count >= 6){
					animationTimer[5].stop();
					count = 0;
					changeToMyheadpicture();
				}
				else {
					for(int j = 0; j < 3; j++){
						remove(headPicture[j]);
						headPicture[j] = new HeadPicture(image_smoke[count], null);
						add(headPicture[j]);
					}
					repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 7) {
				if(count >= 6){
					animationTimer[6].stop();
					count = 0;
					changeHeadForQuit(1);
				}
				else {
						remove(headPicture[1]);
						headPicture[1] = new HeadPicture(image_smoke[count], null);
						add(headPicture[1]);
						repaint();
				}
				count++;

			}
			else if (type == SMOKE && num == 8) {
				if(count >= 6){
					animationTimer[7].stop();
					count = 0;
					changeHeadForQuit(2);
				}
				else {
						remove(headPicture[2]);
						headPicture[2] = new HeadPicture(image_smoke[count], null);
						add(headPicture[2]);
						repaint();
				}
				count++;

			}



		}
	}
	
	/*
	 * type = 0 表示点击不叫地主
	 * type = 1 表示点击叫地主
	 * type = 2 表示点击不出牌
	 * type = 3 表示点击出牌
	 */
	class Click_OutOrCall implements MouseListener{
		int type;
		
		public Click_OutOrCall(int type){
			this.type = type;
		}

		public void mouseClicked(MouseEvent e) {
			if(timer.isRunning())
				clock.setVisible(false);
			if(type == 0){
				//不叫地主
				callbuttonJpanel.setVisible(false);
				notcallbuttonJpanel.setVisible(false);
				notOutOrCallLandlord(1, 0);
				
			}
			else if(type == 1){
				callbuttonJpanel.setVisible(false);
				notcallbuttonJpanel.setVisible(false);
				notOutOrCallLandlord(2, 0);
			}
			else if(type == 2){
				outbuttonJpanel.setVisible(false);
				notoutbuttonJpanel.setVisible(false);
				notOutOrCallLandlord(0, 0);
			}
			else {
				//出牌
				Card[] c = getSelectedCards();
				//判断是否可出牌
				playCards card = new playCards(c, c.length, lastCards, lastCards.length);
				card.judgeType();
				if (!card.legalCard) {
					return;
				}
				card.judgeLastType();
				card.play();
				if (!card.playCard) {
					return;
				}
				outbuttonJpanel.setVisible(false);
				notoutbuttonJpanel.setVisible(false);
				for(int j = 0; j < 20; j++){
					if (myCardJPanels[j].selected) {
						myCardJPanels[j].out = true;
						myCardJPanels[j].selected = false;
					}
				}
				poker_remain_num -= c.length;
				getUserPoker(c);
				setPokerLocation();
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
	class Poker_Out extends JPanel{
		Card[] c;
		public Poker_Out(Card[] c){
			this.c = c;
		}
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			for(int j = 0; j < c.length; j++){
				if(j < 7){
					g.drawImage(image_poker[c[j].color-1][c[j].num-1], 0 + j * 20, 0, 61, 82, null);
				}
				else if (j < 14){
					g.drawImage(image_poker[c[j].color-1][c[j].num-1], 0 + (j -7)* 20, 40, 61, 82, null);
				}
				else{
					g.drawImage(image_poker[c[j].color-1][c[j].num-1], 0 + (j -14)* 20, 40, 61, 82, null);
				}
			}
		}
	}

	class User_Poker_Out extends JPanel{
		Card[] c;
		public User_Poker_Out(Card[] c){
			this.c = c;
		}
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			for(int j = 0; j < c.length; j++){
				g.drawImage(image_poker[c[j].color-1][c[j].num-1], 0 + j * 30, 0, 61, 82, null);
			}
		}
	}
	
	



}

class HeadPicture extends JPanel{
	Image out;
	Image in;
	public HeadPicture(Image out, Image in){
		this.out = out;
		this.in = in;
		setOpaque(false);
	}
	protected void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(in,10, 10, d.width-20, d.height-20,null);
		g.drawImage(out, 0, 0, d.width, d.height, null);
	}
}

class Card{
	int color;   //花色
	int num;     //数字
	public Card(int color, int num){
		this.color = color;
		this.num = num;
	}
	
	public int getNumOfCard(){
		if(color == 5 && num == 1){
			return 16;
		}
		else if (color == 5 && num == 2) {
			return 17;
		}
		else if (num == 1) {
			return 14;
		}
		else if (num == 2) {
			return 15;
		}
		return num;
		
	}
}

class CardJPanel extends JPanel{
	Card card;
	Image image;
	boolean selected;   //表示选中
	boolean out;        //表示牌已经出了
	int localX;
	int localY;
	public CardJPanel(Card card, Image image){
		this.card = card;
		this.image = image;
		selected = false;
		out = false;
		localX = 800;
		localY = 500;
	}
	
	protected void paintComponent(Graphics g){
		Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}
}

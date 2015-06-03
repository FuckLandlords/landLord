/**
 * Created by ���� on 5/22/2015.
 */
package server;

import java.net.*;
import java.io.*;
import java.util.*;

class User{
    String userName;
    Room theRoom;
    int ready;
}
class Room{
    int roomNumber;
    ArrayList<clientThread> users;
    int state;
    int allReadyLock;
    Game game;

    public void notifyTableStatus()
    {
        for(int i=0;i<users.size();i++){
            users.get(i).tableStatus();
        }
    }
    public void notifyReadyStatus(){
        for(int i=0; i<users.size();i++){
            users.get(i).readyStatus();
        }
    }
    public void notifyJoin(String outputString){
        for(int i=0;i<users.size();i++){
            users.get(i).joinOrQuitBroadcast(outputString);
        }
    }
}
class Game{
    ArrayList<clientThread> players;
    ArrayList<clientThread> toBeLoggedOut;
    User landLord;
    ArrayList<CardDeck> cardDecks;
    ArrayList<Card> unfoldedCards;
    ArrayList<Card> biggestCards;
    User bigPlayer;
    int bigIndex;
    int whosTurn;
    String turnName;
    int[] landLordSubmissionTable;
    Timer timer;
    Timer timer2;
    Room theRoom;

    Game()
    {
        landLord = null;
        bigPlayer = null;
        whosTurn = 0;
        turnName = players.get(0).me.userName;
        landLordSubmissionTable = new int[3];
        this.theRoom = theRoom;
        for(int i=0;i<3;i++)
            landLordSubmissionTable[i] = -1;
        gameStartPreparation();
    }

    Game(ArrayList<clientThread> players, Room theRoom)
    {
        landLord = null;
        bigPlayer = null;
        whosTurn = 0;
        turnName = players.get(0).me.userName;
        landLordSubmissionTable = new int[3];
        this.theRoom = theRoom;
        for(int i=0;i<3;i++)
            landLordSubmissionTable[i] = -1;
        this.players = players; // this should be randomized
        gameStartPreparation();
    }

    public void gameStartPreparation()
    {
        int cardDeckToBeShuffle[] = new int[54];
        for(int i=0;i<54;i++){
            //cardIndex = value * 4 + color, so cardIndex should start from 5
            //clowns are 57 and 58
            cardDeckToBeShuffle[i] = i+5;
        }
        //shuffle cardDeck
        Random random = new Random();
        for(int i=0;i<54;i++){
            int ha = random.nextInt(54-i) + i;
            int a = cardDeckToBeShuffle[i]; //actually cardDeckToBeShuffle[i] is just i+1
            int b = cardDeckToBeShuffle[ha];
            //swap a and b
            if(a != b){
                a ^=b;
                b ^=a;
                a ^=b;
            }
            cardDeckToBeShuffle[i] = a;
            cardDeckToBeShuffle[ha] = b;
        }
        //dispatch cards to player
        int ha = 0;
        for(int j = 0;j<3;j++){
            CardDeck aPlayersCards = cardDecks.get(j);
            for(int i=0;i<17;i++){
                Card dispatchedCard = new Card();
                dispatchedCard.value = cardDeckToBeShuffle[ha];
                aPlayersCards.cards.add(dispatchedCard);
                ha++;
            }
        }
        //remaining cards are unfolded cards
        for(int i = 0; i<3;i++){
            Card unfoldedCard = new Card();
            unfoldedCard.value = cardDeckToBeShuffle[ha];
            unfoldedCards.add(unfoldedCard);
            ha++;
        }
    }

    public void gameStartNotifier()
    {
        for(int i = players.size()-1;i>=0;i++){
            players.get(i).gameStart();
        }
    }

    public void landLordInvitation()
    {
        String broadcastString = "landLord " + whosTurn%3 + "\r\n";
        for(int i=0;i<3;i++){
            players.get(i).landLord(broadcastString);
        }
    }

    public void landLordRegistration(int agreeOrNot)
    {
        landLordSubmissionTable[whosTurn%3] = agreeOrNot;
        String broadcastString = "landLordCall " + whosTurn;
        if(agreeOrNot == 1)
            broadcastString += " yes\r\n";
        else
            broadcastString += "no\r\n";
        for(int i=0;i<3;i++)
            players.get(i).landLordCallBroadcast(broadcastString);

        whosTurn++;
        if(whosTurn<3){
            landLordInvitation();
        }
        else if(whosTurn == 3) {
            if (landLordSubmissionTable[0] == 1) {
                landLordInvitation();
                return;
            } else {
                for(int i=1;i>=0;i--){
                    if(landLordSubmissionTable[i]==1) {
                        landLordAscendingCeremony(i);
                        whosTurn = i;
                        turnName = players.get(i).me.userName;
                        XTurn();
                        return;
                    }
                }
                landLordAscendingCeremony(0);
                whosTurn = 0;
                turnName = players.get(0).me.userName;
                XTurn();
                return;
            }
        }
        else{
            if(landLordSubmissionTable[0] == 1){
                landLordAscendingCeremony(0);
                whosTurn = 0;
                turnName = players.get(0).me.userName;
                XTurn();
                return;
            } else{
                for(int i=1;i>=0;i--){
                    if(landLordSubmissionTable[i]==1) {
                        landLordAscendingCeremony(i);
                        whosTurn = i;
                        turnName = players.get(i).me.userName;
                        XTurn();
                        return;
                    }
                }
                // this should not happen, but just in case
                landLordAscendingCeremony(0);
                whosTurn = 0;
                turnName = players.get(0).me.userName;
                XTurn();
                return;
            }
        }

    }

    public void landLordAscendingCeremony(int landLordIndex)
    {
        landLord = players.get(landLordIndex).me;
        CardDeck landLordsCards = cardDecks.get(landLordIndex);
        for(int i=unfoldedCards.size()-1;i>=0;i--){
            landLordsCards.cards.add(unfoldedCards.get(i));
        }
        String landLordIsString = "landLordIs " + landLordIndex + "\r\n";
        for(int j=0;j<3;j++)
            players.get(j).landLordIs(landLordIsString);
    }

    public void XTimeOut()
    {
        String timeOutString = "XTimeOut" + whosTurn;
        for(int i=0;i<3;i++){
            players.get(i).XTimeOut(timeOutString);
        }
    }

    public void XTurn()
    {
        String broadcastString = "XTurn " + whosTurn + "\r\n";
        for(int i=0;i<3;i++){
            players.get(i).XTurn(broadcastString);
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                XTimeOut();
                toBeLoggedOut.add(players.get(whosTurn));
                if(biggestCards.size() == 0) {
                    cardOut(cardDecks.get(whosTurn).cards.get(0).value + "\r\n", players.get(whosTurn).me.userName);
                }
                else{
                    cardOut(-1 + "\r\n", players.get(whosTurn).me.userName);
                }
                if(toBeLoggedOut.size()==3){
                    gameOver(-1);
                }
            }
        }, 0, 25000);
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                String XTimeString = "XTime " + whosTurn + "10\r\n";
                for(int i=0;i<3;i++){
                    players.get(i).XTime(XTimeString);
                }
            }
        }, 0, 12000);
    }

    public void cardOut(String cardList, String userName)
    {
        if(!players.get(whosTurn).me.userName.equals(userName))
            return;
        //verify card list

        timer.cancel();
        timer2.cancel();

        Card card = new Card();
        int cardEndIndex = 0, cardStartIndex = 0, cardIndex;
        ArrayList<Card> playersCards = cardDecks.get(whosTurn).cards;
        int originalSize = biggestCards.size();
        while (true) {
            cardStartIndex = cardEndIndex + 1;
            cardEndIndex = cardList.indexOf(' ', cardStartIndex);
            if(cardEndIndex == -1)
                break;
            card.value = Integer.parseInt(cardList.substring(cardStartIndex, cardEndIndex));
            cardEndIndex++;//jump through the space between cards
            for(cardIndex=playersCards.size()-1;cardIndex>=0;cardIndex--) {
                if (playersCards.get(cardIndex).value == card.value)
                    break;
            }
            biggestCards.add(playersCards.remove(cardIndex));
        }
        card.value = Integer.parseInt(cardList.substring(cardStartIndex, cardList.length()));
        if(card.value!=-1) {
            for (cardIndex = playersCards.size() - 1; cardIndex >= 0; cardIndex--) {
                if (playersCards.get(cardIndex).value == card.value)
                    break;
            }
            biggestCards.add(playersCards.remove(cardIndex));
            bigPlayer = players.get(whosTurn).me;
            bigIndex = whosTurn;
            for (int i = 0; i < originalSize; i++)
                biggestCards.remove(0);
        }

        cardOutReply(cardList);
        cardInfo();

        for(int i=toBeLoggedOut.size()-1;i>=0;i--){
            if(toBeLoggedOut.get(i).me.userName.equals(userName)){
                toBeLoggedOut.remove(i);
            }
        }

        if(playersCards.size() == 0)
            gameOver(whosTurn);

        whosTurn++;
        XTurn();

    }

    public void cardOutReply(String cardList)
    {
        String cardOutString = "cardOut ";
        cardOutString += whosTurn + " ";
        cardOutString += cardList;
        for(int i=0;i<3;i++){
            players.get(i).cardOutReply(cardOutString);
        }
    }

    public void cardInfo()
    {
        String cardInfoString = "cardInfo";
        for( int i = biggestCards.size()-1;i>=0;i--){
            cardInfoString += " " + biggestCards.get(i).value;
        }
        cardInfoString += " " + bigIndex;
        for(int i=0;i<3;i++){
            cardInfoString += " " + cardDecks.get(i).cards.size();
        }
        cardInfoString += "\r\n";
        for(int i = 0; i<3;i++){
            players.get(i).cardInfo(cardInfoString);
        }
    }

    public String generateCardInfoString()
    {
        String cardInfoString = "cardInfo";
        for( int i = biggestCards.size()-1;i>=0;i--){
            cardInfoString += " " + biggestCards.get(i).value;
        }
        cardInfoString += " " + bigIndex;
        for(int i=0;i<3;i++){
            cardInfoString += " " + cardDecks.get(i).cards.size();
        }
        cardInfoString += "\r\n";
        return cardInfoString;
    }

    public void gameOver(int winner)
    {
        for(int i=toBeLoggedOut.size()-1;i>=0;i--){
            clientThread h = toBeLoggedOut.get(i);
            h.quitRoom();
            h.logout("logout " + h.me.userName + " 123\r\n");
        }

        String gameOverString = "gameOver" + whosTurn + "\r\n";
        for(int i = 0; i<3;i++){
            clientThread h = players.get(i);
            h.gameOver(gameOverString);
            h.notReady();
        }
        theRoom.game = null;
        theRoom.notifyTableStatus();

    }
}
class Card{
    int value;
}
class CardDeck{
    ArrayList<Card> cards;
}

public class fuckLandlordsServer {
    public static void main(String args[]) throws Exception
    {
        Hashtable<String, User> userList = new Hashtable<String, User>();
        ArrayList<Room> roomArray = new ArrayList<Room>();

        ServerSocket soc=new ServerSocket(2015);
        System.out.println("Fuck Landlords Server Started on Port Number 2015");
        while(true){
            clientThread t = new clientThread(soc.accept(), userList, roomArray);
        }
    }
}

class clientThread extends Thread{

    Socket ClientSoc;
    String dataReceiverIP;
    int dataReceiverPort;

    DataInputStream din;
    DataOutputStream dout;
    BufferedReader sin;

    Hashtable<String, User> userList;
    ArrayList<Room> roomArray;

    User me;

    clientThread(Socket soc, Hashtable<String, User> userList, ArrayList<Room> roomArray)
    {
        try
        {
            ClientSoc=soc;
            din=new DataInputStream(ClientSoc.getInputStream());
            dout=new DataOutputStream(ClientSoc.getOutputStream());
            InputStreamReader isr = new InputStreamReader(ClientSoc.getInputStream());
            sin = new BufferedReader(new BufferedReader(isr));
            System.out.println("Fuck Landlord Client Connected ...");

            dataReceiverIP = ClientSoc.getInetAddress().getHostAddress();
            dataReceiverPort = ClientSoc.getPort();

            this.userList = userList;
            this.roomArray = roomArray;

            dout.write(("220 Fuck Landlords Server Ready.\r\n").getBytes("UTF-8"));
            start();

        }
        catch(Exception ex)
        {
            System.out.println("Exception in clientThread(Socket soc)");
        }
    }

    public void run()
    {
        //login
        login();
        //while true: receive and dispatch messages
        while (true){
            int returnCode;
            String clientCommand;
            try{
                clientCommand = sin.readLine();
            }
            catch (Exception ex){
                System.out.println("Exception in run()'s sin.readLine()");
                return;
            }
            System.out.println(clientCommand);
            if(clientCommand.startsWith("logout")){
                logout(clientCommand);
                break;
            } else if(clientCommand.startsWith("startMatching")){
                startMatching();
            } else if(clientCommand.startsWith("tableStatus")){
                tableStatus();
            } else if(clientCommand.startsWith("tablesStatus")){
                tablesStatus();
            } else if(clientCommand.startsWith("openNewRoom")){
                openNewRoom();
            } else if(clientCommand.startsWith("joinUser")){
                joinUser(clientCommand);
            } else if(clientCommand.startsWith("joinRoom")){
                joinRoom(clientCommand);
            } else if(clientCommand.startsWith("quitRoom")){
                quitRoom();
            } else if(clientCommand.startsWith("readyStatus")){
                readyStatus();
            } else if(clientCommand.startsWith("ready")){
                ready();
            } else if(clientCommand.startsWith("notReady")){
                notReady();
            } else if(clientCommand.startsWith("thatJer")){
                thatJerk(clientCommand);
            } else if(clientCommand.startsWith("gameInfo")){
                gameInfo();
            } else if(clientCommand.startsWith("landLordCall")){
                landLordCall(clientCommand);
            } else if(clientCommand.startsWith("cardOut")){
                cardOut(clientCommand);
            }
        }
    }

    public void login()
    {
        while(true) {
            try {
                String clientMessage = sin.readLine();
                int userNameIndex = clientMessage.indexOf(' ') + 1;
                /*
                if (userNameIndex == 0 || userNameIndex == clientMessage.length()) {
                    dout.write(("login fail\r\n").getBytes("UTF-8"));
                    continue;
                }
                */
                int passwordIndex = clientMessage.indexOf(' ', userNameIndex) + 1;
                /*
                if (passwordIndex == 0 || passwordIndex == clientMessage.length() || passwordIndex == userNameIndex + 1) {
                    dout.write(("login fail\r\n").getBytes("UTF-8"));
                    continue;
                }
                */
                String userName = clientMessage.substring(userNameIndex, passwordIndex - 1);
                me = new User();
                me.userName = userName;
                me.theRoom = null;
                User anotherMe = me;
                me = userList.put(userName, me);
                if(me!=null) {
                    me = userList.put(me.userName, me);
                    dout.write(("login fail\r\n").getBytes("UTF-8"));
                    continue;
                }
                me = anotherMe;
                dout.write(("login success\r\n").getBytes("UTF-8"));
                break;
            } catch (Exception ex) {
                System.out.println("Exception in login()");
                return;
            }
        }
    }

    public void logout(String clientMessage)
    {
        try{
            int userNameIndex = clientMessage.indexOf(' ') + 1;
            int passwordIndex = clientMessage.indexOf(' ', userNameIndex) + 1;
            String userName = clientMessage.substring(userNameIndex, passwordIndex - 1);
            User deletedUser = userList.remove(userName);
            if(deletedUser == null){
                System.out.println("Warning: In logout(String clientMessage), Deleted User = NULL");
            }
            dout.write(("logout success\r\n").getBytes("UTF-8"));
            return;
        } catch (Exception ex){
            System.out.println("Exception in logout(String clientMessage)");
            return;
        }
    }

    public int startMatching()
    {
        try{
            Random random = new Random();
            int originalSize = roomArray.size();
            if(originalSize == 0)
                openNewRoomNoDout();
            else
            {
                int partition = random.nextInt(originalSize);
                int searchIndex;
                for(searchIndex = (partition+1)%originalSize;searchIndex!=partition;searchIndex=(searchIndex+1)%roomArray.size())
                {
                    if(roomArray.get(searchIndex).users.size()<3){
                        me.theRoom = roomArray.get(searchIndex);
                        me.theRoom.users.add(this);
                        break;
                    }
                }
                if(searchIndex == partition){
                    if(roomArray.get(searchIndex).users.size()<3){
                        roomArray.get(searchIndex).users.add(this);
                        me.theRoom = roomArray.get(searchIndex);
                    }
                    else{
                        openNewRoomNoDout();
                    }
                }
            }
            String tableStatusString = "joinRoom check " + generateTableStatusString(me.theRoom) + "\r\n";
            me.theRoom.notifyJoin(tableStatusString);

        }
        catch (Exception ex){
            System.out.println("Exception in startMatching()");
            return -1;
        }
        return 0;
    }

    public String generateTableStatusString(Room targetRoom)
    {
        String tableStatus;
        tableStatus = targetRoom.roomNumber + " " + targetRoom.users.size();
        for(int i=0;i<targetRoom.users.size();i++){
            clientThread userThread = targetRoom.users.get(i);
            tableStatus += " " + userThread.me.userName + " " + userThread.me.ready;
        }
        return tableStatus;
    }

    public int tableStatus()
    {
        try {
            String tableStatusString = "tableStatus " + generateTableStatusString(me.theRoom) + "\r\n";
            dout.write(tableStatusString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in tableStatus()");
            return -1;
        }
        return 0;
    }

    public Room openNewRoomNoDout()
    {
        Room newRoom = new Room();
        newRoom.game = null;
        newRoom.users = new ArrayList<>();
        newRoom.users.add(this);
        newRoom.state = 0;
        if(roomArray.size() == 0)
            newRoom.roomNumber = 1;
        else
            newRoom.roomNumber = roomArray.get(roomArray.size()).roomNumber + 1;
        roomArray.add(newRoom);
        me.theRoom = newRoom;
        return newRoom;
    }

    public int openNewRoom()
    {
        try{
            openNewRoomNoDout();
            dout.write(("openNewGame " + me.theRoom.roomNumber + "\r\n").getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in openNewGame()");
            return -1;
        }
        return 0;
    }

    public int tablesStatus()
    {
        try {
            int searchIndex;
            String tablesStatusString = "tablesStatus ";
            String tableList = new String();
            for(searchIndex = 0; searchIndex < roomArray.size();searchIndex++){
                tableList += " " + generateTableStatusString(roomArray.get(searchIndex));
            }
            tablesStatusString += searchIndex + tableList + "\r\n";
            dout.write(tablesStatusString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in tablesStatus()");
            return -1;
        }
        return 0;
    }

    public int joinUser(String clientMessage)
    {
        try {
            int targetUserNameIndex = clientMessage.indexOf(' ') + 1;
            int userNameIndex = clientMessage.indexOf(' ', targetUserNameIndex);
            String targetUserName = clientMessage.substring(targetUserNameIndex, userNameIndex - 1);
            User targetUser = userList.get(targetUserName);

            if(joinRoomNoDout(targetUser.theRoom) == 0){
                String outputString = "joinUser check " + generateTableStatusString(me.theRoom) + "\r\n";
                dout.write(outputString.getBytes("UTF-8"));
                me.theRoom.notifyTableStatus();
            }
            else{
                dout.write(("joinUser fail\r\n").getBytes("UTF-8"));
                return 1;
            }

        } catch (Exception ex){
            System.out.println("Exception in joinUser(String clientMessage)");
            return -1;
        }
        return 0;
    }

    public int joinRoomNoDout(Room targetRoom)
    {
        if(targetRoom.users.size()<3)
        {
            targetRoom.users.add(this);
            me.theRoom = targetRoom;
        }
        else
            return 1;
        return 0;
    }

    public int joinRoom(String clientMessage)
    {
        try{
            int targetRoomIndex = clientMessage.indexOf(' ') + 1;
            int userNameIndex = clientMessage.indexOf(' ', targetRoomIndex) + 1;
            String targetRoomString = clientMessage.substring(targetRoomIndex, userNameIndex - 1);
            int targetRoomNumber = Integer.parseInt(targetRoomString);
            int searchIndex;
            Room targetRoom;
            for(searchIndex = 0;searchIndex<roomArray.size();searchIndex++){
                targetRoom = roomArray.get(searchIndex);
                if(targetRoom.roomNumber == targetRoomNumber){
                    if(joinRoomNoDout(targetRoom) == 0){
                        String outputString = "joinRoom check " + generateTableStatusString(me.theRoom) + "\r\n";
                        me.theRoom.notifyJoin(outputString);
                        return 0;
                    }
                    else{
                        dout.write(("joinRoom fail\r\n").getBytes("UTF-8"));
                        return 1;
                    }
                }
            }
            // room is empty
            targetRoom = openNewRoomNoDout();
            String outputString = "joinRoom check " + generateTableStatusString(targetRoom) + "\r\n";
            dout.write(outputString.getBytes("UTF-8"));
            return 0;
        } catch (Exception ex){
            System.out.println("Exception in joinRoom(String clientMessage)");
            return -1;
        }
    }

    public Room quitRoomNoDout()
    {
        getMEUnready();
        if(me.ready == 1)
            return null;
        boolean quited = me.theRoom.users.remove(this);
        if(quited == false) {
            System.out.println("Warning: In quitRoomNoDout(), me is already deleted");
            return null;
        }
        if(me.theRoom.users.size() == 0){
            roomArray.remove(me.theRoom);
        }
        Room lao = me.theRoom;
        me.theRoom = null;
        return lao;
    }

    public int quitRoom()
    {
        try{
            Room quitedRoom = quitRoomNoDout();
            if(quitedRoom!=null)
            {
                String outputString = "quitRoom check " + me.userName + "\r\n";
                quitedRoom.notifyJoin(outputString);
                dout.write(outputString.getBytes("UTF-8"));
            }

        } catch (Exception ex){
            System.out.println("Exception in quitRoom()");
            return -1;
        }
        return 0;
    }

    public void getMEReady()
    {
        if(me.ready == 0){
            me.ready = 1;
            me.theRoom.state++;
        }
    }

    public void getMEUnready()
    {
        if(me.theRoom.allReadyLock == 1)
            return;
        if(me.ready == 1){
            me.theRoom.state--;
            me.ready = 0;
        }
    }

    public int ready()
    {
        try{
            getMEReady();
            String readyString = "ready " + me.userName + "\r\n";
            me.theRoom.notifyJoin(readyString);
            if(me.theRoom.state == 3) {
                me.theRoom.allReadyLock = 1;
                //start the game
                Game newGame = new Game(me.theRoom.users, me.theRoom);
                me.theRoom.game = newGame;
                newGame.gameStartNotifier();
                newGame.landLordInvitation();
                me.theRoom.allReadyLock = 0;
            }

        } catch (Exception ex){
            System.out.println("Exception in ready()");
            return -1;
        }
        return 0;
    }

    public int notReady()
    {
        try{
            getMEUnready();
            if(me.ready == 0)
                dout.write(("notReady check\r\n").getBytes("UTF-8"));
            else
                dout.write(("notReady fail\r\n").getBytes("UTF-8"));
            me.theRoom.notifyReadyStatus();
        } catch (Exception ex){
            System.out.println("Exception in notReady()");
            return -1;
        }
        return 0;
    }

    public int readyStatus()
    {
        try{
            String readyStatusString = "readyStatus " + me.theRoom.users.size();
            Room myRoom = me.theRoom;
            for(int searchIndex = 0; searchIndex<myRoom.users.size(); searchIndex++){
                readyStatusString += " " + myRoom.users.get(searchIndex).me.ready;
            }
            readyStatusString+="\r\n";
            dout.write(readyStatusString.getBytes("UTF-8"));

        } catch (Exception ex){
            System.out.println("Exception in readyStatus()");
            return -1;
        }
        return 0;
    }

    public int thatJerk(String clientMessage)
    {
        try {
            int userNameStart = clientMessage.indexOf(' ') + 1;
            int userNameEnd = clientMessage.indexOf('\r');
            String targetUserName = clientMessage.substring(userNameStart, userNameEnd);
            User targetUser = userList.get(targetUserName);
            if(targetUser == null){
                System.out.println("Warning: In thatJerk(String clientMessage), targetUser doesn't exist");
                dout.write(("thatJerk check").getBytes("UTF-8"));
                return 1;
            }
            if(targetUser.theRoom != me.theRoom){
                System.out.println("Warning: In thatJerk(String clientMessage), targetUser.theRoom != me.theRoom");
                dout.write(("thatJerk check").getBytes("UTF-8"));
                return 2;
            }
            for(int i=0;i<me.theRoom.users.size();i++){
                clientThread jerkThread = me.theRoom.users.get(i);
                if(jerkThread.me == targetUser){
                    jerkThread.youJerk();
                    me.theRoom.notifyTableStatus();
                    dout.write(("thatJerk check").getBytes("UTF-8"));
                    return 0;
                }
            }

        } catch (Exception ex){
            System.out.println("Exception in thatJerk(String clientMessage)");
            return -1;
        }
        return 0;
    }

    public int youJerk()
    {
        try{
            Room quitedRoom = quitRoomNoDout();
            if(quitedRoom == null)
                return 1;
            quitedRoom.notifyTableStatus();//quitedRoom won't be null due to thatJerk's check
            dout.write(("youJerk " + me.userName + "\r\n").getBytes("UTF-8"));

        } catch (Exception ex){
            System.out.println("Exception in youJerk()");
            return -1;
        }
        return 0;
    }

    public int gameStart()
    {
        try{
            String gameInfoString = "gameStart";
            Game theGame = me.theRoom.game;
            int myPlace = 0;
            for(int i = 0; i < 3;i++){
                String userName = theGame.players.get(i).me.userName;
                gameInfoString += " " + userName;
                if(userName.equals(me.userName)){
                    myPlace = i;
                }
            }
            CardDeck myCards = theGame.cardDecks.get(myPlace);
            for(int i = 0; i<17 ; i++){
                gameInfoString += " " + myCards.cards.get(i).value;
            }
            for(int i = 0; i<3; i++){
                gameInfoString += " " + theGame.unfoldedCards.get(i).value;
            }
            gameInfoString += "\r\n";
            dout.write(gameInfoString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in gameStart()");
            return -1;
        }
        return 0;
    }

    public int gameInfo()
    {
        try {
            String gameInfoString = "gameInfo";
            Game theGame = me.theRoom.game;
            String landLordName = theGame.landLord==null?"":theGame.landLord.userName;
            int myPlace = -1, landLordIndex = -1;
            for(int i = 0; i < 3;i++){
                String userName = theGame.players.get(i).me.userName;
                gameInfoString += " " + userName;
                if(userName.equals(me.userName)){
                    myPlace = i;
                }
                if(landLordName.equals(userName)){
                    landLordIndex = i;
                }
            }
            CardDeck myCards = theGame.cardDecks.get(myPlace);
            for(int i = 0; i<17 ; i++){
                gameInfoString += " " + myCards.cards.get(i).value;
            }
            for(int i = 0; i<3; i++){
                gameInfoString += " " + theGame.unfoldedCards.get(i).value;
            }
            gameInfoString += " " + landLordIndex;
            gameInfoString += "\r\n";
            dout.write(gameInfoString.getBytes("UTF-8"));

        } catch (Exception ex){
            System.out.println("Exception in gameInfo()");
            return -1;
        }
        return 0;
    }

    public int landLord(String invitationString)
    {
        try {
            dout.write(invitationString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in landLord(int whosTurn)");
            return -1;
        }
        return 0;
    }

    public int landLordCall(String clientMessage)
    {
        try{
            Game theGame = me.theRoom.game;
            if(!theGame.turnName.equals(me.userName))
                return 1;
            int answerIndex = clientMessage.indexOf(' ') + 1;
            if(clientMessage.charAt(answerIndex) == 'y')
                me.theRoom.game.landLordRegistration(1);
            else
                me.theRoom.game.landLordRegistration(0);
        }catch (Exception ex){
            System.out.println("Exception in landLordCall(String clientMessage)");
            return -1;
        }
        return 0;
    }

    public int landLordCallBroadcast(String answerString)
    {
        try{
            dout.write(answerString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in landLordCallBroadcast(String answerString)");
            return -1;
        }
        return 0;
    }

    public int landLordIs(String landLordIsString)
    {
        try{
            dout.write(landLordIsString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in landLordCallIs(String landLordIsString)");
            return -1;
        }
        return 0;
    }

    public int XTurn(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in XTurn(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int XTime(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in XTime(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int cardOut(String clientMessage)
    {
        int cardListIndex = clientMessage.indexOf(' ') + 1;
        String cardList = clientMessage.substring(cardListIndex), userName = me.userName;
        me.theRoom.game.cardOut(cardList, userName);
        return 0;
    }

    public int cardInfo(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in cardOutReply(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int cardInfo()
    {
        try{
            String broadcastString = me.theRoom.game.generateCardInfoString();
            dout.write(broadcastString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in cardOutReply(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int gameOver(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in gameOver(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int XTimeOut(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        }catch (Exception ex){
            System.out.println("Exception in XTimeOut(String broadcastString)");
            return -1;
        }
        return 0;
    }

    public int cardOutReply(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        } catch(Exception ex){
            System.out.println("Exception in cardOutReply(String broadcastString");
            return -1;
        }
        return 0;
    }

    public int joinOrQuitBroadcast(String broadcastString)
    {
        try{
            dout.write(broadcastString.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("Exception in joinOrQuitBroadcast(String broadcastString)");
            return -1;
        }
        return 0;
    }

}

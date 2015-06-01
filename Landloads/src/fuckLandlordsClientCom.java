import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by äÈÒã on 6/1/2015.
 */
public class fuckLandlordsClientCom {
    public static void main(String args[]) throws Exception
    {
    }
}

class client{

}
class ClientCom
{
    Socket ClientSoc;

    BufferedReader sin;
    DataInputStream din;
    DataOutputStream dout;
    BufferedReader br;
    client god;

    ClientCom()
    {
        try
        {
            String ip = "";
            ClientSoc=new Socket(ip,2015);
            din=new DataInputStream(ClientSoc.getInputStream());
            dout=new DataOutputStream(ClientSoc.getOutputStream());
            br=new BufferedReader(new InputStreamReader(System.in));

            InputStreamReader isr = new InputStreamReader(ClientSoc.getInputStream());
            sin = new BufferedReader(isr);

            System.out.println(sin.readLine());
        }
        catch(Exception ex)
        {
            System.out.println("Haha");
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

    public void login_send()
    {
        try{
            //your turn
            String userName = "";
            String password = "";
            //my stuff
            String toSend;
            toSend = "login " + userName + ' ' + password + "\r\n";
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("login_send");
            return;
        }
    }

    public boolean login_rec(String clientMessage)
    {
        clientMessage = clientMessage.substring(clientMessage.indexOf(' ')+1);
        if(clientMessage.startsWith("s"))
            return true;
        return false;
    }

    public void logout_send()
    {
        try{
            //your turn
            String userName = "";
            String password = "";
            //my stuff
            String toSend;
            toSend = "logout " + userName + ' ' + password + "\r\n";
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("logout_send");
            return;
        }
    }

    public boolean logout_rec(String clientMessage)
    {
        clientMessage = clientMessage.substring(clientMessage.indexOf(' ')+1);
        if(clientMessage.startsWith("s"))
            return true;
        return false;
    }

    public void startMatching_send()
    {
        try{
            //your turn
            String userName = "";
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
                endElementIndex = clientMessage.length()-2;
            readyList[i] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        }
        //your turn

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
        int endElementIndex = clientMessage.length() - 2;
        newRoomIndex = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
        //your turn

    }

    public void tablesStatus_send()
    {
        try{
            //your turn
            String userName = "";
            //my stuff
            String toSend = "tablesStatus " + userName + "\r\n";
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            System.out.println("tablesStatus_send");
            return;
        }
    }

    public void tablesStatus_rec(String clientMessage) {
        int roomCounter = 0;
        int[] roomIndex, playerCounter;
        String[] playerList;
        int[] readyList;
        //my stuff
        int startElementIndex = clientMessage.indexOf(' ') + 1;
        int endElementIndex = clientMessage.indexOf(' ', startElementIndex);
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
                        endElementIndex = clientMessage.length() - 2;
                    readyList[i * 3 + j] = Integer.parseInt(clientMessage.substring(startElementIndex, endElementIndex));
                }
            }
        }
        // your turn

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

}
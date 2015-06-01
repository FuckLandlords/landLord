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

    public void login_send(String toSend)
    {
        try{
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            return;
        }
    }

    public boolean login_rec()
    {
        String rec = receiveMessage();
        rec = rec.substring(rec.indexOf(' ')+1);
        if(rec.startsWith("s"))
            return true;
        return false;
    }

    public void logout_send(String toSend)
    {
        try{
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            return;
        }
    }

    public boolean logout_rec()
    {
        String rec = receiveMessage();
        rec = rec.substring(rec.indexOf(' ')+1);
        if(rec.startsWith("s"))
            return true;
        return false;
    }

    public void startMatching_send(String toSend)
    {
        try{
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            return;
        }
    }

    public boolean startMatching_rec()
    {
        return false;
    }

    public void openNewRoom_send(String toSend)
    {
        try{
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            return;
        }
    }

    public void openNewRoom_rec()
    {

    }

    public void tablesStatus_send(String toSend)
    {
        try{
            dout.write(toSend.getBytes("UTF-8"));
        } catch (Exception ex){
            return;
        }
    }

    public void tablesStatus_rec()
    {

    }

}
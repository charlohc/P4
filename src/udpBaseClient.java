import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


//TODO: Hvordan få info om hva serveren svarte på client siden
public class udpBaseClient
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf = null;



        System.out.println("Connected");
        System.out.println("You can now type in an addition or subtraction calculation, you must have space between numbers");
        System.out.println("To exit type: exit");

        // loop while user not enters "exit"
        while (true)
        {
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket DpSend =
                    new DatagramPacket(buf, buf.length, ip, 1234);

            // Step 3 : invoke the send-call to actually send
            // the data.
            ds.send(DpSend);


            // break the loop if user enters "bye"
            if (inp.equals("exit"))
                break;
        }
    }
}
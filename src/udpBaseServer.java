import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;


public class udpBaseServer
{
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        System.out.println("connected, waiting for input from client");
        while (true)
        {

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            ds.receive(DpReceive);

            String data = String.valueOf(data(receive));

            String[] calculating;
            int firstNumber = 0;
            int secondNumber = 0;
            int sum = 0;

            if (data.contains(" ") && data.matches(".*\\d.*")) {
                calculating = data.split(" ");
                firstNumber = Integer.parseInt(calculating[0]);
                secondNumber = Integer.parseInt(calculating[2]);

                if (Objects.equals(calculating[1], "+")) {

                    System.out.printf(" Sent from the client: %s\n", data);
                    sum = (firstNumber + secondNumber);
                    System.out.println(sum);

                } else if (Objects.equals(calculating[1], "-")) {

                    System.out.printf(" Sent from the client: %s\n", data);
                   sum = (firstNumber - secondNumber);
                    System.out.println(sum);

                } else {
                    System.out.println(" Sent from the client: letters");
                    System.out.println("You did not type in an equation containing + or -");
                }
            } else {
                System.out.println(" Sent from the client: letters");
                System.out.println("You did not type in an equation containing + or -");
            }

            // Exit the server if the client sends "bye"
            if (data.equals("exit"))
            {
                System.out.println("Exiting.....");
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H2hmacAndSend 
{
	static void writeToFile(String fileName, Object object) throws Exception
	{
		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(object);
		oout.close();
	}
	public static void main(String[] args) 
	{
		try
		{
			String message = "This is the data I am sending";
			
			// Write secret key
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = kg.generateKey();
			writeToFile("data/secretKey", sk);
			
			// Write message
			writeToFile("data/message.txt", message);
		
			// Write hmac
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			byte[] hmac = mac.doFinal(message.getBytes());
			writeToFile("data/hmac", hmac);
			
			System.out.println(hmac.length);
		}
		catch(Exception e)
		{
			// Print error
			e.printStackTrace();
		}
	}
}

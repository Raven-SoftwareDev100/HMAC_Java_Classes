import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H1hmacEx 
{
	public static void main(String[] args)
	{
		try
		{
			String message = "Hello World";
			
			// Generate secret key
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = kg.generateKey();
		
			// Get instance of Mac object and initialize
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			byte[] hmacSignature = mac.doFinal(message.getBytes());
			System.out.println(hmacSignature.length);
			
			// Receiver
			Mac mac2 = Mac.getInstance("HmacSHA256");
			mac2.init(sk);
			byte[] hmacSignature2 = mac2.doFinal(message.getBytes());
			System.out.println(hmacSignature2.length);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

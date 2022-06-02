import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class H3hmacAndVerify 
{
	static Object readFromFile(String fileName) throws Exception
	{
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream oin = new ObjectInputStream(fin);
		Object object = oin.readObject();
		oin.close();
		return object;
	}
	public static void main(String[] args) throws Exception
	{
		try
		{
			// Read secret key
			SecretKey sk = (SecretKey) readFromFile("data/secretKey");
			byte[] sentiHmac = (byte[]) readFromFile("data/hmac");
			String message = (String) readFromFile("data/message.txt");
			
			// Calculate hmac
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			byte[] myHmac = mac.doFinal(message.getBytes());
			
			System.out.println("Check: " + Arrays.equals(sentiHmac, myHmac));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

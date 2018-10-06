package dies.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {
	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
	
	public static String passwordGenerator(String username, String password, String passowrdHash) {
		int hashIterations = 10000;
        String algorithmName =  "SHA-512";  
        String salt1 = username;  
        String salt2 =  new  SecureRandomNumberGenerator().nextBytes().toHex();  
        if (passowrdHash != null) {
        	salt2 =  passowrdHash;
        }
        SimpleHash hash =  new  SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
        String encodedPassword = hash.toHex();
        return encodedPassword;
	}
}

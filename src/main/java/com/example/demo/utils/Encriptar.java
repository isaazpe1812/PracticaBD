package com.example.demo.utils;


import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

public class Encriptar {
    /* Constante predeterminada del vector*/
	public static final String IV = "1234567890123456";
	public static final String pkey ="wwwwwwwwwwwwwww1wwwwwwwwwwwwwww1";
	
	public static byte[] aesEncrypt(String content, String pkey) {
		try {
			//SecretKey secretKey = generateKey(pkey);
			//byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), "AES");
			Security.addProvider(new BouncyCastleProvider());// Para usar el relleno PKCS7Padding, se debe agregar un proveedor que admita PKCS7Padding
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");// "Algoritmo / Cifrado / Relleno"
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skey, iv);// Inicializa el encriptador
			byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
			return encrypted; // cifrado
		} catch (Exception e) {
			System.out.println("aesEncrypt() method error:"+e);
		}
		return null;
	}
	/** 
	  * Consigue la llave 
	 * @param secretKey 
	 * @return 
	 * @throws Exception  
	 */  
	private static SecretKey  generateKey(String secretKey) throws Exception{  
	    // Prevenir la generación aleatoria de claves en Linux
		Provider p=Security.getProvider("SUN");
	    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG",p);  
	    secureRandom.setSeed(secretKey.getBytes());  
	    KeyGenerator kg = KeyGenerator.getInstance("AES");  
	    kg.init(secureRandom);
	    // Generar clave  
	    return kg.generateKey();  
	} 
	/**
	 * @Title: aesEncryptStr 
	  * @ Descripción: cifrado simétrico aes 
	  * @param content contenido original antes del cifrado
	  * @param pkey tiene 16 caracteres de largo, 128 bits
	  * @return base64EncodeStr aes contenido después del cifrado
	 * @throws
	 */
	public static String aesEncryptStr(String content, String pkey){
		byte[] aesEncrypt = aesEncrypt(content, pkey);
		System.out.println("Conjunto de bytes cifrados:"+Arrays.toString(aesEncrypt));
		String base64EncodeStr = Base64.encodeBase64String(aesEncrypt);
		System.out.println("Base64EncodeStr después del cifrado:"+base64EncodeStr);
		return base64EncodeStr;
	}
	/**
	 * @throws Exception
	 * @Title: aesDecodeStr 
	  * @Description: el descifrado devolverá NULL
	  * @param content base64 cadena procesada
	  * @param pkey key
	  * @return String return type
	 * @throws
	 */
	public static String aesDecodeStr(String content, String pkey) throws Exception  {
		try {
			System.out.println("Contenido a descifrar:"+content);
			byte[] base64DecodeStr = Base64.decodeBase64(content);
			System.out.println("base64DecodeStr:"+Arrays.toString(base64DecodeStr));
			byte[] aesDecode = aesDecode(base64DecodeStr, pkey);
			System.out.println("aesDecode:"+Arrays.toString(aesDecode));
			if(aesDecode == null){
				return null;
			}
			String result;
			result = new String(aesDecode,"UTF-8");
			System.out.println("aesDecode result:"+result);
			return result;
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			throw new Exception("Excepción de descifrado");
		}
	}

	/**
	  * Descifrar 128 bit
	 * 
	  * @param content byte array antes del descifrado
	  * @param pkey key
	  * @return resultado descifrado conjunto de bytes
	 * @throws Exception 
	 */
	public static byte[] aesDecode(byte[] content, String pkey) throws Exception {
		
		//SecretKey secretKey = generateKey(pkey);
		//byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), "AES");
		IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");// crear cifrado
		cipher.init(Cipher.DECRYPT_MODE, skey,iv);// Inicializa el descifrador
		byte[] result = cipher.doFinal(content);
		return result; // descifrar

	}
	
	//método de prueba
	/*public static void main(String []args) throws Exception{
		//Texto sin formato
		String content ="AlfayOmega07";
		//Llave
		String pkey ="wwwwwwwwwwwwwww1wwwwwwwwwwwwwww1";
		System.out.println("Mensaje a cifrar:"+content);
		System.out.println("Llave:"+pkey);
		String aesEncryptStr =aesEncryptStr(content,pkey);
		System.out.println("Mensaje cifrado:"+aesEncryptStr);
		String aesDecodeStr =aesDecodeStr(aesEncryptStr,pkey);
		System.out.println("Descifrar mensaje:"+aesDecodeStr);
		System.out.println("Si los contenidos antes y después del cifrado son iguales:"+aesDecodeStr.equals(content));
	}*/

}

package driver;

import helper.Encryption;

/**
 * Created by apple on 2016/12/14.
 */
public class DriverForEncryption {
	public static void main(String[] args) {
		String i = "徐亚婧";
//		System.out.println(Encryption.string2MD5(i));
		System.out.println(Encryption.convertMD5(i));
		System.out.println(Encryption.convertMD5(Encryption.convertMD5(i)));
//		Encryption.byteArrayToHex(i.getBytes());
	}
}

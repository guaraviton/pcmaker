package br.com.pcmaker.common.util;


public class DigestUtils {
	
    public static String sha1Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data);
    }
}

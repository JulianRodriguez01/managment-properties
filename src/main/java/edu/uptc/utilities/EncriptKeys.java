package edu.uptc.utilities;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EncriptKeys {
	
	public static final String ENCRIPT_TYPE = "utf-8";
    
    public static String encript(String password) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(password.getBytes(ENCRIPT_TYPE));
    }
    
    public static String decrypt(String passwordEncript) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(passwordEncript.getBytes());
        return new String(decode, ENCRIPT_TYPE);
    }
}
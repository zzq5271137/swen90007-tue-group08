package security;

import java.util.regex.Pattern;

public class ParamValidator {
	
	/**
	 * Regular Expression: Validate username
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z0-9_]\\w{3,18}$";
	
	/**
	 * Regular Expression: Validate password
	 */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    
    /**
	 * Regular Expression: Validate item size & weight 1~999
	 */
    public static final String REGEX_ITEM_PARAM = "^[1-9][0-9]{0,2}(.[0-9]{0,2})?$";
    
    /**
	 * Regular Expression: Validate address
	 */
    public static final String REGEX_ADDRESS = "^[a-zA-Z]*$";
    
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
    
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    
    public static boolean isItemParam(String item_param) {
        return Pattern.matches(REGEX_ITEM_PARAM, item_param);
    }
    
    public static boolean isAddress(String address) {
        return Pattern.matches(REGEX_ADDRESS, address);
    }
}

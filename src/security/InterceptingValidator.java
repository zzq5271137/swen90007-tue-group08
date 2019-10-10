package security;

public class InterceptingValidator {
	
	/**
	 * Check Parameters from Login Page form
	 * 
	 * @return whether the username is valid or not
	 */
	public boolean checkLoginParam(String username, String password) {
		boolean loginParam = ParamValidator.isUsername(username) && ParamValidator.isPassword(password);
		return loginParam;
	}
	
	/**
	 * Check Parameters from Edit Order form 
	 * 
	 * @param address 
	 * @param item_weight 
	 * @param item_size 
	 * @return whether the parameters are valid or not
	 */
	public boolean checkOrderParam(String item_size, String item_weight, String address) {
		boolean inputParam = ParamValidator.isItemParam(item_size) 
				&& ParamValidator.isItemParam(item_weight) && ParamValidator.isAddress(address);
		return inputParam;
	}
}
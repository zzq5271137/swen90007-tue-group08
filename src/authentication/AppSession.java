package authentication;

import domain.User;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;

public class AppSession {

    public static final String USER_ATTRIBUTE_NAME = "user";
    public static final String CUSTOMER_ROLE = "customer";
    public static final String COURIER_ROLE = "courier";

    public static boolean hasRole(String role) {
        return SecurityUtils.getSubject().hasRole(role);
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static void init(User user) {
    	System.out.println("the user has been added to session: " + user.getUsername());

        SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
    }

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
    }
}
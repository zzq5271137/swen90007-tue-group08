package security;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import domain.Courier;
import domain.Customer;
import domain.User;

public class AppRealm extends JdbcRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();

        final User user = User.getUser(username);

        if (user == null) {
            System.out.println(
                    "No account found for user with username " + username);
            return null;
        }
        // collect the subject's principal (username) and credential (password)
        // and submit them to an authentication system
        return new SimpleAuthenticationInfo(username, user.getPassword(),
                getName());
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(
            PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();
        if (principals.isEmpty()) {
            System.out.println("Given principals to authorize are empty.");
            return null;
        }

        String username = (String) principals.getPrimaryPrincipal();
        final User user = User.getUser(username);

        if (user == null) {
            System.out.println(
                    "No account found for user with username " + username);
            return null;
        }

        if (user instanceof Customer) {
            roles.add(AppSession.CUSTOMER_ROLE);
        } else if (user instanceof Courier) {
            roles.add(AppSession.COURIER_ROLE);
        }
        return new SimpleAuthorizationInfo(roles);
    }
}

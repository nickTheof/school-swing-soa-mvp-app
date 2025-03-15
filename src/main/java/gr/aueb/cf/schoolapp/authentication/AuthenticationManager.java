package gr.aueb.cf.schoolapp.authentication;

import java.util.Arrays;

public class AuthenticationManager {
    private AuthenticationManager() {

    }

    public static Boolean validate(String username, char[] password) {
        return username.matches("^[aA]dmin$") && Arrays.equals(password, "12345".toCharArray());
    }
}

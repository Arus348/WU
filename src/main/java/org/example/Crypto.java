package org.example;

import org.mindrot.jbcrypt.BCrypt;

public class Crypto {

    private final String salt = BCrypt.gensalt(12);
    private String password;
    private String hassPassword;

    public String getHassPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean passwordMatches(String password, String hassPassword) {
        return BCrypt.checkpw(password, hassPassword);
    }

}

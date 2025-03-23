package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class LoginUser {

    private UserRepository userRepository;
    String emailUser;
    String passUser;
    User loggedUser;
    static int licznik;

    public LoginUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        licznik++ ;
        System.out.println("Podaj adres email");
        emailUser = scanner.next();
        System.out.println("Podaj haslo");
        passUser = scanner.next();


        List<User> users = null;
        try {
            users = userRepository.findAll();
        } catch (FileNotFoundException e) {
            System.out.println("Bald. Kod bledu 64"); // 64 brak pliku z danymi
          //  throw new RuntimeException(e);
        }

        for (User user: users){
            Crypto crypto = new Crypto();
            if ( user.getEmail().equals(emailUser) && crypto.passwordMatches(passUser,user.getPassword()) ){
                this.loggedUser = user;
            }
        }

        if (loggedUser == null){
            System.out.println("Logowanie nieuda, sprobuj ponownie");
            if (licznik<=3){
                login();
            } else {
                System.out.println("Zbyt dużo prób logowań odczekaj 1000 minut");
            }
        }

    }

    public User getLoggedUser() {
        return loggedUser;
    }

}

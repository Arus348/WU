package org.example;

import Users.User;

import java.io.FileNotFoundException;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Scanner;

public class LoginUser {

    private UserRepository userRepository;
    private String emailUser;
    private String passUser;
    private User loggedUser;
    static int licznik;
    private int iloscProb = 3;
    private String text;

    public LoginUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login() {
        if (iloscProb - licznik == 1) {
            text = "Panel logowania (masz " + (iloscProb - licznik) + " próbę) ";
        } else if (ValueRange.of(2, 3, 4).isValidValue(iloscProb - licznik)) {
            text = "Panel logowania (masz " + (iloscProb - licznik) + " próby) ";
        } else {
            text = "Panel logowania (masz " + (iloscProb - licznik) + " prób) ";
        }

        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj adres email: ");
        emailUser = scanner.next();
        System.out.print("Podaj haslo: ");
        passUser = scanner.next();
        licznik++;

        List<User> users = null;
        try {
            users = userRepository.findAll();
        } catch (FileNotFoundException e) {
            System.out.println("Bald. Kod bledu 64"); // Blad 64 brak pliku z danymi
        }

        for (User user : users) {
            Crypto crypto = new Crypto();
            if (user.getEmail().equals(emailUser) && crypto.passwordMatches(passUser, user.getPassword())) {
                this.loggedUser = user;
            }
        }

        if (loggedUser == null) {
            System.out.println("Logowanie nieuda, sprobuj ponownie");
            if (licznik < 3) {
                login();
            } else {
                System.out.println("Zbyt dużo prób logowań, konto zostaje zablokowane.\nProsze skontaktować się z administratorem. ");
            }
        }

    }

    public User getLoggedUser() {
        return loggedUser;
    }

}

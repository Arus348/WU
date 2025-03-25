package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        User user1 = new User("Adam", "Nowak", "adam@onet.eu", crypto.getHassPassword("adam@34"), LocalDate.of(1925, 10, 15));
        User user2 = new User("Jan", "Kowalski", "janek@interia.pl", crypto.getHassPassword("jaki485"), LocalDate.of(1995, 10, 20));
        User user3 = new User("Mire", "Frązek", "mirk@gmail.com", crypto.getHassPassword("mirr15"), LocalDate.of(2005, 1, 25));

        FileUserRepository fileUserRepository = new FileUserRepository();
        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);
        fileUserRepository.insert(user3);

        LoginUser loginUser = new LoginUser(fileUserRepository);
        loginUser.login();
        User userLog = loginUser.getLoggedUser();
        if (userLog != null) {
            System.out.println("Zalogowalo: " + userLog.getFirstName() + " " + userLog.getLastName());
        }

    }

    private static void printUser(User user) {
        System.out.println("Imię i nazwisko: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Adres email: " + user.getEmail());

        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        System.out.println("Data urodzenia: " + user.getDateOfBirth().format(europeanDateFormatter));

    }
}
package org.example;

import Users.Student;
import Users.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUser() {
        User user1 = new Student("Jan", "Kowalski", "jan@kowalski.pl", "bkbksdbc", LocalDate.of(2015, 12, 10), 4414);
        String firstName = user1.getFirstName();
        String lastName = user1.getLastName();
        String email = user1.getEmail();
        assertEquals("Jan", firstName);
        assertEquals("Kowalski", lastName);
        assertEquals("jan@kowalski.pl", email);

    }
}
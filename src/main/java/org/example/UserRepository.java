package org.example;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserRepository {

    void insert(User user);

    List<User> findAll() throws FileNotFoundException;

}

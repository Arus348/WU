package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class FileUserRepository implements UserRepository{

    private int areExists;

    @Override
    public void insert(User newUser) {
        List<User> users = findAll();

        //sprawdzenie czy email już w bazie istenieje, jak tak to nie zakladamy konta
        for(User user: users){
            areExists=0;
            if (user.areExists(newUser)){
                areExists=1;
                System.out.println("Taki email już istnieje: " + user.getEmail() + ". Prosze się zalogować.");
                break;
            }
        }
        if (areExists != 1) {
            newUser.setPassword(BCrypt.hashpw(newUser.getPassword(),BCrypt.gensalt(12)));
            users.add(newUser);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            try {
                String json = objectMapper.writeValueAsString(users);

                File file = new File("D:/Pliki studia/fileUsersTypFile.json");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();

            } catch (JsonProcessingException e) {
                System.out.println("Plik nie zostal zapisany!!!");
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<User> findAll() {

        FileInputStream fileInputStream ;
        try {
            fileInputStream = new FileInputStream("D:/Pliki studia/fileUsersTypFile.json");
            byte[] bytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            return objectMapper.readValue(bytes, new TypeReference<List<User>>() {});

        } catch (FileNotFoundException e) {
           return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }
}

package Users;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME
        , include = JsonTypeInfo.As.EXISTING_PROPERTY
        , property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "Student")
        , @JsonSubTypes.Type(value = Teacher.class, name = "Techer")
        , @JsonSubTypes.Type(value = Administrator.class, name = "Administrator")
})
public abstract class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public User(){
    };

    public User(String firstName, String lastName, String email, String password, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean areExists(User newUser){
        return this.email.equalsIgnoreCase(newUser.email);
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return  this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

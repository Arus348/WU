package Users;

import java.time.LocalDate;

public class Administrator extends User {

    private final String administratos = "Administrator";

    public Administrator(String firstName, String lastName, String email, String password, LocalDate dateOfBirth) {
        super(firstName, lastName, email, password, dateOfBirth);
    }

    public String getAdministratos() {
        return administratos;
    }
}

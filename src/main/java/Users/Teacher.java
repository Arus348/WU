package Users;

import java.time.LocalDate;

public class Teacher extends User {

    private String academicDegree;

    public Teacher(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String academicDegree) {
        super(firstName, lastName, email, password, dateOfBirth);
        this.academicDegree = academicDegree;
    }

    public String getAcademicDegree() {
        return this.academicDegree;
    }

}

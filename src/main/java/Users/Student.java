package Users;

import java.time.LocalDate;

public class Student extends User {

    private long albumNumber;

    public Student(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, long albumNumber) {
        super(firstName, lastName, email, password, dateOfBirth);
        this.albumNumber = albumNumber;
    }

    public long getAlbumNumber() {
        return albumNumber;
    }
}

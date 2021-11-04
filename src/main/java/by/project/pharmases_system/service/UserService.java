package by.project.pharmases_system.service;

import by.project.pharmases_system.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<List<User>> getListUsers(String surname);

    public ResponseEntity<User> getUserById( Long id);

    public ResponseEntity<User> createUser(User userBody);

    public ResponseEntity<User> updateUser(Long id, User userBody);

    public ResponseEntity<User> deleteUser(Long id);
}

package by.project.pharmases_system.repository;

import by.project.pharmases_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findBySurnameContaining(String surname);
}

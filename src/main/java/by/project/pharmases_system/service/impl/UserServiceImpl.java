package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.User;
import by.project.pharmases_system.repository.UserRepo;
import by.project.pharmases_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<List<User>> getListUsers(String surname){
        try {
            List<User> listUsers = null;
            if (surname == null)
                listUsers = userRepo.findAll();
            else
                listUsers = userRepo.findBySurnameContaining(surname);

            if (listUsers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getUserById(Long id){
        Optional<User> userData = userRepo.findById(id);

        if(!userData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(User userBody){
        try {
            User user = userRepo.save(new User(
                    userBody.getSurname(),
                    userBody.getName(),
                    userBody.getPhone(),
                    userBody.getEmail(),
                    userBody.getPosition(),
                    userBody.getPharmacy(),
                    userBody.getLogin(),
                    userBody.getPassword()));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(Long id, User userBody){
        Optional<User> user = userRepo.findById(id);

        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User userTmp = user.get();
        userTmp.setSurname(userBody.getSurname());
        userTmp.setName(userBody.getName());
        userTmp.setPhone(userBody.getPhone());
        userTmp.setEmail(userBody.getEmail());
        userTmp.setPosition(userBody.getPosition());
        userTmp.setPharmacy(userBody.getPharmacy());
        userTmp.setLogin(userBody.getLogin());
        userTmp.setPassword(userBody.getPassword());

        return new ResponseEntity<>(userRepo.save(userTmp), HttpStatus.OK);
    }

    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

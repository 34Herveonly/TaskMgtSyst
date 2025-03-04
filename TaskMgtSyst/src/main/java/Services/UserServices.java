package Services;

import Dto.UserDto;
import Entity.Users;
import Entity.roles;
import Repository.RolesRepository;
import Repository.usersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    public  usersRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;


    public UserServices(usersRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users registerUser(UserDto userDto) {

    Users user = new Users();
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());

    return userRepository.save(user);
    }

    public List<Users> getAllUsers() {
    return userRepository.findAll();
    }
    public Optional<Users> getUserById(long id) {
    return userRepository.findById(id);
    }
    public void deleteUser(long id) {
    userRepository.deleteById(id);
    }

    @Transactional
    public void assignRoleToUser(long id, String roleName) {
        // Step 1: Find the user
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Step 2: Find the role
        roles role = RolesRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found!"));

        // Step 3: Check if the user already has this role
        if (user.getRoles().contains(role)) {
            throw new RuntimeException("User already has this role!");
        }

        // Step 4: Add the role to the user's roles list
        user.getRoles().add(role);

        // Step 5: Save the updated user
        userRepository.save(user);
      }

      public Users updateUser(long id, UserDto userDto) throws Exception {
        Optional<Users> user= userRepository.findById(id);

        if(user.isPresent()){
            new Exception("User Exists you can proceed with your editing!");
        }
        user.orElseThrow(()->new Exception("User with id :" + id + "not found!"));

        Users users=new Users();
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(users);
      }


      public String authenticateUser(UserDto userDto) throws Exception {
        Optional<Optional<Users>> username= Optional.ofNullable(userRepository.findByName(userDto.getUsername()));

        if(username.isPresent() && passwordEncoder.matches(userDto.getPassword(), username.get().get().getPassword())){
            return userDto.getUsername();
        }
        else{
            throw new Exception("Username or password is incorrect!");
        }
      }
    }

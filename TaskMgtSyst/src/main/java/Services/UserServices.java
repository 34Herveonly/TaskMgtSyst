package Services;

import Dto.UserDto;
import Entity.User;
import Entity.role;
import JWT.JwtUtil;
import Repository.RolesRepository;
import Repository.usersRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    public usersRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public RolesRepository rolesRepository;


    public UserServices(usersRepository userRepository, BCryptPasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }

    public User registerUser(UserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void assignRoleToUser(long id, String roleName) {
        // Step 1: Find the user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Step 2: Find the role
        role role = rolesRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found!"));

        // Step 3: Check if the user already has this role
        if (user.getRoles().contains(role)) {
            throw new RuntimeException("User already has this role!");
        }

        user.getRoles().add(role);

        userRepository.save(user);
    }

    public User updateUser(long id, UserDto userDto) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("User with id " + id + "is not found!");
        }

        User existingUser = user.get();
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(existingUser);
    }


    public String authenticateUser(@NotNull UserDto userDto) throws Exception {
        Optional<User> user = userRepository.findByName(userDto.getUsername());
        if (user.isEmpty() || !passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
            throw new Exception("Invalid Username or Password!");
        }
        return JwtUtil.generateToken(user.get().getUsername());
    }
}
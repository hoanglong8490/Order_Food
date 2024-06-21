package com.pizzahouse.Service;

import com.pizzahouse.DTO.UserDTO;
import com.pizzahouse.Entity.Role;
import com.pizzahouse.Entity.User;
import com.pizzahouse.Payload.Request.UserRequest;
import com.pizzahouse.Repository.RoleRepository;
import com.pizzahouse.Repository.UserRepository;
import com.pizzahouse.Service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(UserRequest userRequest) {
        try {
            //kiem tra xem da co email trong data chua
            if(userRepository.findByEmail(userRequest.getEmail()) != null){
                throw new Exception("User already exists");
            }
            User user = new User();
            user.setEmail(userRequest.getEmail());
            //ma hoa mat khau
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
            user.setPassword(encodedPassword);
            //mac dinh signup va add user role la user
            roleRepository.findById(2).ifPresent(user::setRoles);
            userRepository.save(user);
            return true;
        }catch (Exception e) {
            System.out.println("Error insert users: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        Role role = new Role();
        role.setRoleName(role.getRoleName());

        for (User user : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setRoleName(user.getRoles().getRoleName());

            userDTOList.add(userDTO);

        }
        return userDTOList;
    }

    @Override
    public boolean updateUser(int userId, UserRequest userRequest) {
        Optional<User> users = userRepository.findById(userId);
        try{
            if(users.isPresent()){
                User currentUser = users.get();
                currentUser.setEmail(userRequest.getEmail());

                String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
                currentUser.setPassword(encodedPassword);

                userRepository.save(currentUser);
            }
            return true;
        }catch (Exception e){
            System.out.println("Error update users: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        if(userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}

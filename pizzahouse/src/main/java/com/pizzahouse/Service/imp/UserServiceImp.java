package com.pizzahouse.Service.imp;

import com.pizzahouse.DTO.UserDTO;
import com.pizzahouse.Payload.Request.UserRequest;

import java.util.List;

public interface UserServiceImp {
    boolean addUser(UserRequest userRequest);
    List<UserDTO> getAllUser();
    boolean updateUser(int userId ,UserRequest userRequest);
    boolean deleteUser(int userId);
}

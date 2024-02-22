package com.travelwanderbooking.services.impl;

import com.travelwanderbooking.config.AppConstants;
import com.travelwanderbooking.entites.Role;
import com.travelwanderbooking.entites.User;
import com.travelwanderbooking.exceptions.APIException;
import com.travelwanderbooking.payloads.UserDTO;
import com.travelwanderbooking.repositories.RoleRepo;
import com.travelwanderbooking.repositories.UserRepo;
import com.travelwanderbooking.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        try{
              User user = modelMapper.map(userDTO,User.class);

            Role role = roleRepo.findById(AppConstants.USER_ID).get();
            user.getRoles().add(role);

            User registerUser = userRepo.save(user);
            return userDTO;
        }catch (DataIntegrityViolationException e) {
            throw new APIException("User already exists with emailId: " + userDTO.getEmail());
        }
    }
}

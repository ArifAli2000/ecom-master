package com.ecom.backend.User.service;

import com.ecom.backend.product.exception.ResourceNotFoundException;
import com.ecom.backend.User.model.User;
import com.ecom.backend.User.payload.UserDto;
import com.ecom.backend.User.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;
    //userDto to user
    public UserDto createUser(UserDto userDto){
       User user = this.mapper.map(userDto, User.class);
        User saveUser = this.userRepo.save(user);
       // user to userDto
       UserDto saveUserDto = this.mapper.map(saveUser, UserDto.class);
        return saveUserDto;
    }
    public UserDto getById(String Id){
       User findByuserId = this.userRepo.findById(Id).orElseThrow(() ->new ResourceNotFoundException("User not found by this id"+ Id));
       UserDto userDto =this.mapper.map(findByuserId,UserDto.class);
        return userDto;
    }
    public UserDto deleteUser(String Id){
        User findById = this.userRepo.findById(Id).orElseThrow(() ->new ResourceNotFoundException("User not found by this id"+ Id));
        UserDto userDto =this.mapper.map(findById,UserDto.class);
        return userDto;

    }
    public List<UserDto> findAllUser() {
        List<User> findAll = this.userRepo.findAll();
        //user -> userDto
        List<UserDto> collect = findAll.stream().map(each -> this.mapper.map(each, UserDto.class)).collect(Collectors.toList());
        return collect;
    }
        public UserDto update(UserDto t, String Id) {
            User u=userRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("User not found by this id"));
            u.setPhone(t.getPhone());
            u.setPassword(t.getPassword());
            u.setName(t.getName());
            u.setGender(t.getGender());
            u.setEmail(t.getEmail());
            u.setDate(t.getDate());
            u.setAddress(t.getAddress());
            u.setActive(t.isActive());
            u.setAbout(t.getAbout());
            User Updateuser=this.userRepo.save(u);
            UserDto dto = this.mapper.map(Updateuser, UserDto.class);
            return dto;

    }


}

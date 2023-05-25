package com.ecom.backend.user.controller;

import com.ecom.backend.user.dto.UserDto;
import com.ecom.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto){
        Date date =new Date();
        userDto.setDate(date);
        System.out.println(userDto.getId());
        UserDto createUser = this.userService.createUser(userDto);
        return  new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
    }
    @GetMapping("{Id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable String Id){
        UserDto byId = this.userService.getById(Id);
        return new ResponseEntity<UserDto>(byId,HttpStatus.OK);
    }

    @DeleteMapping("{Id}")
    public void deleteUser(@PathVariable String Id){
        this.userService.deleteUser(Id);

    }
    @GetMapping
    ResponseEntity<List<UserDto>> findAllUser(){
       List<UserDto> findAllUser = this.userService.findAllUser();
       return new ResponseEntity<List<UserDto>>(findAllUser,HttpStatus.OK);
    }
    @PutMapping("{Id}")
    public ResponseEntity<UserDto> update(@PathVariable String Id, @RequestBody UserDto t){
        UserDto updateUser = this.userService.update(t, Id);
        return new ResponseEntity<UserDto>(updateUser,HttpStatus.RESET_CONTENT);
    }
}

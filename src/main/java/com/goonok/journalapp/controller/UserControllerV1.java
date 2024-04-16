package com.goonok.journalapp.controller;

import com.goonok.journalapp.entity.JournalEntity;
import com.goonok.journalapp.entity.User;
import com.goonok.journalapp.service.JournalEntryService;
import com.goonok.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/*
    //TODO - This controller is used to save the journal data into MongoDB
    // The calling stack = Controller => Service => Repository;
    // now setting up the http return code by ResponseEntity
 */
@RestController
@RequestMapping("/user")
public class UserControllerV1 {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> allUser = userService.getAllUser();

        if (allUser != null && !allUser.isEmpty()){
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user){
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        if (user != null){
            userService.deleteUserByUserName(username);
            return new ResponseEntity<>(user, HttpStatus.GONE);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId myId){
        Optional<User> user = userService.findUserById(myId);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserById(@RequestBody User newUser, @PathVariable String username){
       User oldUser = userService.findUserByUserName(username);
        if(oldUser != null){
            oldUser.setUserName(!newUser.getUserName().isEmpty() ? newUser.getUserName() : oldUser.getUserName());
            oldUser.setPassword(!newUser.getPassword().isEmpty() ? newUser.getPassword() : oldUser.getPassword()) ;
            userService.saveUser(oldUser);
            return new ResponseEntity<>(oldUser, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}

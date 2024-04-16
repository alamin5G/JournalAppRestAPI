package com.goonok.journalapp.service;

import com.goonok.journalapp.entity.User;
import com.goonok.journalapp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveUser(User user){
        userEntryRepository.save(user);
    }

    public List<User> getAllUser(){
        return userEntryRepository.findAll();
    }

    public Optional<User> findUserById(ObjectId id){

        return userEntryRepository.findById(id);
    }

    public void deleteUserByUserName(String username){
        userEntryRepository.deleteByUserName(username);
    }

    public User findUserByUserName(String username){
        return userEntryRepository.findByUserName(username);
    }
}

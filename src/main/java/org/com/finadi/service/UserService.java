package org.com.finadi.service;

import org.com.finadi.dao.UserRepository;
import org.com.finadi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public User saveCliente(User user) {
    return userRepository.save(user);
  }

  public List<User> getUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  public User getUserById(String id) {
    return userRepository.findById(id).orElseThrow();
  }

  public void deleteUserById(String id) {
    userRepository.deleteById(id);
  }

  public User updateUser(User user) {
    getUserById(user.getId());
    return userRepository.save(user);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow();
  }


}

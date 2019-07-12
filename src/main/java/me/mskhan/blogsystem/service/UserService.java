package me.mskhan.blogsystem.service;

import me.mskhan.blogsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByUsername(String theUsername);
    public User findByEmail(String theEmail);
    public void save(User theUser);
    public List<User> findAllByUsername();
}

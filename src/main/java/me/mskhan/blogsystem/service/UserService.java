package me.mskhan.blogsystem.service;

import me.mskhan.blogsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername(String theUsername);
    public void save(User theUser);
}

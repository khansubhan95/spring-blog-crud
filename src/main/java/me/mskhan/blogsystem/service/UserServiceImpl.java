package me.mskhan.blogsystem.service;

import me.mskhan.blogsystem.dao.UserRepository;
import me.mskhan.blogsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findByUsername(String theUsername) {
        return userRepository.findUserByUsername(theUsername);
    }

    @Override
    @Transactional
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User theUser = userRepository.findUserByUsername(s);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ANONYMOUS"));
        return new org.springframework.security.core.userdetails.User(theUser.getUsername(), theUser.getPassword(), grantedAuthoritySet);
    }
}

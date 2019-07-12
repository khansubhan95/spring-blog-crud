package me.mskhan.blogsystem.dao;


import me.mskhan.blogsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUsername(String theUsername);
    public User findUserByEmail(String theEmail);
}

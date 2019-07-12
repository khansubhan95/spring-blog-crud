package me.mskhan.blogsystem.service;

import me.mskhan.blogsystem.entity.Post;
import me.mskhan.blogsystem.entity.User;

import java.util.List;

public interface PostService {
    public List<Post> findAll();
    public Post findById(int theId);
    public void save(Post thePost);
    public void deleteById(int theId);
    public List<Post> findByUser(User theUser);
}

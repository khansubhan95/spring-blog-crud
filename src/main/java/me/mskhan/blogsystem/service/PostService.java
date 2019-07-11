package me.mskhan.blogsystem.service;

import me.mskhan.blogsystem.entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> findAll();
    public Post findById(int theId);
    public void save(Post thePost);
    public void deleteById(int theId);
}

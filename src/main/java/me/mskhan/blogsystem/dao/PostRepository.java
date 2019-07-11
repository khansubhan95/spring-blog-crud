package me.mskhan.blogsystem.dao;

import me.mskhan.blogsystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findAllByOrderByDateDesc();
}

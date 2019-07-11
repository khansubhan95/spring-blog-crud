package me.mskhan.blogsystem.controller;

import me.mskhan.blogsystem.entity.Post;
import me.mskhan.blogsystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String listPosts(Model theModel) {
        List<Post> thePosts = postService.findAll();
        theModel.addAttribute("posts", thePosts);
        return "list-posts";
    }

    @GetMapping("/post")
    public String showPost(@RequestParam("postId") int theId, Model theModel) {
        Post thePost = postService.findById(theId);
        theModel.addAttribute("post", thePost);
        return "show-post";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Post thePost = new Post();
        theModel.addAttribute("post", thePost);
        return "post-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("post") Post thePost) {
        postService.save(thePost);
        return "redirect:/posts";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("postId") int theId, Model theModel) {
        Post thePost = postService.findById(theId);
        theModel.addAttribute("post", thePost);
        return "post-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("postId") int theId) {
        postService.deleteById(theId);
        return "redirect:/posts";
    }

}

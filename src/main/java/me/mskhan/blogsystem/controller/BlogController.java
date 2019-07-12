package me.mskhan.blogsystem.controller;

import me.mskhan.blogsystem.entity.Post;
import me.mskhan.blogsystem.entity.User;
import me.mskhan.blogsystem.service.PostService;
import me.mskhan.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/posts")
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String theUsername = auth.getName();

        if (thePost.getId() != 0) {
            Post theTempPost = postService.findById(thePost.getId());
            String postOwnerUsername = theTempPost.getAuthor().getUsername();
            if (!theUsername.equals(postOwnerUsername)) {
                return "redirect:/posts";
            }
        }
        User theUser = userService.findByUsername(theUsername);
        thePost.setAuthor(theUser);
        Set<Post> tempPosts = theUser.getPosts();
        tempPosts.add(thePost);
        theUser.setPosts(tempPosts);
        postService.save(thePost);
        return "redirect:/posts";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("postId") int theId, Model theModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String theUsername = auth.getName();

        String postOwnerUsername = postService.findById(theId).getAuthor().getUsername();

        if (!theUsername.equals(postOwnerUsername)) {
            return "redirect:/posts";
        }

        Post thePost = postService.findById(theId);
        theModel.addAttribute("post", thePost);
        return "post-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("postId") int theId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String theUsername = auth.getName();

        String postOwnerUsername = postService.findById(theId).getAuthor().getUsername();

        if (!theUsername.equals(postOwnerUsername)) {
            return "redirect:/posts";
        }

        postService.deleteById(theId);
        return "redirect:/posts/myPosts?userId="+theUsername;
    }

    @GetMapping("/myPosts")
    public String myPosts(@RequestParam("userId") String theUsername, Model theModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();

        if (!loggedInUsername.equals(theUsername)) {
            return "redirect:/posts";
        }

        User theUser = userService.findByUsername(theUsername);
        List<Post> thePosts = postService.findByUser(theUser);
        theModel.addAttribute("thePosts", thePosts);
        return "user-posts";
    }

}

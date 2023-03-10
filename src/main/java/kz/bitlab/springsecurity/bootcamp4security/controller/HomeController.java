package kz.bitlab.springsecurity.bootcamp4security.controller;

import kz.bitlab.springsecurity.bootcamp4security.dto.PostDTO;
import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.service.PostService;
import kz.bitlab.springsecurity.bootcamp4security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping(value = "/")
    public String index(Model model){
        List<PostDTO> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping(value = "/sign-in")
    public String signIn(Model model){
        return "signinpage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage(Model model){
        return "profile";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String admin(Model model){
        return "admin";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model){
        return "accessdenied";
    }

    @GetMapping(value = "/sign-up")
    public String signUp(Model model){
        return "registerpage";
    }

    @PostMapping(value = "/to-register")
    public String toRegsiter(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_re_password") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName){

        Boolean result = userService.registerUser(email, password, rePassword, fullName);
        if(result!=null){
            if(result==Boolean.TRUE){
                return "redirect:/sign-up?success";
            }else{
                return "redirect:/sign-up?passworderror";
            }
        }else{
            return "redirect:/sign-up?usererror";
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/add-news")
    public String addPost(PostDTO post){
        postService.createPost(post);
        return "redirect:/admin?success";
    }
}
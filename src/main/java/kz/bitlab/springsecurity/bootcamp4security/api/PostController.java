package kz.bitlab.springsecurity.bootcamp4security.api;

import kz.bitlab.springsecurity.bootcamp4security.dto.PostDTO;
import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> getPosts(){
        return postService.getPosts();
    }

    @GetMapping(value = "{id}")
    public PostDTO getPost(@PathVariable(name = "id") Long id){
        return postService.getPost(id);
    }

    @PostMapping
    public PostDTO addPost(@RequestBody PostDTO post){
        return postService.createPost(post);
    }

    @PutMapping
    public PostDTO updatePost(@RequestBody PostDTO post){
        return postService.updatePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}

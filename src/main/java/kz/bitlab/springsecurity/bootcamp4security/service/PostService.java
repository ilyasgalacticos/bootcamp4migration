package kz.bitlab.springsecurity.bootcamp4security.service;

import kz.bitlab.springsecurity.bootcamp4security.dto.PostDTO;
import kz.bitlab.springsecurity.bootcamp4security.mapper.PostMapper;
import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;

    public PostDTO createPost(PostDTO post){
        Post postEntity = postMapper.toEntity(post);
        if(!(postEntity.getAuthor()!=null && postEntity.getAuthor().getId()!=null)){
            postEntity.setAuthor(userService.getCurrentUser());
        }
        return postMapper.toDto(postRepository.save(postEntity));
    }

    public List<PostDTO> getPosts(){
        return postMapper.toDtoList(postRepository.findAllByOrderByIdDesc());
    }

    public PostDTO getPost(Long id){
        return postMapper.toDto(postRepository.findById(id).orElseThrow());
    }

    public PostDTO updatePost(PostDTO post){
        Post postEntity = postMapper.toEntity(post);
        if(!(postEntity.getAuthor()!=null && postEntity.getAuthor().getId()!=null)){
            postEntity.setAuthor(userService.getCurrentUser());
        }
        return postMapper.toDto(postRepository.save(postEntity));
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
}
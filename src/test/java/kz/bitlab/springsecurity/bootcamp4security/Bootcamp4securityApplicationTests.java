package kz.bitlab.springsecurity.bootcamp4security;

import kz.bitlab.springsecurity.bootcamp4security.dto.PostDTO;
import kz.bitlab.springsecurity.bootcamp4security.mapper.PostMapper;
import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.model.User;
import kz.bitlab.springsecurity.bootcamp4security.repository.PostRepository;
import kz.bitlab.springsecurity.bootcamp4security.service.PostService;
import kz.bitlab.springsecurity.bootcamp4security.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Bootcamp4securityApplicationTests {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private PostRepository postRepository;

	@Test
	public void testPostDTOMapper(){

		User user = new User();
		user.setId(10L);
		user.setFullName("Testbek Testbekov");
		user.setEmail("testbek@gmail.com");

		Post post = new Post();
		post.setId(10L);
		post.setAuthor(user);
		post.setTitle("New Post");
		post.setContentText("New Post Text");

		PostDTO postDTO = postMapper.toDto(post);

		Assertions.assertNotNull(postDTO); // if(postDTO!=null)
		Assertions.assertEquals(post.getId(), postDTO.getId());
		Assertions.assertEquals(post.getTitle(), postDTO.getTitle());
		Assertions.assertEquals(post.getContentText(), postDTO.getContent());
		Assertions.assertNotNull(postDTO.getAuthor());
		Assertions.assertEquals(post.getAuthor().getId(), postDTO.getAuthor().getId());
		Assertions.assertEquals(post.getAuthor().getEmail(), postDTO.getAuthor().getEmail());
		Assertions.assertEquals(post.getAuthor().getFullName(), postDTO.getAuthor().getFullName());
	}

	@Test
	public void checkPostInsert(){
		User user = createUser();
		user = userService.insertUser(user);

		Post post = new Post();
		post.setAuthor(user);
		post.setContentText("My Content Text");
		post.setTitle("My Title");

		postRepository.deleteAll();

		PostDTO createdPost = postService.createPost(postMapper.toDto(post));

		Assertions.assertNotNull(createdPost);
		Assertions.assertNotNull(createdPost.getId());

		List<Post> posts = postRepository.findAll();
		Assertions.assertNotNull(posts);
		Assertions.assertTrue(posts.size() > 0);

		Post checkPost = postRepository.findById(createdPost.getId()).orElseThrow();
		Assertions.assertNotNull(checkPost);
		Assertions.assertEquals(createdPost.getId(), checkPost.getId());
		Assertions.assertEquals(createdPost.getTitle(), checkPost.getTitle());
		Assertions.assertEquals(createdPost.getContent(), checkPost.getContentText());

		Assertions.assertNotNull(checkPost.getAuthor());
		Assertions.assertEquals(checkPost.getAuthor().getId(), createdPost.getAuthor().getId());

		postRepository.deleteAll();
		userService.deleteUser(user.getId());
	}

	private User createUser(){
		User user = new User();
		user.setId(1L);
		user.setFullName("Test Testov");
		return user;
	}
}

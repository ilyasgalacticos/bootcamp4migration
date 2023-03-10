package kz.bitlab.springsecurity.bootcamp4security.mapper;

import kz.bitlab.springsecurity.bootcamp4security.dto.PostDTO;
import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "contentText", target = "content")
    PostDTO toDto(Post post);

    @Mapping(source = "content", target = "contentText")
    Post toEntity(PostDTO postDTO);

    List<PostDTO> toDtoList(List<Post> posts);
}
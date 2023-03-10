package kz.bitlab.springsecurity.bootcamp4security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
}

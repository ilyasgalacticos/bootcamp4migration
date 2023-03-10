package kz.bitlab.springsecurity.bootcamp4security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDTO {

    private Long id;
    private String fullName;
    private String email;
}
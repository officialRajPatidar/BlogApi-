package com.blogapis.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    // Avoid bidirectional references by using @JsonIgnore
 

    private int id;
    private String content;
}

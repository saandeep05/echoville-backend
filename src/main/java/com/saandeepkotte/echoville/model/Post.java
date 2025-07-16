package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity<Post, PostDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private EchoUser createdBy;

    @Override
    public PostDTO toDto() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setTitle(title);
        postDTO.setContent(content);
        postDTO.setPublic(isPublic);
        postDTO.setCommunityId(community.getId());
        postDTO.setCreatorId(createdBy.getId());
        return postDTO;
    }

    @Override
    public Post toModel(PostDTO postDTO) {
        this.setId(postDTO.getId());
        this.setTitle(postDTO.getTitle());
        this.setContent(postDTO.getContent());
        this.setPublic(postDTO.isPublic());
        return this;
    }
}


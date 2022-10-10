package com.be.grooming_mood.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String profileImg;

    @Builder
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImg = user.getProfileImg();
    }
}
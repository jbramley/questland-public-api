package com.questland.handbook.publicmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    private String googleId;
    private String name;
    private String email;
    private String profileImgUrl;

    // Null will be default
    private Boolean darkTheme;

}

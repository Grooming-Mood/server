package com.be.grooming_mood.diary.domain;

import com.be.grooming_mood.common.domain.BaseTimeEntity;
import com.be.grooming_mood.feeling.domain.FeelingType;
import com.be.grooming_mood.like.domain.Likes;
import com.be.grooming_mood.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "diary")
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "feeling", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeelingType feeling;

    @Column(name = "diary_content", nullable = false)
    private String diaryContent;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Builder
    public Diary(User user, FeelingType feeling,
                 String diaryContent,
                 Boolean isPublic){
        this.user = user;
        this.feeling = feeling;
        this.diaryContent = diaryContent;
        this.isPublic = isPublic;
        this.createdDate = LocalDateTime.now();
        this.likesCount = getLikesCount();
    }

    public void update(String diaryContent, Boolean isPublic){
        this.diaryContent = diaryContent;
        this.isPublic = isPublic;
    }

    //좋아요 개수 매핑
    @JsonIgnoreProperties({"diary"})
    @JsonBackReference
    @OneToMany(mappedBy = "diary", cascade = CascadeType.REMOVE)
    private List<Likes> likesList;

    @Column(name = "like_count")
    @ColumnDefault("0")
    private Integer likesCount;

    @PrePersist
    public void prePersist() {
        this.likesCount = this.likesCount == null ? 0 : this.likesCount;
    }

    public void updateLikesCount() {
        this.likesCount = this.likesCount + 1;
    }

}

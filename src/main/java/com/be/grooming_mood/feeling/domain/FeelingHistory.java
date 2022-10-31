package com.be.grooming_mood.feeling.domain;

import com.be.grooming_mood.common.domain.BaseTimeEntity;
import com.be.grooming_mood.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="feelingHistory")
public class FeelingHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "feeling", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeelingType feeling;

    @Builder
    public FeelingHistory(User user,
                          FeelingType feeling){
        this.user = user;
        this.feeling = feeling;
        this.createdDate = LocalDateTime.now();

    }
}

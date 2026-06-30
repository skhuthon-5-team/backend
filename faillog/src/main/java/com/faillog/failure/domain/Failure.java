package com.faillog.failure.domain;


import com.faillog.failure.api.dto.request.FailureUpdateRequestDto;
import com.faillog.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Failure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "failure_id")
    private Long failureId;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FailureCategory category;

    @Column(nullable = false, length = 1000)
    private String situation;

    @Column(nullable = false, length = 1000)
    private String choice;

    @Column(length = 1000)
    private String emotion;

    @Column(nullable = false)
    private Long viewCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Failure(
            String title,
            FailureCategory category,
            String situation,
            String choice,
            String emotion,
            User user
    ) {
        this.title = title;
        this.category = category;
        this.situation = situation;
        this.choice = choice;
        this.emotion = emotion;
        this.user = user;
        this.viewCount = 0L;
    }

    public void update(FailureUpdateRequestDto failureUpdateRequestDto) {
        this.title = failureUpdateRequestDto.title();
        this.category = failureUpdateRequestDto.category();
        this.situation = failureUpdateRequestDto.situation();
        this.choice = failureUpdateRequestDto.choice();
        this.emotion = failureUpdateRequestDto.emotion();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}

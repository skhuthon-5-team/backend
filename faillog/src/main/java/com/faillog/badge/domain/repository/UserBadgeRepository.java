package com.faillog.badge.domain.repository;

import com.faillog.badge.domain.Badge;
import com.faillog.badge.domain.UserBadge;
import com.faillog.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {

    List<UserBadge> findByUser(User user);

    Optional<UserBadge> findByUserAndBadge(User user, Badge badge);
}

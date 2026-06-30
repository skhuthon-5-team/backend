package com.faillog.failure.domain.repository;

import com.faillog.failure.domain.Failure;
import com.faillog.failure.domain.FailureCategory;
import com.faillog.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    List<Failure> findByUser(User user);
    List<Failure> findByCategory(FailureCategory category);
    List<Failure> findByTitleContaining(String keyword);
    List<Failure> findAllByOrderByViewCountDesc();
}

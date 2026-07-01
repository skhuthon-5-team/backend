package com.faillog.failure.domain.repository;

import com.faillog.failure.domain.Failure;
import com.faillog.failure.domain.FailureCategory;
import com.faillog.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FailureRepository extends JpaRepository<Failure, Long> {
    @Query(
            value = """
                    select f
                    from Failure f
                    join fetch f.user
                    """,
            countQuery = """
                    select  count(f)
                    from Failure f
                    """)
    Page<Failure> findAll(Pageable pageable);

    @Query(
            value = """
                    select f
                    from Failure f
                    join fetch f.user
                    where f.user = :user
                    """,
            countQuery = """
                    select count(f)
                    from Failure f
                    where f.user = :user
                    """)
    Page<Failure> findByUser(@Param("user") User user, Pageable pageable);

    @Query(
            value =  """
                    select f
                    from Failure f
                    join fetch f.user
                    where f.category = :category
                    """,
            countQuery = """
                    select count(f)
                    from Failure f
                    where f.category = :category
                    """)
    Page<Failure> findByCategory(@Param("category") FailureCategory category, Pageable pageable);

    @Query(
            value = """
                    select f
                    from Failure f
                    join fetch f.user
                    where f.title like concat('%', :keyword, '%')
                    """,
            countQuery = """
                    select count(f)
                    from Failure f
                    where f.title like concat('%', :keyword, '%')
                    """)
    Page<Failure> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
}

package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    @Query("SELECT u from User u " +
            "JOIN FETCH u.department d " +
            "JOIN FETCH u.role r " +
            "where u.updateDate <= :updateDate and d.id = :departmentId")
    List<User> findUsersByDepartmentUpdatedBefore(@Param("departmentId")Long departmentId,
                                                  @Param("updateDate") Instant updateDate);

    @Query("SELECT u FROM User u " +
            "JOIN FETCH u.role r " +
            "JOIN FETCH u.department d " +
            "WHERE r.name = 'Admin' " +
            "AND u.active = true "
            + "AND d IN (SELECT d2 FROM Department d2 " +
            "JOIN User u2 ON u2.department = d2 " +
            "JOIN u2.role r2" +
            " where r2.name = 'Admin' " +
            "AND u2.active = true " +
            "GROUP BY d2 " +
            "HAVING COUNT(u2) > :admCount)")
    List<User> findActiveAdminsInDepartmentWithMoreThanCountActiveAdmin(@Param("admCount") Long admCount,
                                                                        Pageable pageable);

}

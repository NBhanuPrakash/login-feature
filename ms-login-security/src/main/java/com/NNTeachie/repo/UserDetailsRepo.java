package com.NNTeachie.repo;

import com.NNTeachie.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, String> {
    UserDetails findByUserName(String username);
}

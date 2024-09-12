package com.NNTeachie.repo;

import com.NNTeachie.entity.SignUpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpDetailsRepo extends JpaRepository<SignUpDetails,String> {
    SignUpDetails findByUserName(String userName);
}

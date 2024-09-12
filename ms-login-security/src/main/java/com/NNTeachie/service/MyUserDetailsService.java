package com.NNTeachie.service;

import com.NNTeachie.entity.UserPrincipals;
import com.NNTeachie.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.NNTeachie.entity.UserDetails byUserName = userDetailsRepo.findByUserName(username);
        if (byUserName==null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not found");
        }

        return new UserPrincipals(byUserName);
    }
}

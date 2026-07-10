//package com.example.ShlopApp.Identity.Infrastructure.adapter;
//
//import com.example.ShlopApp.Identity.Domain.port.AccountRepoPort;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class AccountUserDetailsService implements UserDetailsService {
//
//    private final JpaAccountRepository repository;
//
//    public AccountUserDetailsService(JpaAccountRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return (UserDetails) repository.findByUsername(username);
//    }
//}

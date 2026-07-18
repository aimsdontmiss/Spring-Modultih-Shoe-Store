package com.example.ShlopApp.Identity.Infrastructure.adapter;

import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.port.AccountRepoPort;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountPersistenceMapper;
import com.example.ShlopApp.Identity.Infrastructure.security.AccountPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final JpaAccountRepository repository;
    private final AccountPersistenceMapper mapper = new AccountPersistenceMapper();

    public AccountUserDetailsService(JpaAccountRepository repository) {
        this.repository = repository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return (UserDetails) repository.findByUsername(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AccountEntity entity = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username));

        Account account = mapper.toDomain(entity);

        return new AccountPrincipal(
            account.getAccountId(),
            account.getUsername(),
            account.getPasswordHash()
        );
    }

}

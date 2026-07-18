package com.example.ShlopApp.Identity.Presentation;

import com.example.ShlopApp.Identity.Application.api.dto.AccountDto;
import com.example.ShlopApp.Identity.Application.api.dto.AccountResponseMapper;
import com.example.ShlopApp.Identity.Application.internals.command.LoginCommand;
import com.example.ShlopApp.Identity.Application.internals.command.RegisterCommand;
import com.example.ShlopApp.Identity.Application.internals.interactor.LoginUseCase;
import com.example.ShlopApp.Identity.Application.internals.interactor.RegisterUseCase;
import com.example.ShlopApp.Identity.Domain.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
class AccountController {

    private final AccountResponseMapper mapper;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AccountController(
            AccountResponseMapper mapper,
            RegisterUseCase registerUseCase,
            LoginUseCase loginUseCase,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository
    ) {
        this.mapper = mapper;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDto> createAccount(@RequestBody RegisterCommand command) {
        Account account = registerUseCase.execute(command);
        return ResponseEntity.ok(mapper.toDto(account));
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDto> loginAccount(
            @RequestBody LoginCommand command,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                command.username(),
                                command.password()
                        )
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(
                context,
                request,
                response
        );

        return ResponseEntity.ok(
                mapper.toDto(loginUseCase.execute(command))
        );

    }

    @GetMapping("/isLogged")
    public ResponseEntity<Boolean> isLogged(Authentication authentication) {
        return ResponseEntity.ok(authentication.isAuthenticated());
    }

}

package com.web_dev.notebook.securityJwt.domain.authenticationFacade;

import com.web_dev.notebook.securityJwt.domain.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    UserDetailsImpl getUserDetailsImpl();
}

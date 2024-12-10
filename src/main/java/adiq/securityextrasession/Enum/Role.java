package adiq.securityextrasession.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER;


    public String getAuthority() {
        return name();
    }
}

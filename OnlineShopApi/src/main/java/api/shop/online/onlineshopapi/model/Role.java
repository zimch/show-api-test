package api.shop.online.onlineshopapi.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USER_PERM)),
    ADMIN(Set.of(Permission.USER_PERM, Permission.ADM_PERM));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissionSet().stream()
                .map(permissionSet -> new SimpleGrantedAuthority(permissionSet.getPermission()))
                .collect(Collectors.toSet());
    }
}

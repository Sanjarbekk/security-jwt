package paybek.uz.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static paybek.uz.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, PROVINCE_READ, PROVINCE_WRITE)),
    SUPERADMIN(Sets.newHashSet(STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
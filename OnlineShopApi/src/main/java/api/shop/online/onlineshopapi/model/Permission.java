package api.shop.online.onlineshopapi.model;

public enum Permission {
    USER_PERM("user:perm"), // read articles
    ADM_PERM("adm:perm"); // add article

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }


    public String getPermission() {
        return permission;
    }
}

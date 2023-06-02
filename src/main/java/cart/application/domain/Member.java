package cart.application.domain;

public class Member {

    private final Long id;
    private final String email;
    private final String password;
    private final long point;

    public Member(Long id, String email, String password, long point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getPoint() {
        return point;
    }

    public boolean hasPassword(String password) {
        return this.password.equals(password);
    }
}

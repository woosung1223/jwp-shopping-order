package cart.entity;

import cart.exception.application.ExceedOwnedPointException;

public class Member {

    private final Long id;
    private final String email;
    private final String password;
    private long point;

    public Member(Long id, String email, String password, long point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.point = point;
    }

    public void addPoint(Long pointToAdd) {
        point += pointToAdd;
    }

    public void usePoint(long pointToUse) {
        if (point - pointToUse < 0) {
            throw new ExceedOwnedPointException();
        }
        point -= pointToUse;
    }

    public boolean hasPassword(String password) {
        return this.password.equals(password);
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
}

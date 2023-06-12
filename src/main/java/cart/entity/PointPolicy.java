package cart.entity;

import java.util.function.Function;

public enum PointPolicy {

    AVAILABLE(price -> price),
    UNAVAILABLE(price -> 0L),
    ;

    private final Function<Long, Long> policy;

    PointPolicy(Function<Long, Long> policy) {
        this.policy = policy;
    }

    public static PointPolicy of(boolean pointAvailable) {
        if (pointAvailable) {
            return AVAILABLE;
        }
        return UNAVAILABLE;
    }

    public long calculatePoint(long price) {
        return this.policy.apply(price);
    }

    public boolean isPointAvailable() {
        return this.equals(AVAILABLE);
    }
}

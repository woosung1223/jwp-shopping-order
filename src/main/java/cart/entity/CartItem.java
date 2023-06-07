package cart.entity;

import cart.exception.application.IllegalMemberException;

import java.util.Objects;

public class CartItem {

    private final Long id;
    private final Product product;
    private final Member member;
    private long quantity;

    public CartItem(Long id, long quantity, Product product, Member member) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.member = member;
    }

    public void updateQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void validateIsOwnedBy(Member member) {
        if (!Objects.equals(this.member.getId(), member.getId())) {
            throw new IllegalMemberException();
        }
    }

    public boolean isOutOfStock() {
        return quantity <= 0;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }
}

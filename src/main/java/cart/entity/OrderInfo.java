package cart.entity;

public class OrderInfo {

    private final Long id;
    private final Product product;
    private final String name;
    private final String imageUrl;
    private final long price;
    private final long quantity;

    public OrderInfo(Long id, Product product, String name, long price, String imageUrl, long quantity) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public long calculateAvailablePoint() {
        return quantity * product.calculateAvailablePoint();
    }

    public long calculateEarnedPoint() {
        return quantity * product.calculatePointToEarn();
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getQuantity() {
        return quantity;
    }
}

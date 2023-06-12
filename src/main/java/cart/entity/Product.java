package cart.entity;

public class Product {

    private final Long id;
    private final String name;
    private final long price;
    private final String imageUrl;
    private final double pointRatio;
    private final PointPolicy pointPolicy;

    public Product(Long id, String name, long price, String imageUrl, double pointRatio, PointPolicy pointPolicy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.pointRatio = pointRatio;
        this.pointPolicy = pointPolicy;
    }

    public long calculateAvailablePoint() {
        return pointPolicy.calculatePoint(price);
    }

    public long calculatePointToEarn() {
        double point = pointRatio / 100 * price;
        return (long) Math.ceil(point);
    }

    public Long getId() {
        return id;
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

    public double getPointRatio() {
        return pointRatio;
    }

    public PointPolicy getPointPolicy() {
        return pointPolicy;
    }
}

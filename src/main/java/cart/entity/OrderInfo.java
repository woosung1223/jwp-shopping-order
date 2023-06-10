package cart.entity;

public class OrderInfo {

    private final Long id;
    private final Product product;
    private final ProductImage productImage;
    private final long quantity;

    public OrderInfo(Long id, Product product, ProductImage productImage, long quantity) {
        this.id = id;
        this.product = product;
        this.productImage = productImage;
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

   public String getProductName() {
        return productImage.getName();
   }

   public String getProductImageUrl() {
        return productImage.getImageUrl();
   }

   public long getProductPrice() {
        return productImage.getPrice();
   }

    public long getQuantity() {
        return quantity;
    }
}

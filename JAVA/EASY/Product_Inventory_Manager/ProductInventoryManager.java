// package JAVA.EASY.Product_Inventory_Manager;
import java.util.ArrayList;
import java.util.Scanner;

class Product {
  private String productId;
  private String productName;
  private double price;
  private int quantity;

  public Product(String productId, String productName, double price, int quantity) {
    this.productId = productId;
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }

  public String getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void displayProductDetails() {
    System.out.println("Product ID: " + productId);
    System.out.println("Product Name: " + productName);
    System.out.println("Price: " + price);
    System.out.println("Quantity: " + quantity);
  }
}



public class ProductInventoryManager {
 
  private ArrayList<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    if(product.getPrice() < 0 || product.getQuantity() < 0 || product.getProductId() == null || product.getProductName() == null || product.getProductName().isEmpty()) {
      throw new IllegalArgumentException("Invalid product details. Ensure that price and quantity are non-negative, and product ID and name are not null or empty.");
    }
    
    if(getProductById(product.getProductId()) != null) {
      throw new IllegalArgumentException("Product with ID " + product.getProductId() + " already exists.");
    }

    products.add(product);
    System.out.println("Product added successfully.");
  }

  public void listProducts() {
    if(products.isEmpty()) {
      System.out.println("No products in inventory.");
      return;
    }
    for(Product product : products) {
      product.displayProductDetails();
      System.out.println("--------------------");
    }
  }

  public Product getProductById(String productId) {
    for(Product product : products) {
      if(product.getProductId().equals(productId)) {
        return product;
      }
    }
    return null;
  }

  public void updateProduct(String productId, double newPrice, int newQuantity) {
    Product product = getProductById(productId);
    if(product == null) {
      throw new IllegalArgumentException("Product with ID " + productId + " not found.");
    }
    if(newPrice < 0 || newQuantity < 0) {
      throw new IllegalArgumentException("Price and quantity must be non-negative.");
    }
    product.setPrice(newPrice);
    product.setQuantity(newQuantity);
    System.out.println("Product updated successfully.");
  }

  public void deleteProduct(String productId) {
    Product product = getProductById(productId);
    if(product == null) {
      throw new IllegalArgumentException("Product with ID " + productId + " not found.");
    }
    products.remove(product);
    System.out.println("Product deleted successfully.");
  }

  public static void main(String[] args) {
    ProductInventoryManager manager = new ProductInventoryManager();
    Scanner sc = new Scanner(System.in);
    boolean exit = false;

    while(!exit) {
      System.out.println("\n--- Product Inventory Manager ---");
      System.out.println("1. Add Product");
      System.out.println("2. List Products");
      System.out.println("3. Update Product");
      System.out.println("4. Delete Product");
      System.out.println("5. Exit");
      System.out.print("Choose an option: ");
      
      int choice = sc.nextInt();
      sc.nextLine(); 

      try {
        switch(choice) {
          case 1:
            System.out.print("Enter Product ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            manager.addProduct(new Product(id, name, price, quantity));
            break;
          case 2:
            manager.listProducts();
            break;
          case 3:
            System.out.print("Enter Product ID to update: ");
            String updateId = sc.nextLine();
            System.out.print("Enter New Price: ");
            double newPrice = sc.nextDouble();
            System.out.print("Enter New Quantity: ");
            int newQuantity = sc.nextInt();
            manager.updateProduct(updateId, newPrice, newQuantity);
            break;
          case 4:
            System.out.print("Enter Product ID to delete: ");
            String deleteId = sc.nextLine();
            manager.deleteProduct(deleteId);
            break;
          case 5:
            exit = true;
            break;
          default:
            System.out.println("Invalid option. Please try again.");
        }
      } catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }

    sc.close();
    System.out.println("Exiting Product Inventory Manager.");
  }
}

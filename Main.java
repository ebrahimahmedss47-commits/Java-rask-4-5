import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Item {
    private String name;
    private double price;
    private int availableCopies;

    public Item(String name, double price, int availableCopies) {
        this.name = name;
        this.price = price;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void sell() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("Out of stock");
        }
    }

    public void returnItem() {
        availableCopies++;
    }

    public abstract String getCategory();
}

abstract class Clothing extends Item {
    private String size;
    private String color;

    public Clothing(String name, double price, int availableCopies,
                     String size, String color) {
        super(name, price, availableCopies);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
}

class Shirt extends Clothing {
    private String material;

    public Shirt(String name, double price, int copies, String size,
                 String color, String material) {
        super(name, price, copies, size, color);
        this.material = material;
    }

    public String getCategory() {
        return "Clothing - Shirt";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getSize() + " | " + getColor()
                + " | " + material;
    }
}

class Socks extends Clothing {
    private int pairsInPack;

    public Socks(String name, double price, int copies, String size,
                 String color, int pairsInPack) {
        super(name, price, copies, size, color);
        this.pairsInPack = pairsInPack;
    }

    public String getCategory() {
        return "Clothing - Socks";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getSize() + " | " + getColor()
                + " | Pairs: " + pairsInPack;
    }
}

class Hat extends Clothing {
    private String style;

    public Hat(String name, double price, int copies, String size,
               String color, String style) {
        super(name, price, copies, size, color);
        this.style = style;
    }

    public String getCategory() {
        return "Clothing - Hat";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getSize() + " | " + getColor()
                + " | " + style;
    }
}

abstract class Device extends Item {
    private String brand;
    private int warrantyMonths;

    public Device(String name, double price, int copies, String brand,
                  int warrantyMonths) {
        super(name, price, copies);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}

class Printer extends Device {
    private boolean isColor;

    public Printer(String name, double price, int copies, String brand,
                   int warrantyMonths, boolean isColor) {
        super(name, price, copies, brand, warrantyMonths);
        this.isColor = isColor;
    }

    public String getCategory() {
        return "Device - Printer";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getBrand() + " | Color: " + isColor;
    }
}

class Laptop extends Device {
    private int ramGB;

    public Laptop(String name, double price, int copies, String brand,
                  int warrantyMonths, int ramGB) {
        super(name, price, copies, brand, warrantyMonths);
        this.ramGB = ramGB;
    }

    public String getCategory() {
        return "Device - Laptop";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getBrand() + " | RAM: " + ramGB + "GB";
    }
}

class Projector extends Device {
    private int lumens;

    public Projector(String name, double price, int copies, String brand,
                     int warrantyMonths, int lumens) {
        super(name, price, copies, brand, warrantyMonths);
        this.lumens = lumens;
    }

    public String getCategory() {
        return "Device - Projector";
    }

    public String toString() {
        return getName() + " | " + getCategory() + " | $" + getPrice()
                + " | Stock: " + getAvailableCopies()
                + " | " + getBrand() + " | Lumens: " + lumens;
    }
}

class Cart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (Item item : items) {
            if (item.getAvailableCopies() > 0) {
                item.sell();
                System.out.println(item.getName() + " - $" + item.getPrice());
            } else {
                System.out.println(item.getName() + " is out of stock");
            }
        }

        System.out.println("Total: $" + calculateTotal());
        items.clear();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public List<Item> getItems() {
        return items;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<Item> inventory = new ArrayList<>();
        inventory.add(new Shirt("T-Shirt", 20, 5, "M", "Black", "Cotton"));
        inventory.add(new Socks("Socks", 10, 8, "L", "White", 3));
        inventory.add(new Hat("Hat", 15, 4, "One Size", "Blue", "Sport"));
        inventory.add(new Printer("Printer", 120, 3, "HP", 12, true));
        inventory.add(new Laptop("Laptop", 800, 2, "Dell", 24, 16));
        inventory.add(new Projector("Projector", 300, 2, "Epson", 18, 4000));

        Cart cart = new Cart();

        while (true) {
            System.out.println("\n1. View Store Inventory");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Return Item");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();

            if (choice == 1) {
                for (int i = 0; i < inventory.size(); i++) {
                    System.out.println(i + " - " + inventory.get(i));
                }
            } else if (choice == 2) {
                System.out.print("Enter item number: ");
                int index = input.nextInt();

                if (index >= 0 && index < inventory.size()) {
                    Item item = inventory.get(index);
                    if (item.getAvailableCopies() > 0) {
                        cart.addItem(item);
                        System.out.println("Added to cart");
                    } else {
                        System.out.println("Out of stock");
                    }
                } else {
                    System.out.println("Wrong number");
                }
            } else if (choice == 3) {
                cart.showCart();
            } else if (choice == 4) {
                cart.checkout();
            } else if (choice == 5) {
                System.out.print("Enter item number to return: ");
                int index = input.nextInt();

                if (index >= 0 && index < inventory.size()) {
                    inventory.get(index).returnItem();
                    System.out.println("Item returned");
                } else {
                    System.out.println("Wrong number");
                }
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Wrong choice");
            }
        }

        input.close();
    }
}

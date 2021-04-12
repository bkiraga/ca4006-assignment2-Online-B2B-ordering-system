public class Main {
    public static void main(String[] args) {
        DataStorage ds = new DataStorage();
        ds.addProduct("laptop", 28, 5, 2);
        ds.addProduct("tv", 22, 7, 3);
        ds.addProduct("ps4", 20, 3, 4);
        ds.addProduct("xbox", 18, 3, 4);
        ds.addProduct("pc", 14, 6, 2);
        System.out.println(ds.displayProduct("laptop"));
        System.out.println(ds.displayAllProducts());
    }
}
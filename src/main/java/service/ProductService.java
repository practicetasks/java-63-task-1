package service;

import dao.ProductDao;
import model.Product;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private final ProductDao dao;
    private final Scanner scanner;

    public ProductService(Scanner scanner) {
        this.dao = new ProductDao();
        this.scanner = scanner;
    }


    public void findById() {
        int id = getId();
        Product product = dao.findById(id);
        if (product == null) {
            System.out.printf("Товар с id=%d не найден\n", id);
            return;
        }
        System.out.println(product);
    }

    public void findAll() {
        List<Product> products = dao.findAll();
        System.out.println(products);
    }

    public void findSum() {
        Double sum = dao.findSum();
        System.out.println(sum);
    }

    public void findCount() {
        int count = dao.findCount();
        System.out.println(count);
    }

    public void findMostExpensiveProduct() {
        try {
            Product product = dao.findMostExpensiveProduct();
            System.out.println(product);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findTopN() {
        System.out.println("Введите число: ");
        int n = Integer.parseInt(scanner.nextLine());
        List<Product> products = dao.findTopN(n);

        showProducts(products);
    }

    public void makeDiscount() {
        int id = getId();

        System.out.println("Введите скидку в процентах");
        double percent = Double.parseDouble(scanner.nextLine()) / 100;

        dao.makeDiscount(id, percent);
    }

    public void findProductsInRange() {
        System.out.println("Введите начальный диапазон: ");
        int from = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите конечный диапазон: ");
        int to = Integer.parseInt(scanner.nextLine());

        List<Product> products = dao.findProductsInRange(from, to);
        showProducts(products);
    }

    private int getId() {
        System.out.println("Введите id товара: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void showProducts(List<Product> products) {
        products.forEach(System.out::println);
    }
}



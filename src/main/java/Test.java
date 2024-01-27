import dao.ProductDao;
import service.ProductService;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProductService service = new ProductService(scanner);

//        System.out.println("findAll");
//        service.findAll();
//
//        System.out.println("\nfindMostExpensiveProduct");
//        service.findMostExpensiveProduct();
//
//        System.out.println("\nfindSum");
//        service.findSum();
//
//        System.out.println("\nfindById");
//        service.findById();
//
//        System.out.println("\nfindTopN");
//        service.findTopN();

//        service.findProductsInRange();

        service.makeDiscount();

    }

    static String menu() {
        System.out.println("""
                1. Создать товар
                2. Вывести все товары
                3. Найти товар по id
                4. Найти товар по названию
                5. Удалить товар по id
                6. Обновить стоимость товара по id
                7. Обновить стоимость товара по названий""");
        return scanner.nextLine();
    }
}

package dao;

import model.Product;

import javax.persistence.*;
import java.util.List;

public class ProductDao {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = factory.createEntityManager();

    public void update(Product product) {
        try {
            manager.getTransaction().begin();

            manager.merge(product);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Product findById(int id) {
        return manager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return manager.createQuery("select p from Product p", Product.class).getResultList();
    }

    //  - Подсчитать общую стоимость всех товаров.
    public Double findSum() {
        return manager.createQuery("select sum(price) from Product p", Double.class).getSingleResult();
    }

    //  - Вывести общее количество товаров.
    public Integer findCount() {
        return manager.createQuery("select p from Product p").getResultList().size();
    }

    //  - Найти самый дорогой товар.
    public Product findMostExpensiveProduct() {
        return manager.createQuery("select p from Product p order by price desc", Product.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    //  - Показать топ N товаров с самой высокой стоимостью.
    public List<Product> findTopN(int n) {
        return manager.createQuery("select p from Product p order by price", Product.class)
                .setMaxResults(n)
                .getResultList();
    }

    //  - Предоставить скидку на товар по его id (в процентах).
    public void makeDiscount(int id, double percent) {
//        СПОСОБ 1
//        Product product = findById(id);
//        product.setPrice(product.getPrice() - product.getPrice() * percent);
//        update(product);


//        СПОСОБ 2
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("update Product set price = price - price * ?1 where id = ?2");
            query.setParameter(1, percent);
            query.setParameter(2, id);
            query.executeUpdate();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    //  - Найти товары, которые находятся в определенной ценовой категорий
    public List<Product> findProductsInRange(double from, double to) {
        TypedQuery<Product> query = manager
                .createQuery("select p from Product p where price between :from and :to", Product.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    public List<Product> findProductsLike(String prompt) {
        TypedQuery<Product> query = manager
                .createQuery("select p from Product p where name like ?1", Product.class);

        query.setParameter(1, "%" + prompt + "%");

        return query.getResultList();


    }
}

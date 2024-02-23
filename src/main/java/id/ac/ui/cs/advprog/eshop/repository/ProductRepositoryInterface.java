package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepositoryInterface {
    public Product create(Product product);

    public boolean delete(Product product);

    public Product edit(String index, Product productUpdated);

    public Iterator<Product> findAll();
}
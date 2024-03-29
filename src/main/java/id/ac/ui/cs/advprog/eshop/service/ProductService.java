package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;


public interface ProductService{
    public Product create(Product product);
    public List<Product> findAll();

    public Product edit(Product updatedProduct, String index);

    public boolean delete(Product product);
    public Product getProduct(String id);
    public Product edit(String index, Product product);
}

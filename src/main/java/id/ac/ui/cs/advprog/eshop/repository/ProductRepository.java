package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }
    public boolean delete( Product productToBeDeleted){
        return productData.remove(productToBeDeleted);

    }

    public Iterator <Product> findAll() {
        return productData.iterator();
    }
}

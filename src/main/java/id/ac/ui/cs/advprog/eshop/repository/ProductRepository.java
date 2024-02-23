package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements ProductRepositoryInterface{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        if(product.getProductId() == null){
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }

        productData.add(product);
        return product;
    }
    public boolean delete( Product productToBeDeleted){
        return productData.remove(productToBeDeleted);
    }

    public Product edit(String index, Product productUpdated){
        for (int i = 0; i < productData.size(); i++){
            Product currentProduct = productData.get(i);
            if (currentProduct.getProductId().equals(index)){
                productData.set(i, productUpdated);
                return productUpdated;
            }
        }
        return null;
    }

    public Iterator <Product> findAll() {
        return productData.iterator();
    }
}

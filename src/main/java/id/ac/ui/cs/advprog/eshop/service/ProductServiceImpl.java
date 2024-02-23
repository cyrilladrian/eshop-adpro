package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepositoryInterface productRepository;

    @Override
    public Product create(Product product){
        productRepository.create(product);
        return product;
    }

    @Override
    public Product edit(Product updatedProduct, String index){
        return productRepository.edit(index, updatedProduct);
    }

    @Override
    public boolean delete(Product product){return product != null && productRepository.delete(product);}

    @Override
    public Product getProduct(String productId){
        Iterator <Product> productList = productRepository.findAll();
        while(productList.hasNext()){
            Product currentProduct = productList.next();
            if (currentProduct.getProductId().equals(productId)){
                return currentProduct;
            }
        }
        return null;
    }

    @Override
    public Product edit(String index, Product product) {
        return null;
    }


    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
}

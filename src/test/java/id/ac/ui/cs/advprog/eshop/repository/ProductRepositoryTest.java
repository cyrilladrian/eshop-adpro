package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import static  org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator <Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-do821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Kecap cap cap");
        product1.setProductQuantity(1000);
        productRepository.create(product1);

        productRepository.delete(product1);
        Iterator<Product> IteratorProduct = productRepository.findAll();
        assertFalse(IteratorProduct.hasNext());
    }
    @Test
    void testEdit(){
        Product productBefore = new Product();
        productBefore.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productBefore.setProductName("Kecap cap cap");
        productBefore.setProductQuantity(100);
        productRepository.create(productBefore);

        Product productAfter = new Product();
        productAfter.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productAfter.setProductName("Teh Botol tol tol");
        productAfter.setProductQuantity(200);

        Iterator <Product> productIterator = productRepository.findAll();
        productRepository.edit(productBefore.getProductId(), productAfter);

//        Check Validity
        Product editedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(), productAfter.getProductId());
        assertEquals(editedProduct.getProductName(), productAfter.getProductName());
        assertEquals(editedProduct.getProductQuantity(), productAfter.getProductQuantity());
        assertEquals(editedProduct.getProductId(), productBefore.getProductId());
    }


}

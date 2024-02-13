package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;



    @BeforeEach
    void setUp(){
    }

    @Test
    void createProductTest(){
        Product product = Mockito.mock(Product.class);
        product.setProductQuantity(10);
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        verify(productRepository, times(1)).create(product);
        assertEquals(product, createdProduct);

    }

    @Test
    void findAllTest(){
        Product product = new Product();
        product.setProductName("Mantap Pancing");
        product.setProductId("89101112");
        product.setProductQuantity(2024);
        Product anotherProduct = new Product();
        anotherProduct.setProductId("123456");
        anotherProduct.setProductName("Hugh Digenmouth");
        anotherProduct.setProductQuantity(10);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(anotherProduct);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List <Product> productInList = productService.findAll();

        assertEquals(2, productInList.size());
        verify(productRepository, times(1)).findAll();
        assertEquals(productList, productInList);
    }

    @Test
    void deleteTest(){
        Product product = Mockito.mock(Product.class);
        when(productRepository.delete(product)).thenReturn(true);

        boolean returnValue = productService.delete(product);
        verify(productRepository).delete(product);
        assertTrue(returnValue);
    }

    @Test
    void editTest(){
        Product product = Mockito.mock(Product.class);
        Product anotherProduct = Mockito.mock(Product.class);

        when(productRepository.edit(product.getProductId(), anotherProduct)).thenReturn(anotherProduct);

        Product editedProduct = productService.edit(anotherProduct, product.getProductId());
        verify(productRepository).edit(product.getProductId(), anotherProduct);
        assertEquals(editedProduct, anotherProduct);
    }

    @Test
    void getProductTest(){
        Product product = new Product();
        product.setProductId("123456");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        Product retrievedProduct = productService.getProduct(product.getProductId());

        assertNotNull(retrievedProduct);
        assertEquals(product.getProductId(), retrievedProduct.getProductId());
    }


}

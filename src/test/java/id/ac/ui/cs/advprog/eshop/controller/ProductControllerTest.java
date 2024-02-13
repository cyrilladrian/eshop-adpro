package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @Mock
    Model model;


    @Test
    void testCreatePage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", isA(Product.class)));
    }

    @Test
    void testCreatePost() throws Exception {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        Product createdProduct = new Product();
        createdProduct.setProductId("generatedId");
        createdProduct.setProductName(product.getProductName());
        createdProduct.setProductQuantity(product.getProductQuantity());

        when(productService.create(any(Product.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/product/create")
                        .param("productName", product.getProductName())
                        .param("productQuantity", String.valueOf(product.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }


    @Test
    void testDelete() throws Exception {
        Product product = new Product();
        product.setProductId("ini id test");

        when(productService.getProduct("ini id test")).thenReturn(product);

        mockMvc.perform(get("/product/delete/{id}", "ini id test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
        verify(productService, times(1)).delete(product);
    }

    @Test
    void testProductList() throws Exception {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String returnedValue = productController.productListPage(model);

        assertEquals("productList", returnedValue);
        verify(model, times(1)).addAttribute("products", productList);

    }

    @Test
    void testEditPage() throws Exception {
        Product product = Mockito.mock(Product.class);
        product.setProductId("1");

        when(productService.getProduct("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", "1"))
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(status().isOk());

    }

    @Test
    void testEditProduct() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        mockMvc.perform(post("/product/edit/{id}", "1").flashAttr("product", product))
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).edit(product, "1");
    }

}

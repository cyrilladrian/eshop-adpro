package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void startAppSuccessfully() {
        try{
            EshopApplication.main(new String[] {});
        } catch (Exception e){
            fail("Error thrown while running application: " + e.getMessage());
        }
    }

}

package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }

    @Test
    void checkCreateProduct(ChromeDriver driver) throws Exception{
        String productName = "Teh Botol";
        int productQuantity = 1000;

        driver.get(baseUrl);

        //Fill all the input
        WebElement inputProductName = driver.findElement(By.id("nameInput"));
        inputProductName.clear();
        inputProductName.sendKeys(productName);

        WebElement inputProductQuantity = driver.findElement(By.id("quantityInput"));
        inputProductQuantity.clear();
        inputProductQuantity.sendKeys(String.valueOf(productQuantity));

        //Submit the input
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();

        //Wait until the list appears
        WebDriverWait waitResponse = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitResponse.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()=\"Product' List\"]")));

        //Verify the product details
        WebElement pageProductName = driver.findElement(By.xpath("//td[text()='"+ productName +"']"));
        WebElement pageProductQuantity = driver.findElement(By.xpath("//td[text()='"+ productQuantity +"']"));

        //Check the equality of the values
        assertEquals(productName, pageProductName.getText());
        assertEquals(String.valueOf(productQuantity), pageProductQuantity.getText());
    }

}

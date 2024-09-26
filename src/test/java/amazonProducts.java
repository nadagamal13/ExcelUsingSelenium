import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class amazonProducts {
        public static void main(String[] args) throws InterruptedException {
            WebDriver driver=new ChromeDriver();
            driver.get("https://www.amazon.eg/-/en/");
            driver.manage().window().maximize();
            WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
            search.sendKeys("Monitor");
            search.submit();
            Thread.sleep(3000);
            List<WebElement> productName=driver.findElements(By.xpath("//div[@data-cy=\"title-recipe\"]//h2//a//span"));
            List<WebElement> productPrice=driver.findElements(By.className("a-price-whole"));
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet1");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("AMZL Product Name");
            row.createCell(1).setCellValue("AMZL Product Price");
            for (int i = 0; i < 5; i++) {
                Row newrow = sheet.createRow(i + 1);
                newrow.createCell(0).setCellValue(productName.get(i).getText());
                newrow.createCell(1).setCellValue(productPrice.get(i).getText());
            }

            try (FileOutputStream fos = new FileOutputStream("Scope Assessment1.xlsx")) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }

}

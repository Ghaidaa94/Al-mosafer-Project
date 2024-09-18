package AlmosaferTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

public class TestCases {
    WebDriver driver = new ChromeDriver();
    String WebURL = "https://www.almosafer.com/en";
    Random random = new Random();
    int SwitchLanguage = random.nextInt(4); // Random Number between (0-4)
    int RandomRoom = random.nextInt(3);
    JavascriptExecutor Scroll = (JavascriptExecutor) driver;

    @Test(priority = 1,description = "Requirement 1 & 2")
    public void OpenWebsite() {
        System.out.println("Requirement 1 & 2 :");
        driver.navigate().to(WebURL);
        driver.findElement(By.cssSelector("[class=\"sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary\"]")).click(); // close the Popup dialogue
        WebElement LanguageButton = driver.findElement(By.cssSelector("[data-testid=\"Header__LanguageSwitch\"]"));

        String Language = LanguageButton.getText();
        Assert.assertEquals(Language, "العربية");
        System.out.println("Passed: Default Language is English");
        System.out.println();
    }





    @Test (priority = 2,description = "Requirement 3")
    public void CurrencyCheck (){
        System.out.println("Requirement 3 :");
        WebElement Currency = driver.findElement(By.cssSelector("[data-testid=\"Header__CurrencySelector\"]"));
        Assert.assertEquals(Currency.getText(), "SAR");
        System.out.println("passed: Default Currency is: " + Currency.getText());
        System.out.println();
    }




    @Test (priority = 3,description = "Requirement 4")
    public void CheckTheLogo () throws InterruptedException {
        System.out.println("Requirement 4 :");
        WebElement QitafLogo = driver.findElement(By.cssSelector("[data-testid=\"Footer__QitafLogo\"]"));
        Scroll.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);
        Assert.assertTrue(QitafLogo.isDisplayed());
        System.out.println("Passed: Qitaf Logo is Displayed in Footer ");
        System.out.println();
    }




    @Test (priority = 4, description = "Requirement 5")
    public void CheckForHotelTap (){
        System.out.println("Requirement 5 :");
        WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        Assert.assertFalse(HotelTap.isSelected());
        System.out.println("Passed: The Hotel Tap is NOT Selected");
        System.out.println();
    }




    @Test (priority = 5, description = "Requirement 6 & 7")
    public void CheckFlightDate(){
        System.out.println("Requirement 6 & 7 :");
        driver.navigate().to("https://www.almosafer.com/en?ncr=1");
        // Get the current date
        LocalDate CurrentDate = LocalDate.now();
        System.out.println("the Real Date is: " + CurrentDate);

        // Get the first flight date
        String  FirstFlight = driver.findElement(By.cssSelector("[class=\"sc-cPuPxo LiroG\"]")).getText();
        int firstflight = Integer.valueOf(FirstFlight);
        Assert.assertEquals(CurrentDate.plusDays(1).getDayOfMonth(), firstflight);
        System.out.println("the Departure Date is: " + FirstFlight + " =(" + CurrentDate + " + 1 Day), as Expected ");

        // Get the return flight date
        WebElement ReturnBox = driver.findElement(By.cssSelector("[data-testid=\"FlightSearchBox__ToDateButton\"]"));
        String ReturnFlight = ReturnBox.findElement(By.cssSelector("[class=\"sc-cPuPxo LiroG\"]")).getText();
        int returnflight = Integer.valueOf(ReturnFlight);
        Assert.assertEquals(CurrentDate.plusDays(2).getDayOfMonth(), returnflight);
        System.out.println("the Return Date is: " + ReturnFlight + " =(" + CurrentDate + " + 2 Days), as Expected ");
        System.out.println("Requirements passed");
        System.out.println();
    }




    @Test (priority = 6, description = "Requirement 8")
    public void AddingAssertion (){
//        driver.navigate().to(WebURL);
//        driver.findElement(By.cssSelector("[class=\"sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary\"]")).click();
String [] Element = {"//h4[normalize-space()='Car rental']",
                     "//h4[normalize-space()='Domestic packages']",
                     "//h4[normalize-space()='Cruise packages']",
                     "//h4[normalize-space()='Packages']",
                     "//h4[normalize-space()='Sports packages']"};
        System.out.println("Assertion to Check Some Elements");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        for (int X=0; Element.length > X; X++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            WebElement Action = driver.findElement(By.xpath(Element[X]));
            Assert.assertTrue(Action.isDisplayed());
            Action.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//            System.out.println(Action.getText()+ ": is Active");
        }
//         Assert for checking boxes in trip
        WebElement CheckBox = driver.findElement(By.cssSelector("[for=\"fare-calendar-checkbox\"]"));
        Assert.assertFalse(CheckBox.isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("[for=\"direct-flights-only-checkbox\"]")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("[for=\"has-baggage-only-checkbox\"]")).isSelected());
        System.out.println("All Check Boxes are unselected");
        System.out.println();
    }




    @Test (priority = 7, description = "Requirement 9 & 10")
    public void SelectRandomLanguage(){
        System.out.println("Requirement 9 & 10 :");
        WebElement LanguageButton = driver.findElement(By.cssSelector("[data-testid=\"Header__LanguageSwitch\"]"));

        if (SwitchLanguage % 2 == 0){
            System.out.println("number of random: " + SwitchLanguage); // Even number
            System.out.println("Your Selected Language is English");
            System.out.println("Would you like to change language into: (" +LanguageButton.getText() +")?" );
        } else {
            System.out.println("number of random: " + SwitchLanguage);
            LanguageButton.click();
            System.out.println("Your Selected Language is: (العربية)");
            System.out.println("Would you like to change language into: (" +driver.findElement(By.cssSelector("[data-testid=\"Header__LanguageSwitch\"]")).getText() +")?" );
        }
        System.out.println("Requirement passed");
        System.out.println();
    }



    @Test(priority = 8, description = "Requirement 11 ")
    public void HotelSearchTap (){
        System.out.println("Requirement 11 :");
        WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        HotelTap.click();
        WebElement CheckButton = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchBox__SearchButton\"]"));
        if (SwitchLanguage % 2 == 0){
            Assert.assertEquals(CheckButton.getText(), "Search hotels");
            System.out.println("Search hotels: is displayed");
        } else {
            Assert.assertEquals(CheckButton.getText(), "ابحث عن فنادق");
            System.out.println("ابحث عن فنادق : is displayed");
        }
        System.out.println("Requirement passed");
        System.out.println();
    }




    @Test (priority = 9, description = "Requirement 12 & 13")
    public void LocationTest () throws InterruptedException {
        System.out.println("Requirement 12 & 13:");
        WebElement HotelLocation = driver.findElement(By.cssSelector("[data-testid=\"AutoCompleteInput\"]"));
        if (SwitchLanguage % 2 == 0){// Even number--> English language--> Type (Dubai,Jeddah,Riyadh)
            HotelLocation.sendKeys("Dubai,Jeddah,Riyadh");
        } else {// Odd number--> Arabic language--> Type (دبي - جده  )
            HotelLocation.sendKeys("دبي - جده");
        }
        Thread.sleep(1500);
        WebElement FirstChoice = driver.findElement(By.cssSelector("[data-testid=\"AutoCompleteResultItem0\"]"));
        System.out.println("First Available Choice is: (" + FirstChoice.getText() + ")");
        FirstChoice.click();
        System.out.println("Requirement passed");
        System.out.println();
    }



    @Test (priority = 10, description = "Requirement 14")
    public void SelectRandomlyRoom (){
        System.out.println("Requirement 14:");
        WebElement Booking = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchBox__ReservationSelect_Select\"]"));
                Booking.click();
        if (RandomRoom % 2 == 0){// Even number--> Select (1R,2A,0C)
            WebElement FirstOption = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchBox__ReservationSelect_A\"]"));
            FirstOption.click();
            System.out.println("Passed: Random Booking is: (" + FirstOption.getText() + ")");
        } else {// Odd number--> Select (1R,1A,0C)
            WebElement SecondOption = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchBox__ReservationSelect_B\"]"));
            SecondOption.click();
            System.out.println("Passed: Random Booking is: (" + SecondOption.getText() + ")");
        }
        System.out.println();
    }


    @Test (priority = 11, description = " Requirement 15 & 16:")
    public void SearchForHotel (){
        System.out.println("Requirement 15 & 16:");
        driver.findElement(By.cssSelector("[data-testid=\"HotelSearchBox__SearchButton\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement CheckResultPage = driver.findElement(By.cssSelector("[data-testid=\"hotelSearch-filterWrapper\"]"));
        Assert.assertTrue(CheckResultPage.isDisplayed());
        System.out.println("Passed: Navigate to Search Result Page of Hotels Successfully");
        System.out.println();
    }



    @Test (priority = 12,description = "Requirement 17:")
    public void AssertionForResults(){
        System.out.println("Requirement 17:");
        String [] StarRating = {"[for=\"star-rating-0\"]","[for=\"star-rating-1\"]","[for=\"star-rating-2\"]","[for=\"star-rating-3\"]","[for=\"star-rating-4\"]"};
        int SLength = StarRating.length;
        for (int X=0; X<SLength; X++) {
               WebElement Check= driver.findElement(By.cssSelector(StarRating[X]));
               Assert.assertFalse(Check.isSelected());
            System.out.println(Check.getText() + ", is not selected");
        }
        System.out.println("Pass: Assert if any of star rating are selected");
        System.out.println();

    }



    @Test (priority = 18, description = "Requirement 18 & 19")
    public void ApplyLowerPrice () throws InterruptedException {
        System.out.println("Requirement 18 & 19:");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("[data-testid=\"HotelSearchResult__sort__LOWEST_PRICE\"]")).click();
        WebElement FirstOffer = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchResult__Hotel0__PriceLabel\"]")); // Scroll Down
        Thread.sleep(2000);
        Scroll.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement HighestOffer = driver.findElement(By.cssSelector("[data-testid=\"HotelSearchResult__Hotel39__PriceLabel\"]"));
        String LowestPrice = FirstOffer.getText().replaceAll("[^0-9]","");
        int Low = Integer.parseInt(LowestPrice);
        System.out.println("First Hotel prise = " + Low);
        String HighestPrice = HighestOffer.getText().replaceAll("[^0-9]","");
        int Hight = Integer.parseInt(HighestPrice);
        System.out.println("Last Hotel prise = " + Hight);
        if (Low  <  Hight){
            System.out.println("The System Sorted The Offers (Low-High Amount)");
        }else {
            System.out.println("Wrong Assertion");
        }
        System.out.println("Requirements passed");
        System.out.println();
    }
}

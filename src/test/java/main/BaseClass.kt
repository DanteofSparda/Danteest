package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import java.net.URL
import java.util.concurrent.TimeUnit

class BaseClass {
    lateinit var driver:AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver(){


        val url = URL( "http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()


        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android") //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11") // версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel API 30") // имя устройства
        caps.setCapability(MobileCapabilityType.NO_RESET,true) //не сбрасывать приложение в 0 при новом запуске
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"7200")
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.sportmaster.app.presentation.start.StartActivity")
        caps.setCapability(MobileCapabilityType.APP,"/Users/Dante/sportmaster-4.0.13.5605_dev_beta.apk")
        // caps.setCapability(MobileCapabilityType.UDID,"")

        driver = AndroidDriver(url,caps) // установка драйвера и приложения на Android device
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS)
    }

    @AfterSuite
    fun end(){
        println("тест окончен")
        driver.quit()
    }

    @Test
    fun testOne(){
        TimeUnit.SECONDS.sleep(1)
        try {
            var element: MobileElement = driver.findElement(MobileBy.xpath("1/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton")) //создаем объект //производим поиск элемента
            element.click() //клик по элементу
            println("клик прошел успешно")
        } catch (e:org.openqa.selenium.WebDriverException){
            println("элемент не найден, тест продолжается")
        }


        //ввод в поле номера телефона
        lateinit var element2: MobileElement
        element2=driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/editTextPhone"))
        element2.sendKeys("9999999901")

        //кнопка получить смс
        lateinit var element3: MobileElement
        element3=driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/buttonGetCode"))
        element3.click()

        //ввод в поле номера телефона
        lateinit var element4: MobileElement
        element4=driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/pinCodeEditText"))
        element4.sendKeys("1111")

        TimeUnit.SECONDS.sleep(5)
    }
}
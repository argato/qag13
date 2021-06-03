package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helper.AttachmentHelper.attachAsText;
import static helper.AttachmentHelper.attachPageSource;
import static helper.AttachmentHelper.attachScreenshot;
import static helper.AttachmentHelper.attachVideo;
import static helper.AttachmentHelper.getConsoleLogs;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

  static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class,
                                                          System.getProperties());

  @BeforeAll
  static void setup() {
    addListener("AllureSelenide", new AllureSelenide());
    if (System.getProperty("environment") == null) {
      System.setProperty("environment", "remote");
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    Configuration.browserCapabilities = capabilities;
    Configuration.browser = driverConfig.webDriverBrowser();
    Configuration.browserVersion = driverConfig.webDriveBrowserVersion();
    Configuration.startMaximized = driverConfig.webDriverMaximized();

    String remoteWebDriver = driverConfig.webDriverUrl();
    if (remoteWebDriver != null) {
      String user = driverConfig.remoteWebUser();
      String password = driverConfig.remoteWebPassword();
      Configuration.remote = String.format("https://%s:%s@%s/wd/hub", user, password,
                                           remoteWebDriver);
    }
  }

  @AfterEach
  void afterEach() {
    attachScreenshot("Last screenshot");
    attachPageSource();
    attachAsText("Browser console logs", getConsoleLogs());
    if (System.getProperty("video.storage") != null) {
      attachVideo();
    }
    closeWebDriver();
  }
}

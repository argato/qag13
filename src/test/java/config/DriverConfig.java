package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
    "classpath:config/${environment}.properties"})
public interface DriverConfig extends Config {

  @Key("webdriver.user")
  String remoteWebUser();

  @Key("webdriver.password")
  String remoteWebPassword();

  @Key("webdriver.browser")
  String webDriverBrowser();

  @Key("webdriver.browser.version")
  String webDriveBrowserVersion();

  @Key("webdriver.url")
  String webDriverUrl();

  @Key("webdriver.maximized")
  Boolean webDriverMaximized();

}

Feature: Cookies created by AcnCacheManager is displayed/triggered once the first category is disabled. Needs to block cookies
  created from AcnCacheManager.

  Background: As Content Owner , I want to apply the cookie blocking on the Identified Authentication cookies so that
  site will be GDPR compliant.

  @MainScenario
  Scenario Outline: Block cookies created from AcnCacheManager when first party analytics is disabled.

    Given I am at the Accenture page "<_url>"
    When I click cookie settings
    And I disable the first party analytics cookie category "<category>"
    And I click save settings
    When I login using careers credentials
      | louisedelosantos1234@gmail.com | Accenture02 |
    Then Cookies produced by AcnCacheManager is blocked "<cookies>"

    Examples:
      | _url                                      | cookies             |
      | https://www.accenture.com/at-de/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/bg-en/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/fr-fr/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/us-en/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/jp-ja/loginpage |_ss_uid;_ss_ui;_ss_cs|

  Scenario Outline: First Party analytics is enabled.

    Given I am at the Accenture page "<_url>"
    And I close the cookie banner
    When I login using careers credentials
      | louisedelosantos1234@gmail.com | Accenture02 |
    Then Cookies produced by AcnCacheManager is no longer blocked "<cookies>"

    Examples:
      | _url                                      | cookies             |
      | https://www.accenture.com/at-de/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/bg-en/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/fr-fr/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/us-en/loginpage |_ss_uid;_ss_ui;_ss_cs|
      | https://www.accenture.com/jp-ja/loginpage |_ss_uid;_ss_ui;_ss_cs|
Feature: DTM testing

  Background: As a user, I want to be able to automate my DTM testing.

  Scenario Outline:
    Given I'm using the environment
    # Options: production, stage
    |production|

    And I launch a browser and navigate to page "<page>" using geo "<geo>"
    # Options: chrome, firefox
    |firefox|

    And I accept cookies and refresh page if geo is eu
    When I open developer tools and check under network panel
    And I filter using id "<id>"

    # Choose between the 2, depending on what is being tested
    Then I should see an image request whose domain and parameter values should be "<domain>" "<params>"
    #Then I should not be able to trigger any image request

    # NOTE: params are comma-separated
    Examples:
    |page               |geo      |id            |domain              |params                      |
    |new-applied-now    |us-en    |8140983       |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref |
    |new-applied-now    |gb-en    |8148167       |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref |
    #|new-applied-now    |jp-ja    |915-KIK-177   |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref |
    #|new-applied-now    |jp-ja    |8148488   |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref|
    #|new-applied-now    |es-es    |8149715   |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref|
    #|new-applied-now    |gb-en    |8148167   |fls.doubleclick.net |ord, gtm, auiddc, u1, ~oref|


    #Possible enhancement
    #       a. Handling Positive and Negative testing
    #       b. Handling multiple tags
                    #Domain
                    #Parameter
    #       c. Multiple IDs - RMT9893
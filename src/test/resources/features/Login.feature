@a
Feature: Login
  As a admin I want to login to the web page


  @123
  Scenario: login successfully with hard username and password
    Given The coffee shop page is opening
    When I enter username and password
    Then I should see the logout button

  @456
  Scenario: login successfully without hard user and pass
    Given The coffee shop page is opening
    When I enter username 'Luke' and password 'Skywalker'
    Then I should see the greeting message 'Hello Luke'

    @789
  Scenario Outline: Greeting message displayed when login successfully without hard username and password
    Given The coffee shop page is opening
    When I enter username '<user>' and password '<pass>'
    Then I should see the greeting message '<message>'
    Examples:
      | user | pass      | message    |
      | Luke | Skywalker | Hello Luke |
      | Anh  | Dang      | Hello Anh  |


  Scenario Outline:: Error message displayed when user login failed
    Given The coffee shop page is opening
    When I enter username '<user>' and password '<pass>'
    Then I should see the greeting message '<message>'
    Examples:
      | user | pass |
      | Anh  | Dang |


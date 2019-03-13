Feature: Deploy the new build on TEST
  As a QA, I wanna build the new build easily

  Background:
    Given I go the Jenkins page
    And I login with username 'attdang' and password
    And I click 'log in' button

#############################Deploy to TEST###########################
Scenario Outline: Deploy to TEST env
When I click on the 'DeploytoENV' tab
And I open the link '<tabName>'
And I open the link 'Build with Parameters'
And I enter the version '4.51.0-5-SNAPSHOT'
And I click 'Build' button
Examples:
| tabName                                                   |
| mms_DBENV_settings__deeacdb03tomcat__DEPLOYTODATABASE     |
| mms_ServiceENV_settings__deeacdb03tomcat__DEPLOYTOSERVICE |
| mms_ShopENV_settings__deeweb03tomcat__DEPLOYTOSHOP        |

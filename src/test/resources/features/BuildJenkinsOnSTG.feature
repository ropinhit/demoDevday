Feature: Deploy the new build
  As a QA, I wanna build the new build easily

  Background:
    Given I go the Jenkins page
    And I login with username 'attdang' and password
    And I click 'log in' button
################################## DeploytoENV #########################################
  Scenario Outline: Deploy to Shop and Services
    When I click on the 'DeploytoENV' tab
    And I open the link '<tabName>'
    And I open the link 'Build with Parameters'
    And I enter the version '4.51.0-9-SNAPSHOT'
    And I click 'Build' button
    Examples:
      | tabName                                                   |
      | mms_ServiceENV_settings__deeacdb01tomcat__DEPLOYTOSERVICE |
      | mms_DBENV_settings__deeacdb01tomcat__DEPLOYTODATABASE     |
      | mms_ShopENV_settings__deeweb01tomcat__DEPLOYTOSHOP        |
      | mms_ShopENV_settings__deeweb02tomcat__DEPLOYTOSHOP        |

  Scenario: Deploy to BO
    When I click on the 'DeploytoENV' tab
    And I open the link 'mms_BOENV_settings__deeweb02tomcat__DEPLOYTOBACKOFFICE'
    And I open the link 'Build with Parameters'
    And I enter the version '4.51.0-10-SNAPSHOT'
    And I enter the versionBoFrontend '0.0.1-SNAPSHOT'
    And I click 'Build' button

################################## DeployCTL #########################################
#  Scenario: Deploy services
#    When I click on the 'DeployCTL' tab
#    And I open the link 'mms_env_CTL__master__PARAMETERIZEFLOW'

@Search
Feature: Search Items
  Description: The purpose of this feature is search items in amazon.com

  Scenario Outline: Search items
  Description:
    Given Application is launched
    When '<item>' is searched

    Examples:
      | item                    |
      |  Bluetooth headset |

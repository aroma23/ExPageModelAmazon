@Search
Feature: Search Items
  Description: The purpose of this feature is search items in amazon.com

  Scenario Outline: Search items
  Description:
    Given Application is launched
    When '<item>' is searched and sorted with '<option>'
    Then Search results should be displayed
    And Fetch urls into context for <count> individual items
    When Open each item and fetch seller, price and review details
    And Write gathered information for item: '<item>' into csv file


    Examples:
      | item                | option          | count |
      | Bluetooth headset   | price-desc-rank | 1     |
#      | 34 inch LED monitor | price-desc-rank | 2     |
#      | USB c dock          | price-asc-rank  | 2     |
#      | Smart Watch         | price-asc-rank  | 2     |

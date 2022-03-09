# Amazon POM

Objective: To write a sample framework for amazon page object model using, testNG, selenium, maven & cucumber

## Requirements
```commandline
Chromedrive
Makesure to match your chrome browser version
https://chromedriver.storage.googleapis.com/index.html?path=98.0.4758.102/
mac users only
xattr -d com.apple.quarantine /Users/muthu/Downloads/chromedriver

java version 11
maven version 3.5.4
```
## NOTE
```commandline
This software framework tested only in mac os so far
```

## Usage
```commandline
Set path.to.chromedriver property  in dev.properties point to path of your chromedriver
mvn clean test -Droot.log.level=info -Dcucumber.filter.tags=@Search
mvn clean test -Droot.log.level=info -Dcucumber.filter.tags=@Login
```

##cucumber-report
```commandline
Where to see reports: target/cucumber-reports/cucumber-html-reports/overview-features.html
Where to see test logs: logs/tests.log
Where to see csv files: results/*.csv
```

###Standards
```html
Sonar Lint plugin in IDE
```


####  Author
```commandline
Muthukumar Ramaiyah
linkedin: https://www.linkedin.com/in/muthukumar-ramaiyah-785673a0/ 
```

[comment]: <> (Selenium related)
[comment]: <> (https://www.toolsqa.com/selenium-cucumber-framework/cucumber-automation-framework/)
[comment]: <> (https://www.softwaretestinghelp.com/selenium-webdriver-cucumber-selenium-tutorial-31/)

[comment]: <> (POM related)
[comment]: <> (https://www.toolsqa.com/selenium-cucumber-framework/page-object-design-pattern-with-selenium-pagefactory-in-cucumber/)
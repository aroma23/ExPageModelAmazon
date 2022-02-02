# da-components-qa

Objective: Provide simple, elegant, efficient and powerful tests/framework to validate the analytics components such as 
pipeline, config, telemetry, maintenance and etc

## Requirements
```commandline
java version 11
maven version 3.5.4
```

## Usage
```commandline
mvn clean test -Dcucumber.filter.tags=@FAGetVimal

-Dtest.env=dev (optional its default in pom.xml)
mvn clean test -Dcucumber.filter.tags=@OpticalMapping -Dtest.env=uat

override properties during runtime over CLI
mvn clean test -Dcucumber.filter.tags=@OpticalMapping -Dom.user=<<user>> -Dom.pwd=<<pwd>>
```

##Optional params
```commandline
-DthreadCount=10
```

##cucumber-report
```commandline
Where to see reports: target/cucumber-reports/cucumber-html-reports/overview-features.html
Where to see test logs: logs/tests.log
Where to see feign logs: logs/feign.log
```

##Jenkin-Jobs
https://jenkins-qa.snp.comcast.com/jenkins/view/DA/job/DA/

###Standards
```html
https://etwiki.sys.comcast.net/pages/viewpage.action?spaceKey=DHP&title=Coding+Standards
Sonar Lint plugin in IDE
```

#####Conventions:
```html
https://etwiki.sys.comcast.net/display/NEST/SNP+MW+-+QA+framework
```

##Optical Mappings tests - How to run:
####Preparing OM Stage properties
```commandline
Get Optical mapping latest code and run it using stage profile under master branch
https://github.comcast.com/NEST/OpticalMapping-Backend2
```
```commandline
make sure stage.properties file has below 3 properties updated
aws.marketsQueue=optical-mapping-backend-markets-stage-auto
device.portal.api.url=http://netiq-device-portal-rest-api-qa.nest.r53.xcal.tv:8443
s3.data.routerstats.path=rs_athena_output_qa
```
####Running OM Application
```commandline
Spin OM application using below command
./gradlew bootRun -Dprofile=stage
```
####Injecting required router pairs to device portal QA instance
```commandline
Run the following test. This tests make sure to rewrite qa device portal table to have limited data set (router pairs). The table will be overwritten after device refresh everyday
mvn clean test -Dcucumber.filter.tags=@OMCSVUpload -P omlocal
```
####Running OM E2E test cases
```commandline
Run OMAlgo scenarios
mvn clean test -P omlocal -Dcucumber.filter.tags=@OMAlgo
```








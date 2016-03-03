## Synopsis

Functional Tests used to test the hwf-survey product.

## How to Invoke

First build with Maven, then invoke.
$ mvn clean package
$ java -jar target/hwf-survey-functional-tests.jar

## Configuration

If you wish to change the default configuration, add an application.yml on your current directory

To change the web site location, add the following items:
survey:
    site:
        url: http://www.example.com/

To change the browser type, add:
browser:
    vendor: htmlunit (or chrome, or firefox, or safari)


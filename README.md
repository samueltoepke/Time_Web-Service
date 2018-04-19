# Time_Web-Service
Microservices based web service that returns the current time in JSON format, pursuant to timezone input from the user.

This project is a microservices-based web service that returns the current time, implemented in [Java](https://java.com/en/). [Spring Boot](https://projects.spring.io/spring-boot/) is used to create an embedded microservices environment, and [Gradle](https://gradle.org/) is used as the build tool. This code can be run locally, or deployed to a cloud service such as [Amazon Web Services](https://aws.amazon.com/) (AWS). Time is returned in JSON format, and returns an informative error in case of incorrect timezone input.
 
The project requires Java 1.8 and Gradle 4.6 to be installed, and available from the command line. To verify, run the following commands; they should both respond with the current version.
* `$ java -version` 
* `$ gradle -version`

## To view available Gradle tasks for the Spring Boot application:
* `$ gradle tasks`

## To run the standalone Spring Boot application in Gradle:
* `$ gradle bootRun`

## To generate the full JAR file, and run manually:
* `$ gradle build`
* `$ java -jar ./build/libs/sprint-boot-time-web-service.jar`

## Once the application is running, use a web browser to view the following URLs:
* Current time for GMT: [http://localhost:8080/gettime](http://localhost:8080/gettime)
* Current time for specified time zone: [http://localhost:8080/gettime?tz=EST](http://localhost:8080/gettime?tz=EST). Legal timezone inputs conform to current recognized [Java timezone IDs](https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/). 
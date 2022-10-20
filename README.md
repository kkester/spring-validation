# spring-validation

### Overview

This PoC demonstrates the following architecture, design, and coding strategies:

1. Spring validation on POJOs provided as parameters to a Spring Service Bean
2. Standard Bean property validation
3. Custom Bean validation
4. Spring Boot Unit Test with Exception assertions
5. Leveraging Lombok
6. Using `reckon` for version management

### Building the App
Below is an example command that builds the application and creates the next semantic version
```
./gradlew build -Preckon.scope=major -Preckon.stage=final reckonTagCreate
```

Upon completion, run `git tag -l` and the tag created from the build should be displayed.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


# spring-boot-warmup
Small onboarding spring boot application

# Useful Readings before starting
- Effective Java (2nd Edition) 2nd Edition
- Beginning Spring 1st Edition
- Spring Recipes: A Problem-Solution Approach 3rd ed. Edition

# Technologies

Middleware:  Java8 / SpringBoot
Database: MongoDB
Communication Protocols: AMQP / HTTP

Before venturing into this project itself it is important to have at least some hands on experience with the aforementioned technologies. As such following, we describe the steps to build some exercise applications in order for one to be familiarized with those technologies.

# 1 - Setup your development environment

- Install Java JDK8 in your machine. Download it from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html Or install it from Linux repositories (oracle-java8-installer).
- Setup Maven. Maven is used to build and compile Jumia Services applications. The recommended version to use with our applications is 3.0.5. Download and install it followinf the instructions on https://maven.apache.org/
- In Jumia Services the official IDE is IntelliJ Idea, as such you should get a free working community edition copy on https://www.jetbrains.com/idea/download/#section=linux
- Setup intelliJ with Jumia Services code style formater. Wrongly formatted source files with not pass code reviews. Learn about the formatter here: Java Code Style
- Install MongoDB version 3.2. You can download it from https://www.mongodb.com/download-center#previous
- Download and install the latest RabbitMQ. Follow the instructions here https://www.rabbitmq.com/download.html

# 2 - Create your first SpringBoot application:

- Create a simple SpringBoot project named JSUserRegistrationService that upon initialization will print the following message to the console: "Jumia Services is Awesome". You can get some documentation in Spring official website: https://projects.spring.io/spring-boot/

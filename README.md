# This is Quiz Application where a user can give a quiz and in that quiz that user will answer some question and as per that result will come and report will come .

So here 4 services are present . 



Here we are going to use same table for all the servicess in different projects 

Here we are going to use feignclient to communicate one service to another ,like when we call the quiz service at that time if we need question response
 as well at that time we need to communicate these 2 servicess we will do that by the help of feignclient thats a Spring Cloud Service
 
For that we need to add this dependency from spring initializer (Note -> Read the below notes to add the dependency)

OpenFeign (SPRING CLOUD ROUTING)
Declarative REST Client. OpenFeign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.

# Step 1
For that 1st we need to add this dependency 

 <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring-cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  
And then we need to add <spring-cloud.version>2023.0.1</spring-cloud.version> this in the properties block like the below
 <properties>
    <java.version>17</java.version>
    <spring-cloud.version>2023.0.1</spring-cloud.version>
  </properties>
  
Then we will add the feign client 

<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

# Step 2
We will add one new interface to communicate to the other service here we will add that in the QuizService to communicate with the QuestionService

# Step 3
We need to add @EnableFeignClients in the main class to enable the Feign Clients 

# This is Quiz Application where a user can give a quiz and in that quiz that user will answer some question and as per that result will come and report will come .

So here 4 services are present .



Here we are going to use same table for all the servicess in different projects

Here we are going to use feignclient to communicate one service to another ,like when we call the quiz service at that time if we need question response
as well at that time we need to communicate these 2 servicess we will do that by the help of feignclient thats a Spring Cloud Service

For that we need to add this dependency from spring initializer (Note -> Read the below notes to add the dependency)

OpenFeign (SPRING CLOUD ROUTING)
Declarative REST Client. OpenFeign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.

# Step 1
# Here we will see how to work with feign client (A spring cloud service)

This will help us to communicate with he other service or one application to another application

By the help of this if a application is working in 8080 port and another one is work with 8081 then they can communicate with each other by the help of this

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

# Step 4

# Now we will learn service registry or make this application as a server (How to work with eureka server)

which we call discovory server , where all our micro servicess will be registered , and where instance will be menaged 	, load balancing will be menaged

This service registry will be work like a server where our all servicess will work like a client

So for making service registry we will use eureka server and for the client like quiz service and question service we will use eureka client which are given by NetFlix

This all things are a separate project so will create a diff project from the spring initializer

Where we will two dependencies one is Eureka Client and web

Eureka Server SPRING CLOUD DISCOVERY (This is the dependency)
spring-cloud-netflix Eureka Server.

We need to make this service as a server not as a client but all other services are work like a client so that this service registry will collect the data and send
those to the client

So for that we need to do some configuration in application.properties file to desable for act as a client

Need to add @EnableEurekaServer this annpotation in the main class of this service project This will help to make this as a eureka server

Now if we run the project then we will see something like this

Replica node URL:  http://localhost:8761/eureka/

we make this project port number as 8761

# Step 5

# Here we will see how to make a serice application as a client so that , that will connect with a client or a service registry (How to work with eureka client)

We need to register other servicess to the service registry we need to make them as a client bcz we already have a server which we made on the step 4 (Servive Registry)

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
  <spring-cloud.version>2023.0.1</spring-cloud.version>  
  (We need to define this in the properties like this 
  <properties>
		<java.version>17</java.version>
		<spring-cloud.version>2023.0.1</spring-cloud.version>
	</properties>) 

	If the above things are already there in you project then we can directly add the below things to make that service app as a client 
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
 </dependency>

Need to add this dependency to make the other service as eureka client

We need to define the server url in the application.properties file of the client servicess project like this ,

eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/  (This we generate on the step 4)

Then if we start that client service application then we will see something like this

Started QuizServiceApplication in 11.445 seconds

To Check we can run the URL in any browser (http://localhost:8761(This is our server port number which is in service registry project step 4))

On that page we will see something like this (Instances currently registered with Eureka)

Here we will see all the registered eureka clients


Instances currently registered with Eureka
Application	   AMIs	Availability Zones	Status
QUESTIONSERVICE	n/a     (1)	     (1)	UP (1) - host.docker.internal:QuestionService:8082
QUIZSERVICE	    n/a     (1)	     (1)	UP (1) - host.docker.internal:QuizService:8081

We will see something like this (host.docker.internal:QuestionService:8082) This is a link if we click o this then , it will redirect to a page like

this http://host.docker.internal:8082/actuator/info but here we need to remove and make that to http://host.docker.internal:8082/question as per our controller

then we will get the data as per our controller .


# Step 6

Here we will see how to menage instances of a service here we will see how to menage the instncess in QuizService

For this we will create a zar file with the port 8081 or anything and run that project by terminal and again we will run this service in 8084 port  or anything

so that we can run the same project in multiple instancess and those we can see on the eureka servver on this url http://localhost:8761


# Step 7

# Now we will see load balancing

This is usefull when we have multiple instances of a same service application if one application is going down at that time we can use another instances of thats
same application

when we used loadbalancing means the time of multiple instances available or if the multiple ports are available  at that time load balancing is helpfull

Spmething like as per our project if we have a condition like where in our quiz service class  getQuestionOfQuiz is a method where we are getting all the question
of the quiz there quiz is depend on the question , at that time if we have a single instance of service application and if this will go down then that will be
problemetic for us so we wil create multiple instancess (Step 6) if one instance will go down or if that will not work fine then another instncesswill wotk at
that time .
So For that we need to load balancing our code fo that we will add one dependencies on our code

 <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>

	And need to add this 
	@FeignClient(name = "QuestionService")
    public interface QuestionClient {
	
	So that this QuestionService this service will work in any port that server will use active ports and work with them 


# Step 8

# Now we will see how to work with API Gatway

 <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

This will helpfull in a situation like we have different servicess those are working in 8080 , 8081 , 8082 , 8083 port like this if we have a project
and in that project if we have 50 servicess application then we need to create 50 port address which is not good so in that case we use  API Gateway .

To create API Gateway we will also create another micro servicess service application  . which will active in a different port number like 9090 . A client will
send the request and 1st that request will come to API Gateway then as per the request that will send to the another service application like quiz service or
question service

we will use this dependency (Gateway) in this new survice application

Gateway SPRING CLOUD ROUTING
Provides a simple, yet effective way to route to APIs in Servlet-based applications. Provides cross-cutting concerns to those APIs such as security,
monitoring/metrics, and resiliency.

then we will add eureka client dependency to make this as a client

Thenn we will add actuator dependencies

Spring Boot Actuator OPS
Supports built in (or custom) endpoints that let you monitor and manage your application - such as application health, metrics, sessions, etc.


To make this as a Api Gateway we need to do some configuration in the application.properties file so that we can directly send the request to the exact service
controller path or the exact port or the exact instance


# Run the project

TO run the project 1st we will start our service registry and then all the service aplication which we have then we will start the Api Gateway service

Then
previously we have http://localhost:8081/quiz     this url to send the data and get the data
http://localhost:8082/question this url to send the data and get the data

	now we have this single port to communicate with both the service application by the help of API GATEWAY    
	
	http://localhost:8083/quiz  AND http://localhost:8083/question  (All port are same for any of the service application)
 


# Msisdn-Categorizer-Backend
This service categorizes and validates country phone numbers.

# RUNNING STEPS FOR THE SERVICE
Note: Java must be installed on your machine to run this project. Preferrably Java 11

## USING IDE
Note: You must have installed a java based IDE preferrably IntelliJ
1. Clone the project from github onto your machine
2. Create a new project from existing sources and select the cloned project and let the IDE setup all the required dependencies
3. Click Run button on the top Right place holder of the IDE
4. The service will launch on port 8080 so ensure the port is not being used by another service though you can change this in the properties file.
5. You can use the collection shared below to test the endpoints.

## USING DOCKER IMAGE
Note: You must have docker installed
### BUILDING THE IMAGE
1. Change directory into the project folder
2. Ensure the dependencies are well setup by running `mvn clean install`
3. Package the project by running `mvn package`
4. Build the image by running `docker build -t ms-msisdn-categorizer-service:v1.0.0 .`
5. If you run `docker images` you should see `ms-msisdn-categorizer-service` in the list

### RUNNING THE IMAGE
1. Run the image `docker run --rm -p 8080:8080 ms-msisdn-categorizer-service:v1.0.0`
2. This will launch the image on port 8080

## COLLECTION FOR TESTING
Note: Install postman on your machine
1. Import the collection below and test
https://www.getpostman.com/collections/49ba51fbbe44f0c4a474

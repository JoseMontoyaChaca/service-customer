FROM openjdk:8
ADD /target/service-customer-0.0.1-SNAPSHOT.jar service-customer.jar 
EXPOSE 9192
CMD ["java","-jar","service-customer.jar"]  

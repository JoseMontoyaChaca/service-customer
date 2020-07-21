FROM openjdk:8
ADD /target/service-customer.jar service-customer.jar
EXPOSE 8085
CMD ["java","-jar","service-customer.jar"]
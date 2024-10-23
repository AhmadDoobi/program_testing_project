FROM openjdk:21
EXPOSE 8088
ADD target/bookstore.jar bookstore.jar
ENTRYPOINT ["java","-jar","/bookstore.jar"]

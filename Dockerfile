FROM openjdk:8
ADD target/article-application.jar article.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "article.jar"]
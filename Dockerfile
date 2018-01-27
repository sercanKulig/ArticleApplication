FROM openjdk:8
ADD target/article-application.jar article.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "article.jar"]

#docker -v
#docker build -f Dockerfile -t article-application
#docker run -p 9090:9090 article-application
#docker run article-application
#docker stop article-application
#docker logs article-application
#docker rmi article-application(delete image)
#docker image rm article-application
#docker container rm *container id*
#docker volume rm *volume id*
#docker images
#docker container ls
#docker pull mysql:5.6 (for mysql download)
#docker run --name container_name_we_choose -e MYSQL_ROOT_PASSWORD=passowrd -e MYSQL_DATABASE=database -e MYSQL_USER=user_name -e MYSQL_PASSWORD=password -d mysql:5.6
#docker run -p 9090:9090 --name article-application --ling mysql-standalone(container_name_we_choose):mysql -d database(database_name_we_choose)

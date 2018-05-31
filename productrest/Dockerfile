# Author Yash
FROM anapsix/alpine-java

EXPOSE 8080
RUN mkdir -p /app/restapi
COPY /target/productrest-0.0.1-SNAPSHOT.jar /app/restapi/

CMD java -jar /app/restapi/productrest-0.0.1-SNAPSHOT.jar
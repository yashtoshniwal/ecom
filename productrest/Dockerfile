# Author Yash
FROM anapsix/alpine-java

EXPOSE 8080
RUN mkdir -p /app/restapi
COPY /target/productrest.jar /app/restapi/

CMD java -jar /app/restapi/productrest.jar
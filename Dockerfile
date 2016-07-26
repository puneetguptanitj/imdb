FROM anapsix/alpine-java:8
COPY imdb.jar /tmp/imdb.jar

ENTRYPOINT ["java", "-jar" , "/tmp/imdb.jar"]

FROM java:8
WORKDIR /
ADD target/principal-2.0.2.RELEASE.jar principal-2.0.2.RELEASE.jar
EXPOSE 8080
CMD java - jar principal-2.0.2.RELEASE.jar
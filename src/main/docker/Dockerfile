FROM eclipse-temurin:17-jre-alpine

ENV TZ                Europe/Zurich
ENV LANG              de_CH.UTF-8
ENV LC_ALL            de_CH.UTF-8
ENV JAVA_TOOL_OPTIONS -XX:MaxRAMPercentage=50
ENV APP_SAMPLE_CONFIG ValueFromDockerfile

# Define user/group (optional)
USER daemon:daemon

# Copy application and define command
WORKDIR /usr/local/application/
EXPOSE 8080
COPY maven .
CMD ["java", "-jar", "sample-springboot-0.1.0-SNAPSHOT.jar"]

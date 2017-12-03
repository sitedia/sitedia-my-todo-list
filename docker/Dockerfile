FROM ibmjava:8-jre
VOLUME /tmp
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xmx256m -Xms128m -Xss128m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /sitedia-my-todo-list.jar" ]
EXPOSE 8080
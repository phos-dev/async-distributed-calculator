FROM openjdk

WORKDIR /app

COPY src /app/src

COPY client /app/client

RUN mkdir -p /app/bin

RUN javac -cp "/app/client/*" -d /app/bin /app/src/Client.java

WORKDIR /app/bin

CMD ["java", "-cp", ".:/app/client/*", "Client"]

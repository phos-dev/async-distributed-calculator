FROM openjdk

WORKDIR /app

COPY src /app/src
COPY server /app/server

RUN mkdir -p /app/bin

RUN javac -cp "/app/server/*" -d /app/bin /app/src/Calculator.java /app/src/CalculatorHandler.java

WORKDIR /app/bin

CMD ["java", "-cp", ".:/app/server/*", "CalculatorHandler"]

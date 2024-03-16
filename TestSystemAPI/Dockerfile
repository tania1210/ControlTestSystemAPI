# Використовуємо базовий образ з Java та Maven
FROM maven:3.8.4-jdk-17 AS builder

# Встановлюємо робочий каталог в контейнері
WORKDIR /app

# Копіюємо файли з ресурсів проекту в робочий каталог контейнера
COPY . .

# Виконуємо збірку проекту з Maven
RUN mvn clean package

# Використовуємо інший базовий образ з Java та Spring Boot
FROM adoptopenjdk/openjdk11:alpine-jre

# Встановлюємо робочий каталог в контейнері
WORKDIR /app

# Копіюємо зі збудованого образу Maven зібрані файли JAR у контейнер
COPY --from=builder /app/target/*.jar app.jar

# Виконуємо встановлення Spring Boot додатку
ENTRYPOINT ["java", "-jar", "app.jar"]

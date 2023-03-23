# Сервис для хранения данных о спортивных командах

## О проекте

Это RESTful сервис для хранения и управления данными о спортивных командах,
участниках команд, видов спорта и ролей участников команд.

### Используемые технологии

Проект написан с использованием фреймворка [Spring Boot](https://spring.io/projects/spring-boot).
Используемая СУБД - [PostgreSQL](https://www.postgresql.org/).
Для взаимодействия сервиса с БД использовалась -
[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/),
а для миграций БД -[Liquibase](https://www.liquibase.org/).

### ERD диаграмма базы данных

![Изображение](https://user-images.githubusercontent.com/100407472/227121182-ef3d35e6-ab7e-4601-92c3-fab9cb7c7d87.png)

## Как запустить

Необходимо установить [Docker](https://docs.docker.com/engine/install/), чтобы запустить сервис и
БД изолированно от операционной системы.
После выполнения инструкций одного из двух любых ниже представленных способов можно будет перейти по
[ссылке](http://localhost:8080/swagger-ui/index.html#/), чтобы посмотреть и протестировать API.

### С использованием терминала на UNIX системах.

Чтобы сделать исполняемые файлы, нужно установить
[Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

```
git clone https://github.com/kosterror/sport-team-api.git
cd sport-team-api
mvn install
docker-compose up --build (возможно понадобятся права супер-пользователя)
```

### С использованием IntelliJ IDEA на любой OC

1. Необходимо установить [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/download/).
2. Склонировать и разархивировать [репозиторий](https://github.com/kosterror/sport-team-api).
3. Открыть разархивированный проект с помощью [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/download/).
4. Сгенерировать исполняемые файлы с помощью функционала
   [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/download/).
   1. Найти и нажать на вкладку `Maven` Она обычно находится в правой части интерфейса.
   2. Развернуть выпадающий список `Lifecycle`.
   3. Найти и запустить сценарий `install`.
5. В корне проекта открыть терминал/командную строку и выполнить команду `docker-compose up --build`.


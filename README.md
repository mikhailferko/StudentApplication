# Student Application

Для запуска проекта скачать и установить [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

Для хранения данных будем использовать БД PostgreSQL, поднятую в контейнере Docker. 

Скачать [Docker](https://www.docker.com/get-started)

После того, как скачали Docker, выкачиваем образ БД:

    docker pull postgres:12-alpine

Запускаем контейнер:

    docker run -d -p 5432:5432 --name db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=demo postgres:12-alpine

Перед запуском приложения скомпилируем код, для создания реализаций мапперов: 

    mvn clean compile

Запускаем приложение.Чтобы запустить проект пропишите в консоли команду:

    mvn spring-boot:run

Чтобы остановить выполнение программы нажмите

    ctrl + c

Также запустить проект можно с помощью команды:

    mvn spring-boot:start

При таком запуске, остановить с помощью команды:

    mvn spring-boot:stop

Запустить выполнение интеграционных тестов можно
с помощью команды:

    mvn verify

Воспользуемся Postman для заполнения БД и проверки работы приложения.

Для работы со студентами url:

    http://localhost:8080/student

Для работы с предметами:

    http://localhost:8080/subject

Для работы с отчетами (оценками):

    http://localhost:8080/mark

Для фильтрации оценок по студенту добавляем в конце /Фамилия_студента

Добавляем запросом POST студентов:

    {
        "surname": "Иванов"
    }

Предметы:

    {
        "name": "География"
    }

Оценки:

    {
        "student": 1,
        "subject": 1,
        "mark": 4
    }

Обратите внимание, что по логике программы Фамилии повторяться могут, предметы - нет (выбросится исключение).

Наполняем БД и проверяем работу программы методами GET, POST, PUT, DELETE.
# 1221_Test

Необходимо создать REST API сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд.

Требования:

1. Пользователи. Добавление пользователей с параметрами:

- ID

- Имя

- Email

- Возраст

- Вес

- Рост

- Цель (Похудение, Поддержание, Набор массы)

 

На основе данных автоматически рассчитать дневную норму калорий (можно использовать формулу Харриса-Бенедикта).

2. Блюда. Добавление блюд с параметрами:

- ID

- Название

- Количество калорий на порцию

- Белки/Жиры/Углеводы

3. Прием пищи. Пользователь может добавлять прием пищи со списком блюд 

4. Отчеты (эндпоинты, без формирования документа):

- отчет за день с суммой всех калорий и приемов пищи;

- проверка, уложился ли пользователь в свою дневную норму калорий;

- история питания по дням.

 

Нефункциональные требования:

- использовать Spring Boot + Spring Data JPA;

- база данных PostgreSQL;

- валидация входных данных (например, проверка веса и роста на адекватность);

- написать юнит-тесты для основной логики;

- реализовать обработку ошибок (например, если пользователь не найден).

 

Формат сдачи:

- ссылка ра GitHub репозиторий 

- README с описанием проекта и инструкцией запуска.

- Postman-коллекция для тестирования API (опционально)

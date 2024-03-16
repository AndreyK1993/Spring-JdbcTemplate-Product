
CRUD операції
----------------
ТЕХСТЕК

Spring Framework
https://spring.io/

Spring Data JDBC
https://spring.io/projects/spring-data-jdbc#overview
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/package-summary.html

Class JdbcTemplate
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
JDBC Template — основний API, через який отримаємо доступ
до більшості функціональних можливостей:
- Створення та закриття з'єднань.
- Виконання операторів і викликів процедур збереження.
- Ітерація по ResultSet і повернення результатів.

MySQL 8
https://www.mysql.com/

--------------------------------------------------

1) Складаємо SQL-запити (INFO/SQLs.sql).

2) Створюємо Maven-проект.

3) Підтягуємо залежності (pom.xml).

4) Формуємо пакети, класи в app.

5) Формуємо файл-properties
src/main/resources/app.properties
Для файлу(ів) *.properties може бути запропонований
плагін Solon (https://plugins.jetbrains.com/plugin/21380-solon).
Можемо встановити.

6) Перевіряємо роботу програми.

--------------------------------------------------

Spring @Component
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html

@Component вказує, що анотований клас є "компонентом".
Такі класи розглядаються як кандидати на автоматичне
виявлення під час використання конфігурації з урахуванням
анотацій та сканування шляхів до класів.

Spring сканує додаток на наявність класів, анотованих
за допомогою @Component.
Створює їх екземпляри та впроваджує в них будь-які зазначені
залежності.
Вставляє їх туди, де це потрібно.


@Component vs @Bean.
@Bean також є анотацією, яку Spring використовує
для збору bean-компонентів під час виконання, але вона не
використовується лише на рівні класу.
Натомість ми анотуємо методи за допомогою @Bean, щоб
Spring міг зберегти результат методу у вигляді
bean-компоненту Spring.

--------------------------------------------------

Spring @Autowired
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html

Автозв'язування/автопідключення - автоматичне впровадження
залежностей у bean-компоненти без їх налаштування в
класі конфігурації.
При автозв'язуванні контейнер Spring динамічно виявляє та
надає залежні значення властивостям компонента без їхнього
явного налаштування.

Spring @Autowired використовується для автоматичного
впровадження bean-компонента.


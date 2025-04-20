# Проект по автоматизации тестирования API для [ReqRes](https://reqres.in)

> ReqRes — это бесплатный сайт, предоставляющий демонстрационное API для тестирования HTTP-запросов.

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

____
<a id="tools"></a>
## Технологии и инструменты

<p align="center">  
<a href="https://www.java.com/"><img src="assets/logo/java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://junit.org/junit5/"><img src="assets/logo/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://rest-assured.io/"><img src="assets/logo/restassured.png" width="50" height="50"  alt="Rest-assured"/></a>  
<a href="https://maven.apache.org/"><img src="assets/logo/maven.svg" width="100" height="50"  alt="Maven"/></a>  
<a href="https://github.com/allure-framework/allure2"><img src="assets/logo/allure.svg" width="50" height="50"  alt="Allure"/></a>    
<a href="https://www.jenkins.io/"><img src="assets/logo/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
</p>

* API автотесты написаны на **Java**.
* Сборщик **Maven**.
* Тестовые фреймворки **JUnit 5** и **REST-assured**.
* Для удаленного запуска реализована задача в **Jenkins** с генерацией отчета **Allure**.

____
<a id="cases"></a>
## Примеры автоматизированных тест-кейсов:
____
- ✓ *Создание пользователя*
- ✓ *Обновление данных пользователя*
- ✓ *Получение данных пользователя*
- ✓ *Получение списка пользователей*
- ✓ *Удаление пользователя*
- ✓ *Вход пользователем*
____
<a id="jenkins"></a>
## Сборка Jenkins:
<p align="center">  
<a href=""><img src="assets/screen/jenkins.png" alt="Jenkins" width="950"/></a>  
</p>
<a id="console"></a>

____
<a id="console"></a>
## Команды для запуска
___
***Локальный запуск:***

```bash  
mvn clean test
```
___
<a id="allure"></a>
## Allure

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="assets/screen/allure_main.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="assets/screen/allure_test.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="assets/screen/allure_graphs.png" width="850">

</p>

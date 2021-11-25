# currencyService
Простой сервис, который сравнивает нынешний курс какой-либо валюты со вчерашней
# Установка
Для установки данного сервиса нам потребуется [gradle](https://gradle.org)(Version 7.x).    Клонируем репозиторий, в удобном для вас месте
```
git clone https://github.com/hondajke/currencyService.git
```
Через командную строку(cmd) переходим в папку репозитория и запускаем сервис через команду
```
gradle bootrun
```
Если ПО благопалучно запустилось, в любой удобный для вас браузер вбиваем
```
http://localhost:8081/index
```
# API list
Данный сервис работает со следующими API:    
REST API курсов валют - https://docs.openexchangerates.org/    
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

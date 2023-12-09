# Collect_Invest_User_service

Путь: POST /signUp

Описание: Запрос на создание нового пользователя

Тело запроса: Принимает объект User в формате JSON:
```json
{
"name": "string",
"email": "string",
"password": "string"
}
```
Успешный ответ: Возвращает объект Message в формате JSON с кодом ответа 200(OK)
```json
{
"id": "integer",
"name": "string"
}
```
Неуспешный ответ: Если email или пароль некорректны, возвращает объект Message в формате JSON с кодом ошибки 400(Bad Request)
```json
{
    "message": "Incorrect email" ИЛИ "Incorrect password"
}
```
____________
Путь: GET /logIn/{email}/{password}

Описание: Запрос доступа к существующему аккаунту

Параметры пути: 
```
{email} - email пользователя 
{password} - пароль пользователя
```

Успешный ответ: Возвращает объект AuthenticatedUser в формате JSON с кодом ответа 200(OK)
```json
{
    "id": "integer",
    "name": "string"
}
```
Неуспешный ответ: Если пароль некорректен или аккаунт не существует, возвращает объект Message в формате JSON с кодом ошибки 401(Unauthorized)
```json
{
  "message": "Incorrect email" ИЛИ "Incorrect password"
}
```

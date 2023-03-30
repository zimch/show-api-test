# SHOP API TEST

## Микросервисы

Все работает на ```Eureka Server```

Порт: ```8088```

Порт микросервиса генирируется динамически.

## Преднастройка:

Пользователи:пароли: ```admin:admin``` и ```user:user```

Используйте популяцию БД:

```SQL
INSERT INTO public.users(
    id, balance, email, login, password, role, status)
VALUES (1, 0, 'user@user.ru', 'user', '$2a$12$uyJkIT17tTbuX9fqw5YmM.i5oN.1Iiz8AQIOdAeO8yOjbyFTQp7Ze
', 'USER', 'ACTIVE');

INSERT INTO public.users(
    id, balance, email, login, password, role, status)
VALUES (2, 1000000, 'user@user.ru', 'admin', '$2a$12$ExwWbc0VK7.vUsDN8/PGJuEAlOEoyn0P4tAeBef78QKTQq6C2i56m
', 'ADMIN', 'ACTIVE');
```


- USER API:

  - ``` POST : /user/organizations/save ``` -- создать оргиназацию

  ```json
  {
      "name": "org1",
      "description": "org1 description"
  }
  ```
  
  - ``` GET : /user/organizations ``` -- получить информация об организации авторизированного пользователя
  
  ```json
  {
    "id": 1,
    "name": "org1",
    "description": "org1 description",
    "logo": null,
    "status": null,
    "user": {
        "id": 1,
        "login": "user",
        "email": "user@user.ru",
        "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
        "balance": 9930
    },
    "products": []
  }
  ```
  
  - ``` POST : /user/buy/product/{org_id}/{prod_id} ``` -- купить товар(prod_id) в организации(org_id)
  
  ```json
  {
    "id": 2,
    "name": null,
    "description": "product2",
    "price": 14,
    "quantity": 423
  }
  ```
  
  - ``` GET : /user/purchases ``` -- получить список покупок текущего пользователя
  
  ```json
  [
    {
        "id": 5,
        "user": {
            "id": 1,
            "login": "user",
            "email": "user@user.ru",
            "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
            "balance": 9916
        },
        "product": {
            "id": 2,
            "name": null,
            "description": "product2",
            "price": 14,
            "quantity": 423
        },
        "price": 14,
        "date": "2023-03-28"
    }
  ]
  ```
  
- ADMIN API:

  - ``` GET : /admin/users ``` -- получить список пользователей
  ```json
  [
    {
        "id": 2,
        "login": "admin",
        "email": "user@user.ru",
        "password": "$2a$12$FlihFYV8UY0LNHtgGV1z8OuopoQnA5fMgQPPpac4cq.5QjVtavUum",
        "balance": 1000000
    },
    {
        "id": 1,
        "login": "user",
        "email": "user@user.ru",
        "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
        "balance": 9916
    }
  ]
  ```
  
  - ``` GET : /admin/users/{id} ``` -- получить информцию о пользователе(id)
  ```json
  {
    "id": 1,
    "login": "user",
    "email": "user@user.ru",
    "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
    "balance": 9916
  }
  ```
  
  - ``` PUT : /admin/users/balance/{id} ``` -- обновить баланс пользователя (например, поменять баланс). ```Пример для (/admin/users/update/1)```
  
  POST:
  ```json
  {
    "balance": 100000
  }
  ```
  
  RETURN:
  ```JSON
  {
    "id": 1,
    "login": "user",
    "email": "user@user.ru",
    "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
    "balance": 100000
  }
  ```
  
  - ``` PUT : /admin/edit/organization/{org_id} ``` -- изменить информацию об организации(org_id) ```Пример для /admin/edit/organization/1```
  
  PUT:
  ```json
  {
    "description": "new org 2"
  }
  ```
  
  RETURN:
  ```json
  {
    "id": 1,
    "name": "org1",
    "description": "new org 2",
    "logo": null,
    "status": null,
    "user": {
        "id": 1,
        "login": "user",
        "email": "user@user.ru",
        "password": "$2a$12$oe6/9quSPDtL7W9.w1YMKOZBRCrgjpnT4fthm5XNZZ55IWWwBHXNS",
        "balance": 100000
    },
    "products": [
        {
            "id": 1,
            "name": null,
            "description": "product1",
            "price": 10,
            "quantity": 1000
        },
        {
            "id": 2,
            "name": null,
            "description": "product2",
            "price": 14,
            "quantity": 423
        }
    ]
  }
  ```
  
  - ``` GET : admin/user/{id}/purchases ``` -- получит покупки пользователя(id)
  ```json
  [
    {
        "id": 5,
        "price": 14,
        "date": "2023-03-28"
    },
    {
        "id": 4,
        "price": 14,
        "date": "2023-03-27"
    },
    {
        "id": 6,
        "price": 14,
        "date": "2023-03-28"
    },
    {
        "id": 9,
        "price": 14,
        "date": "2023-03-28"
    },
    {
        "id": 8,
        "price": 14,
        "date": "2023-03-28"
    },
    {
        "id": 3,
        "price": 14,
        "date": "2023-03-27"
    },
    {
        "id": 7,
        "price": 14,
        "date": "2023-03-28"
    },
    {
        "id": 10,
        "price": 14,
        "date": "2023-03-28"
    }
  ]
  ```

#language: ru

Функциональность: Проверка функционала сервиса погоды

  @all
  @valid
  Структура сценария: Валидный тест. Проверка ответа по городу "<Город>"

    * Запрос погоды по городу "<Город>". Проверка соответствия "<country>" и "<timezoneid>"

    Примеры:
      | Город    | country                  | timezoneid       |
      | Москва   | Россия                   | Europe/Moscow    |
      | Paris    | France                   | Europe/Paris     |
      | New York | United States of America | America/New_York |


  @all
  @invalid
  Структура сценария: Невалидный тест. Проверка получения кода "<Код>"

    * Проверка получения ошибки с кодом "<Код>" и сообщением "<Текст ошибки>"

    Примеры:
      | Код | Текст ошибки                                                                                                              |
      | 101 | You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com]                                   |
      | 105 | Access Restricted - Your current Subscription Plan does not support this API Function.                                    |
      | 603 | Your current subscription plan does not support historical weather data. Please upgrade your account to use this feature. |
## АОП (quiz)

1. Конкретный момент в процессе работы приложения, такой как выполнение метода или обработка исключений, называется ...
- `join point`
- `target`
- `pointcut`
- `aspect`

2. Логика, выполняемая аспектом в конкретной точке соединения, называется...
- `join point`
- `advice`
- `pointcut`
- `introduction`

3. Выражение, соответствующее точкам соединения, называется...
- `weaving`
- `advice`
- `pointcut`
- `aspect`

4. Модуль, содержащий функциональность, связанную со многими другими частями приложения, но не касающийся его основной задачей, называется...
- `introduction`
- `advice`
- `pointcut`
- `aspect`

5. Какую точку соединения описывает следующее выражение: `execution(* service.UserService.*(..))`
- Подходит всем методам класса **UserService** c одним или более параметром.
- Подходит всем методам класса находящимся в пакте **service**.
- Подходит всем публичным методам класса **UserService** с любым набором аргументов. 
- Подходит всем методам класса **UserService** с любым набором параметров. +

6. Выберите все доступные указатели pointcut.
- target +
- around -
- execute -
- within +
- initialization +

7. Выберите все корректные Pointcut выражения.
- execution(* mate.academy.ProductService.*(String)) +
- pointcut(* ProductService.*(java.lang.String)) -
- execution(public void * (?)) -
- execution(*) -
- within(service) +
- execution(* String get*) || execution(* Long get*) +


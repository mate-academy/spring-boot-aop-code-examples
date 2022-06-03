## AOP (quiz)

1. A specific moment in the application such as method execution or exception handling has the name ...
- `target`
- `join point`
- `pointcut`
- `aspect`

2. A logic executed by an aspect at a particular join point has the name ...
- `join point`
- `advice`
- `pointcut`
- `introduction`

3. A predicate that matches join points has the name ...
- `weaving`
- `advice`
- `pointcut`
- `aspect`

5. A module containing the logic linked to many other parts of the program, but which is not related to the program's primary function has the name ...
- `introduction`
- `advice`
- `pointcut`
- `aspect`

6. What kind of join points describe the next pointcut expression: `execution(* service.UserService.*(..))`
- Match all methods defined in class **UserService** with one or more arguments.
- Match all methods defined in classes inside package **service**
- Match public methods defined in class **UserService** with any number arguments.
- Match all methods defined in class **UserService** with any number arguments. +

7. Choose all valid pointcut designators:
- target +
- around -
- execute -
- within +
- initialization +

8. Choose all correct Pointcut expression.
- execution(* mate.academy.ProductService.*(String)) +
- pointcut(* ProductService.*(java.lang.String)) -
- execution(public void * (?)) -
- execution(*) - 
- within(service) + 
- execution(* String get*) || execution(* Long get*) +

## AOP (quiz)

1. A moment during the execution of a program, such as the execution of a method or the handling of an exception is called ...
- `join point`
- `advice`
- `pointcut`
- `aspect`

2. A logic executed by an aspect at a particular join point is called ...
- `join point`
- `advice`
- `pointcut`
- `aspect`

3. A predicate that matches join points is called ...
- `join point`
- `advice`
- `pointcut`
- `aspect`

5. A module containing the logic linked to many other parts of the program, but which is not related to the program's primary function is called ...
- `join point`
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

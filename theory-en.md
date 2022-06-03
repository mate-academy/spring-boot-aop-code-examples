# Aspect Oriented Programming (AOP)

There was a period when programs were written in a procedural style, which had its advantages, 
such as the speed with which code could be produced and the ability to call any process an endless number of times from anywhere in the program.
However, with the growth of projects and the need for long-term support and expansion, this technique proved to be completely worthless.
Then OOP came to the rescue with its modularity and human-friendly abstractions, which not only corrected the PP's mentioned flaws, but also served as a catalyst for the industry's overall growth.
The easier the code is to work with, the less time programmers have to learn and develop, and the more money the company makes.
As a result, readability and clarity are the most important criteria for determining the quality of your code.
To perform all the same tasks, Robert Martin later established 5 design principles for a transparent and flexible application architecture, 
the first letters of which are brilliantly fashioned into the English word **solid**.
According to his first principle states that any unit of code (method, class, package, module) should be responsible for only one task at one level of abstraction.
Everything appears to be in clear, but what about the code that must run near the main one? For instance, how can the method's working time be calculated without breaking the SRP in either the method or the class where it is called?
This question will be answered by **AOP**.

> **AOP** - is a programming paradigm based on the idea of separating the main and service functionality.
Service functionality refers to code that is not related to important business tasks.

##### An example of a business issue
- Reading data from a file;

##### An example of a service issue:
- Checking user permissions;
- Logging service information;
- Exception handling;

To solve the task in an aspect-oriented style, we need to relocate the cross-cutting logic outside the method.
In this tutorial, we'll look at some examples of how this can be done using the **Spring Framework** and the **AspectJ** extension.

## Logging using AOP.

To start using the magic of aspect oriented programming with **spring boot**, the first thing we need to do is add 2 dependencies to the `pom.xml` file. </br>
**spring-aspects** - for connecting the spring module, as well as the **aspectj**, since the spring uses its methods under the hood.

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>1.9.9.1</version>
        </dependency>

After connecting the dependencies, we have access to all the necessary annotations and can begin creating the first aspect class.
Let's make a new package and class with a name that fits the situation. We use the annotations '@Aspect' and '@Component' to make the class an aspect.

And the first subject we need to learn about is **Advice**.

> **Advice** - is a method inside an aspect class that contains cross-cutting logic,
such as: logging, profiling, access control, transaction processing, exception handling, caching, etc.

There are several types of such methods:
- **Before** (`@Before`)
- **After** / **After finally** (`@After`)
- **Around** (`@Around`)
- **After returning** (`@AfterReturning`)
- **After throwing** (`@AfterThrowing`)

For example, let's create a method that logs a `findAll` method calling on an object of type `CategoryService`.

    @Log4j2 
    @Aspect
    @Component
    public class LoggingAspect {
        @Before("execution(* mate.academy.springboot.aop.service.CategoryService.findAll())")
        public void findAllCategoryAdvice() {
            log.info("Method findAll was called.");
        }
    }

As you can see, we used the `@Before` annotation with a pattern specifying the signature of the method 
to set our **advice** so that it is run before executing the 'categoryService.findAll' method.
Such expressions are called **pointcuts**. To specify a specific unit, we need to write the full path to its location.
In **spring** we use **AspectJ Pointcut expression language** to write **pointcuts**.
#### Here is the structure:

    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

- **modifiers-pattern** - (optional) Method visibility (public, protected, private, *)
- **ret-type-pattern** - Return type of the method
- **declaring-type-pattern** - (optional) Package or class (ex: com.app.service.* - applies to all classes in this package, com.app.service.UserService - applies only to UserService class, * - all)
- **name-pattern** - method name (ex: set* - all setters)
- **param-pattern** - method parameters (.. - any number of parameters, java.lang.String - method taking String as parameter.
- **throws-pattern** - (optional) Method throwing this exception.

> **Join points** - are the points of program execution at which
additional code needs to be added. For example: calling a method or accessing a class field.

To access the method signature, arguments, and other information about `join points`,
you can use the `JoinPoint` object, which will be passed to the **advice** parameters.

    @Before("execution(public * mate.academy.springboot.aop.service.ProductService.save("
            + "mate.academy.springboot.aop.model.Product))")
    public void saveProductAdvice(JoinPoint joinPoint) {
      Product product = (Product) joinPoint.getArgs()[0];
      log.info("Method ProductService.save was called. Args: {"
                + "Id = " + product.getId()
                + "; Name = " + product.getName()
                + "; Price = " + product.getPrice()
                + ";");
    }

In this example, our **advice** has the following configurations:
- This advice must be processed before the method named `save`, which is in the `ProductService` interface;
- Access modifier must be `public`;
- The method can return any data type;
- And accept only one argument of type `Product`. 

Also, thanks to the `JoinPoint` object, we have upgraded our logging and now, in addition to the message, we also display information about the saved product.

> **Pointcuts** can be written separately from **advice** and reused any number of times. You can do this with an annotation
`@Pointcut` which is placed above the method and takes **Pointcut expression** as an argument. An example syntax can be found in the following example.
> You can also combine them with && || and !.

## Profiling with AOP

Let's look at another example in which we will measure the execution speed of each method of the `TimeWasteService` class using the object
`ProceedingJoinPoint` as well as the standard `StopWatch` spring class.

    @Log4j2
    @Aspect
    @Component
    public class ProfilingAspect {
        @Pointcut("within(mate.academy.springboot.aop.service.TimeWasteService)")
        public void allMethodsFromTimeWasteService() {
        }

        @Around("allMethodsFromTimeWasteService()")
        public Object profileTimeWasteService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
            return result;
        }
    }

The 'within' designator came in helpful here, which determines within what type the given **advice** will work.

> In addition to the main `execution` and `within`, AspectJ supports other pointcut designators for more specific cases, such as:
> - `this` and `target` - execution of the connection point corresponding to the object of the specified type;
> - `args` - execution of the connection point where an argument of the specified type is available;
> - `call` - calling a method or a set of methods that match the condition;
> - `initialization` - class initialization;

Overall, we learned how to define the simple aspect and got familiar with the basic terms of the principle, 
such as: **Aspect**, **Join point**, **Advice**, **Pointcut**.

### Advantages
By separating the main logic of a method, the purpose of which is described in its name,
from secondary service tasks, we simultaneously achieve:
- Compliance with the Single Responsibility Principle
- Compliance with the principle Don't repeat yourself.
- Ease of reading and understanding the main logic of the project.
- Simplification of unit testing.

### Disadvantages
The disadvantages of AOP include 2 things:
- Slight performance hit due to bean proxying.
  It is also worth saying that if you do everything correctly and do not overload the code with countless aspects,
  in most cases, the advantages of this approach are much more significant.
- High threshold of entry. Difficulties in understanding the concept, due to its non-obviousness.
  As well as the risk of writing even more confusing code abusing the use of aspects.

### AOP in spring
Spring supports AOP by default and uses this approach under the hood, for example, in spring security.
With the help of spring, you can solve the basic, most popular tasks in this style, while AspectJ
provides a wider functionality that solves almost all possible tasks.
For AOP to work in pure spring, you need to add the `@EnableAspectJAutoProxy` annotation in the config class.
There is no such need in spring boot.

#### Useful links

- [Pointcut examples](https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/)

- [Spring AOP proxy under hood.](https://www.google.com/amp/s/habr.com/ru/amp/post/597797/)

- [Spring guide. AOP in Spring Framework.](https://proselyte.net/tutorials/spring-tutorial-full-version/aop-with-spring/)
- 
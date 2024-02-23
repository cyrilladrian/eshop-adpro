

# Reflection 1
I believe, what I am most struggling with is the MVC concept of the SpringBoot Framework. In the Django, MVT concepts enables the developer to have clear seperation of concerns. Hence, each component of the framework have different 'jobs' which are routing, database modelling, templates, views, etc. As oppose to the MVC of the Java SpringBoot which have unclear roles between them and it makes me struggling to debug. 

Here are several clean code principles that are implemented in this project: 
1. I try to make my code self explanatory with the clarity given in my variable name 
2. I try to use comments only when needed, only in the case of coding logic that are needed clarity in the future. 


# Reflection 2
## First Question
I felt relieve and also excited, I am intrigued that all the testing I had done manually in the PBP can be done automatically. A 100 percent code-coverage does not mean that the source code is bug and error free. It is essential to also consider another testing techniques. Also, it is important to update the testing units. 

## Second Question 
I believe that it would be redundant since both of functional testing have the same setup and instance variable, it is more align with the clean code principle to have the same Java class and just added different methods. 

# Reflection 3 
[Link to Deployment](https://eshop-universityofindonesia.koyeb.app/)
## First Question
This is kind of a typo, the Sonar cloud catch this following inconsistency of data. I tried to `assertNotEquals` a productId and productQuantity

`assertNotEquals(currentProduct.getProductId(),
nonExistProduct.getProductQuantity());`
## Second Question

Yes it has implemented the Continuous Integration and Continuous Deployment. Everytime I Merge & pull request to the main branch there are tools such as JUnit, OSSF, Scorecard, Sonarcloud to check the code. Furthermore, I use automatic deployment utilizing Koyeb using a Dockerfile

# Reflection 4
## SOLID principle implementation log 
1. Single Responsibility Principle

Seperate the Product and Car Controller into different file. 
2. Open-closed Principle 

Set random UUID for the Product creation in the product repository and only set the random UUID if the Product ID is null. This can mean that in the future, if other developer want to set the Product ID by other string, it can be done without edit the code.
3. Liskov Subtitution Principle
The Car Controller does not have the same characteristic as the Product Controller. If we implement the Product Controller using the car controller, it will result in different thing.

4. Interface Segregation Principle
Does not have any problems

5. Depedency Inversions Principle
Segregate the Repositories into java class and their respective Interface.
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
## First Question
This is kind of a typo, the Sonar cloud catch this following inconsistency of data. I tried to `assertNotEquals` a productId and productQuantity

`assertNotEquals(currentProduct.getProductId(),
nonExistProduct.getProductQuantity());`
## Second Question

Yes it has implemented the Continuous Integration and Continuous Deployment. Everytime I Merge & pull request to the main branch there are tools such as JUnit, OSSF, Scorecard, Sonarcloud to check the code. Furthermore, I use automatic deployment utilizing Koyeb using a Dockerfile
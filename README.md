# RecipesManager

#**Objective**

Create a standalone java application which allows users to manage their favourite recipes. It should allow adding,
updating, removing and fetching recipes. Additionally users should be able to filter available recipes based on one or
more of the following criteria:

1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.
   For example, the API should be able to handle the following search requests:
   • All vegetarian recipes
   • Recipes that can serve 4 persons and have “potatoes” as an ingredient
   • Recipes without “salmon” as an ingredient that has “oven” in the instructions.

**Requirements**

Please ensure that we have some documentation about the architectural choices and also how to run the application. The
project is expected to be delivered as a GitHub (or any other public git hosting) repository URL.
All these requirements needs to be satisfied:

1. It must be a REST application implemented using Java (use a framework of your choice)
2. Your code should be production-ready.
3. REST API must be documented
4. Data must be persisted in a database
5. Unit tests must be present
6. Integration tests must be present

## **Local Set-Up**

### Pre-requisites:

1. Java 17 or above installed
2. Maven 3.8.1 or above installed
3. Docker and docker-compose installed (optional)

#### Using Docker

1. Clone the repository
2. Open a terminal/cli and navigate to the root of the project
3. Ensure port 8080 and 5432 are available
4. Run 'sh build.sh' to build the project and create the docker image
5. The application should be accessible at http://localhost:8080/swagger-ui.html

#### Without Docker

1. Clone the repository
2. Open a terminal/cli and navigate to the root of the project
3. Ensure port 8080 and 5432 are available
4. Run 'mvn clean install' to build the project
5. Run postgresql locally on port 5432
6. Run 'mvn spring-boot:run -Dspring.profile.active=local' to start the application
7. The application should be accessible at http://localhost:8080/swagger-ui.html

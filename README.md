# Inventory Service
This project is a RESTful service designed to manage a player's inventory. It provides endpoints to create, update, and retrieve inventory items and user details.

## Service Logic and Features
### Main Features
- **User Management**: Allows the creation and retrieval of user details.
- **Inventory Management**: Enables adding, updating, and retrieving inventory items.

### How It Works
- **InventoryItemController**: Manages inventory items. You can add new items, update existing items, and retrieve item details.
- **UserController**: Handles HTTP requests related to user operations. Supports creating a new user and fetching user details.
- **UserService**: Implements the business logic for user operations.
- **Repositories**: Utilize Spring Data JPA to interact with the MySQL database for both users and inventory items.

## Running the Service
The service is initiated by the InventoryServiceApplication class

## Dependencies
The Inventory Service relies on:
- **MySQL** Database: For storing inventory and user data.
- **Maven**: For dependency management and project building.
- **Spring Boot**: Core framework for building and running the application.
- **Spring Data JPA**: Simplifies database interactions and ORM.

## Prerequisites
Before you begin, ensure you have the following requirements:
- Java 17
- Maven 3.6+
- MySQL 8.0+
- Postman (for testing the endpoints)

## Setup
1. **Download and Extract the ZIP File**:
Download the provided ZIP file and extract its contents.

2. **Navigate to the Project Directory**:
cd path/to/extracted/folder/inventory-service

3. **Update the database configuration** in `src/main/resources/application.properties` if necessary.

4. **Build the project**:
    ```sh
    mvn clean install
    ```

5. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```
The service will start on the default port `8080` and will be ready to handle requests.

## Using the JAR File
1. **Build the project**:
    ```sh
    mvn clean install
    ```

2. **Run the JAR file**:
    ```sh
    java -jar target/inventory-service-0.0.1-SNAPSHOT.jar
    ```

The service will start on the default port `8080` and will be ready to handle requests.


## Configuration
The application is configured via the `application.properties` file. Below are the key configuration properties:
```properties
spring.application.name=inventory-service
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=P@ssw0rd1
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.error.include-message=always
 ```



## Before Testing (Insert test data)
In order to set up the service and run tests, it is recommended to insert some test data into the MySQL database. These SQL statements will create two users (user1 and user2) and assign some inventory items to them. This setup ensures you have data to work with when you start testing the service endpoints.

### First, add two users:
 ```SQL
-- Insert users
INSERT INTO inventory_db.user (username) VALUES ('user1');
INSERT INTO inventory_db.user (username) VALUES ('user2');
 ```

### Next, insert inventory data for these users:
 ```SQL
-- Insert inventory for user1
INSERT INTO inventory_db.inventory_item (user_id, item_name, quantity) VALUES (1, 'item1', 10);
INSERT INTO inventory_db.inventory_item (user_id, item_name, quantity) VALUES (1, 'item2', 5);

-- Insert inventory for user2
INSERT INTO inventory_db.inventory_item (user_id, item_name, quantity) VALUES (2, 'item3', 7);
INSERT INTO inventory_db.inventory_item (user_id, item_name, quantity) VALUES (2, 'item4', 3);
 ```

## Testing Scenarios (Using Postman)
To facilitate testing, you can import the provided Postman collection `inventory-service-test.postman_collection.json` located in the root directory of this project. Below are the key scenarios covered:

- **Adding a single item to a user's inventory:** Ensure that an item is correctly added to the user's inventory.
- **Adding multiple items to a user's inventory:** Verify that multiple items can be added in a single request.
- **Fetching a user's inventory:** Confirm that the user's inventory is correctly fetched.
- **Subtracting from a user's inventory:** Ensure that the specified quantity of an item is subtracted from the user's inventory.
- **A user can have 0 of an item:** Verify that an item can have a quantity of zero.
- **A user must only be able to read/edit their own inventory:** Ensure that a user cannot edit another user's inventory.
- **A user's inventory must recognise that new item types could be added later on:** Confirm that new item types can be added to the inventory.

## Future Enhancements and Vision
Here are additional features and steps that I believe should be incorporated into a microservice of this nature, given more time:

### Improved Scalability
- **Microservices Architecture:** Reorganise the application into a suite of microservices to manage different tasks like user management, inventory management, and authentication separately. This will make the system more scalable and allow each service to be developed, deployed, and scaled independently.
- **Load Balancing:** Use load balancing to distribute incoming requests evenly across multiple service instances, ensuring high availability and reliability.
### Advanced Monitoring and Logging
- **Centralised Logging:** Implement centralised logging with the ELK Stack (Elasticsearch, Logstash, and Kibana) to collect logs from all microservices, making it easier to monitor and troubleshoot the system.
- **Performance Monitoring:** Use tools like Prometheus and Grafana for real-time performance monitoring and alerts, ensuring the system runs smoothly under different loads.
### Enhanced Security
- **JWT Authentication:** Add JSON Web Token (JWT) authentication to secure endpoints, making sure only authenticated users can access or modify their inventory. This improves data security and follows industry standards.
- **Role-Based Access Control:** Introduce role-based access control (RBAC) to manage permissions, ensuring only authorised users can perform certain actions, like admin tasks.
### Robust Data Handling
- **Event-Driven Architecture:** Implement an event-driven architecture using message brokers like RabbitMQ or Kafka to handle asynchronous tasks such as inventory updates and notifications. This will improve the system's responsiveness and scalability.
- **Database Optimisation:** Optimise database queries and add indexing strategies to make data retrieval and manipulation faster.
### Enhanced User Experience
- **API Rate Limiting:** Implement API rate limiting to prevent abuse and ensure fair usage among users.
- **Detailed API Documentation:** Create detailed and interactive API documentation using tools like Swagger/OpenAPI, making it easier for developers to understand and use the endpoints.
- **Detailed API Documentation:** Create detailed and interactive API documentation using tools like Swagger/OpenAPI, making it easier for developers to understand and use the endpoints.
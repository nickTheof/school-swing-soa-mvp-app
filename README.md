# **School Management App**

## **Description**

The **School Management App** is designed to manage key resources within a school, including **teachers, students, and courses**. The current implementation focuses on **Teacher and Student Management**.

This project follows **Service-Oriented Architecture (SOA)** principles, adheres to **Clean Architecture**, and implements the **Model-View-Presenter (MVP)** pattern. The user interface is built using **Java Swing** for a rich graphical experience.

---

## **Features**

✅ **Manage Teachers** – Add, update, and remove teacher records.  
✅ **Manage Students** – Maintain student information and perform CRUD operations.  
✅ **Clean Architecture** – Ensures maintainability and scalability.  
✅ **Java Swing GUI** – Provides an interactive and user-friendly interface.  
✅ **MVP Pattern** – Separates concerns for better testability and modularity.

---

## **Installation & Deployment**

### **Prerequisites**

- **Java 17+** (Ensure Java is installed: `java -version`)
- **Apache Maven** (Verify installation: `mvn -version`)
- **MySQL Database** (Required for data storage)

### **Setup Instructions**

1. **Clone the Repository**
   ```sh
   git clone git@github.com:nickTheof/school-swing-soa-mvp-app.git
   cd school-swing-soa-mvp-app
   
2. **Database setup**
   1. **Create a MySQL User**  
      In order to connect to the MySQL database, you must create a new user with the following credentials:

      - **Username**: `user7pro`
      - **Password**: (Choose a secure password for the user)
   2. **Step 2: Set the User Password in the Environment Variable**
      - **Environment Variable Name**: `PASSWD_USER7`
      - **Environment Variable Value**: The password you used when creating the `user7pro` MySQL user.
   
   3. **Step 3: Database Schema and Data**
      - You can find the snap of the db schema and data in the src/main/resources/sql

3. **Build the Application**
   ```sh
   mvn clean package

4. **Run the Application**
   ```sh
   cd target
   java -Xmx1024m -jar school-swing-soa-mvp-clean-1.0-SNAPSHOT.jar


## **Technologies Used**

- **Java 17** – The core programming language used to build the application.
- **Java Swing** – A GUI toolkit for creating the graphical user interface.
- **MySQL** – Relational database management system used for storing application data.
- **Apache Maven** – A build automation tool used for managing project dependencies and building the project.
- **Maven Assembly Plugin** – Plugin used to bundle the application into a single executable JAR with all dependencies.
- **Maven Shade Plugin** – Used for creating an "uber JAR" by combining all dependencies into a single JAR file.
- **Service-Oriented Architecture (SOA)** – Software architecture that allows various services to communicate over a network, ensuring flexibility and scalability.
- **Clean Architecture** – A software design pattern that separates the application into layers for better maintainability and testability.
- **Model-View-Presenter (MVP)** – A design pattern that improves the separation of concerns in the application, enhancing testability and modularity.

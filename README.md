# JPA Hibernate CRUD Example (Java 8 + PostgreSQL)

This is a simple **Java SE console application** using **JPA (Hibernate native)** to perform CRUD operations on `regions` and `countries` tables in a PostgreSQL database.

---

## 📂 Project Structure:

src/

├── main/

│ ├── java/

│ │ ├── App/

│ │ │ ├── Main.java

│ │ │ ├── JPA_Util.java

│ │ ├── entity/

│ │ │ ├── Region.java

│ │ │ ├── Country.java

│ │ ├── service/

│ │ ├── RegionService.java

│ │ ├── CountryService.java

│ ├── resources/

│ └── META-INF/

│ └── persistence.xml


---

## ⚙️ Technologies Used

- **Java 8**
- **Maven**
- **JPA 2.2 (javax.persistence)**
- **Hibernate ORM 5.6**
- **PostgreSQL**
- **Console-based Menu Application**

---

## 🗃️ Database Schema

- `regions(region_id, region_name)`
- `countries(country_id, country_name, region_id)` → `region_id` is a foreign key

---

## 🔧 How to Run

### 1. 🛠 Prerequisites

- Java 1.8 installed
- PostgreSQL running (with a database created, e.g., `newDB`)
- Maven installed and configured in system `PATH`

---

### 2. 🛒 Maven Dependencies (`pom.xml`):

Ensure the following dependencies are in your `pom.xml`:

```xml

<dependencies>

    <!-- Hibernate Core -->
    
    <dependency>
    
        <groupId>org.hibernate</groupId>
        
        <artifactId>hibernate-core</artifactId>
        
        <version>5.6.15.Final</version>
        
    </dependency>

    <!-- JPA API -->
    
    <dependency>
    
        <groupId>javax.persistence</groupId>
        
        <artifactId>javax.persistence-api</artifactId>
        
        <version>2.2</version>
        
    </dependency>

    <!-- PostgreSQL JDBC Driver -->
    
    <dependency>
    
        <groupId>org.postgresql</groupId>
        
        <artifactId>postgresql</artifactId>
        
        <version>42.2.27</version>
        
    </dependency>
    
</dependencies>

----

## 3.⚙️ Configure persistence.xml:

<persistence-unit name="myPersistenceUnit">

    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    
    <class>entity.Region</class>
    
    <class>entity.Country</class>
    
    <properties>
    
        <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
        
        <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/newDB"/>
        
        <property name="javax.persistence.jdbc.user" value="postgres"/>
        
        <property name="javax.persistence.jdbc.password" value="shrutu"/>

        <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
        
        <property name="hibernate.hbm2ddl.auto" value="update"/>
        
        <property name="hibernate.show_sql" value="true"/>
        
    </properties>
    
</persistence-unit>

----

## 💡 Features:

Create, Read, Update, Delete (CRUD) operations for:

Region

Country

Bi-directional relationship mapping between Region and Country

Lazy-loading for performance

Graceful handling of user input and exceptions

Auto table creation using hibernate.hbm2ddl.auto=update

##  4.📞Contact:

For any questions or feedback, feel free to reach out:

Your Name : shruti thorat

Email: shrutithorat767@gmail.com

GitHub: shruti-thorat0715








# MASTERCARD CODE CHALLENGE
This project will determines if two cities are connected. Two cities are considered connected if there’s a series of roads that can be traveled from one city to another

* List of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

* Once application deployed and started it can be accessed with endpoint http://localhost:8080/connected?origin=city1&destination=city2

* This application will respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.
                   Any unexpected input should result in a ’no’ response.

## For Example
city.txt content is:<br>

<code>
Boston, New York<br>
Philadelphia, Newark<br>
Newark, Boston<br>
Trenton, Albany
</code>

* http://localhost:8080/connected?origin=Boston&destination=Newark <br>Should return yes

* http://localhost:8080/connected?origin=Boston&destination=Philadelphia
 <br>Should return yes

* http://localhost:8080/connected?origin=Philadelphia&destination=Albany
 <br>Should return no

### Pre-requisites

* JAVA 8 or higher
* Maven 3.5 or higher

rest all the required dependencies will be downloaded by Maven itself

### How to start Application

#### From Command Prompt or Terminal
After checkout the project from GitHub execute following command 

* make sure in command prompt you are in root directory of project and can see pom.xml
```
mvnw spring-boot:run
```

#### From Intellij

import the project as an existing project in intellij

* locate the ConnectedCitiesApplication by `Ctrl+Shift+N` then press and `Enter` and then press `ctrl+shift+F10`

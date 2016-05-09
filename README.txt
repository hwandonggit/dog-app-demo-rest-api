Introduction
------------
Dog  App for API Code Challenge for demonstrating REST APIs.

Tech Spec
---------
1. Java 8
2. Spring Boot, 
3. In-memory database, 
4. Embedded Tomcat
5. Swagger

Setup (From Jar or Maven)
-----
Run jar from build
------------------
java -jar -Dspring.profiles.active=test target/dog-app-demo-rest-0.2.0.war


--Or-- 

Maven
-----
mvn spring-boot:run



Usage
-----
Please follow below steps using cURL or any ReST client of your choice.


Dog Set up (Initial Set-up)
---------------------------
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{ "name" : "marco", "description" : "Super social", "breed" : "Labrador", "imageUrl": "http://www.akc.org/dog-breeds/labrador-retriever/" }' "http://localhost:8090/example/v1/dogs"
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{ "name" : "sunny", "description" : "Smart , confident , courageous", "breed" : "Greyhound", "imageUrl": "http://www.akc.org/dog-breeds/greyhound/" }' "http://localhost:8090/example/v1/dogs"
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{ "name" : "brando", "description" : "Will be your bff", "breed" : "Greyhound", "imageUrl": "http://www.akc.org/dog-breeds/greyhound/" }' "http://localhost:8090/example/v1/dogs"


User Set up (Initial Set-up)
---------------------------
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{ "username":"suresh", "password": "1234" }' "http://localhost:8090/example/v1/users/registration"
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{ "username":"murali", "password": "5678" }' "http://localhost:8090/example/v1/users/registration"


Dog Get All 
-----------
Note : 
1. Grouped by Breed


curl -X GET -H "Content-type: application/json" -H "Accept: application/json" "http://localhost:8090/example/v1/dogs"


Dog Get By Breed 
----------------
Note : 
1. Single Breed


curl -X GET -H "Content-type: application/json" -H "Accept: application/json" "http://localhost:8090/example/v1/dogs/breeds/greyhound"


Dog Get Individual
------------------
Note:
1. Feel Free to use the dog id

curl -X GET -H "Content-type: application/json" -H "Accept: application/json" "http://localhost:8090/example/v1/dogs/1"


Vote for Favorite
-----------------
Note:
1.Must be a logged in user. 
2.Voting second time , will returnn 406- Not Acceptable

curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{"id":1}' "http://localhost:8090/example/v1/favorites/dogs/3"


Un - Favorite 
-----------------
Note:
1.Must be a logged in user. 
2.Un - Favoriting second time , will returnn 304- Not Modified  (IMPORTANT : This ensures , an user will be able to vote only once.)


curl -H "Accept: application/json" -H "Content-type: application/json"  -X DELETE -d '{"id":1}' "http://localhost:8090/example/v1/favorites/dogs/3"


Set-up to demo , that Dogs are sorted by the votes (Initial Set-up)
-------------------------------------------------------------------
Note:
1. This will favorite up "brando" to 2. Hence "brando" will appear before "sunny" 
(IMPORTANT: This ensures Dog breed App returns the response sorted by the number of times sorted)  

curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{"id":1}' "http://localhost:8090/example/v1/favorites/dogs/3"
curl -H "Accept: application/json" -H "Content-type: application/json"  -X POST -d '{"id":2}' "http://localhost:8090/example/v1/favorites/dogs/3"

Sorted 
-------

curl -X GET -H "Content-type: application/json" -H "Accept: application/json" "http://localhost:8090/example/v1/dogs"






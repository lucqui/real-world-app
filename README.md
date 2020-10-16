# Real World App
From project root

To run all tests:
```
gradle clean test -is
```

To start the application with docker-compose:
```
 gradle build docker -is && docker-compose up
```
This command will start 3 service: MySQL database, spring-boot demo application (on port 8080) and adminer (on port 8081)  

Save data:
```
curl -v -F key1=file -F file=@csv/invoice_data_1.csv http://localhost:8080/invoice/digest
```

Unfortunately, the database on docker is having a couple issues as you will see when issuing that command. I was not able to resolve that in the allotted time unfortunately. 
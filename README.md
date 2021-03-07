# Players
Simple SpringBoot project that aims to handle two types of players: experts and novices. 

## Tools and Frameworks
* Apache Maven (version: 3.6.3)
* Java (version: 11)
* SpringBoot (version: 2.4.3)
* Kafka (version: 2.7.0) 

##Run and Installation

Download or clone this project to your local machine. 

Open the terminal inside the project folder then run the following command to download and install dependencies:

``mvn clean install ``

Inside the project folder, run the following command to start the project so you will be able to send requests to it:

``java -jar target/*.jar ``


##Kafka

To test this project you need to have **Zookeeper** and **Kafka** running on your machine.

#### Topics
When the project detects novice players, it will post messages on a topic called *novice-players*.

Kafka can create the topic automatically, but it is not recommended unless you have Kafka configurations well setted, 
specially the partitions default quantity.

##### Create Topics (Producer)

After start Zookeeper and Kafka server on your local
machine, you will have to create a the topic by running the following command on your terminal:

``kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic novice-players --create --partitions 3 --replication-factor 1``

##### Watch Topics Messages (Consumer)

You can check the messages on the topic by running the following command on your terminal:
``kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic novice-players --from-beginning``


## Send Request
You can use any tool you like to send request to the API. Currently, the only endpoint available is: 

``POST /players``

Payload sample:

``
 {
   "players": [
     {
       "name": "Sub zero",
       "type": "expert"
     },
     {
       "name": "Scorpion",
       "type": "novice"
     },
     {
       "name": "Reptile",
       "type": "meh"
     }
   ]
 }
 ``

## Database

This project uses in-memory database H2, you can have access to the data through the your web browser by the going to
the following URL: ``http://localhost:8080/h2-console``.

Fill the form with the following information:
* **Driver Class**: org.h2.Driver
* **JDBC URL**: jdbc:h2:mem:playersdb
* **User Name**: sa
* **Password**: password


## Further improvements
* Dockerize this application
 
# Application
IDP, Spring Boot, Hibernate, Rest, Couchbase, Kafka, Spark

1.	ZooKeeper
	Install zookeeper by using steps given in below link.
	https://www.edureka.co/community/39059/installing-zookeeper-on-windows

2.	Kafka
	Install kafka by using steps given in below link.
	https://www.edureka.co/community/39170/how-to-install-kafka-on-windows-system

3.	Ignite
	Download ignite from below link
	https://downloads.apache.org//ignite/2.7.6/apache-ignite-2.7.6-bin.zip
	
	
4.	########################## START ZOOKEEPER AND KAFKA ##########################
	Hit below 3 commands on different command promt each
	
	1.	zkserver
	2.	E:\Softwares\kafka_2.13-2.4.0\bin\windows\kafka-server-start.bat E:\Softwares\kafka_2.13-2.4.0\config\server.properties
	3.	E:\Softwares\kafka_2.13-2.4.0\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic App --from-beginning 
	
5.	Run com.org.main.Application class
	
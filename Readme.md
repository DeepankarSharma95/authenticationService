== Executing the application on Docker:

== First Run Only:
docker run -p 3306:3306 --name payrollresultsdb -e MYSQL_ROOT_PASSWORD=123456 --restart unless-stopped -d mysql:latest

mysql -uroot -p123456 -h 127.0.0.1
mysql> create database authenticationDB;

== Everytime the application is modified:

mvn clean install

docker build . -t authservice -f dockerFile

docker run -p 8081:8081 -p 28787:28787 --name authservice --link payrollresultsdb:mysql --restart unless-stopped -d authservice

== Stopping and Removing containers:

docker stop authservice
docker rm authservice

# StonePaperScissors App (Backend)

This application is a take on the popular Stone Paper Scissors game. It allows you play against the computer and also keeps a history of your gameplay.

There are two projects in this repo. I made them submodules of this main app, but you can download the frontend and the backend individually.

I made them submodules so you can run the app using docker-compose.

Repo for Backend: https://github.com/samsonKayode/SSP_FullStack.git (dev branch)

Repo for Frontend: https://github.com/samsonKayode/SSP_FullStack.git (frontend branch)

###Stacks used:

<b><u>Backend</u></b>

1. Java 17
2. Spring Boot Java 3
3. Spring Security
4. Mysql 8.0 Database 
5. ELK (Elastic, Logstash & Kibana) for logging
6. Prometheus & Grafana for metrics and monitoring
7. OpenApi3 (Swagger) for controller documentation

### How To Run The Project

####In order to run this application you need to install two tools: Docker & Docker Compose.

The entire backend application can be run with a single command on a terminal while in the backend directory:

```
$ docker-compose up -d
```
If you want to stop it, use the following command:

```
$ docker-compose down
```

### How To Access Backend URLS
```
http://localhost:8080/api/v1
```
Swagger-UI :
```
http://localhost:8080/api/v1/swagger-ui.html
```
Access Kibana : 
```
http://localhost:5601/
```
Access Grafana : 
```
http://localhost:3000
```
Access Prometheus : 
```
http://localhost:9090
```

#### Backend (REST API)

This is a Spring Boot (Java) based application that connects with a
database that and expose the REST endpoints that can be consumed by
frontend.

Full list of available REST endpoints could be found in Swagger UI,
which can be accessed via: **http://localhost:8080/api/swagger-ui.html**

## StonePaperScissors App (FrontEnd)

The frontend which consumes the rest api allows you access the user interface of the game.

###Stacks used:

<b><u>Frontend</u></b>

1. Angular 17
2. Material css
3. TypeScript
4. Nginx (deployment)

### How To Access Frontend URLS
```
http://localhost:80/login
```

### How To Run The Frontend Project

To access the Frontend project , it has to be in a directory where the backend directory is also located as show below



run docker compose from the Backend directory to access the complete application






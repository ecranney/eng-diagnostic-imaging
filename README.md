# DIES (Diagnostic-Imaging System)


## Installation

This project dynamically configured settings as well as Heroku support 


### Prerequisites

POM file was made to generate WAR file and to support Heroku war file deployement
Anyone can run the POM file with this maven commands to bulid the war file

```
mvn package

```

### Deployment in Heroku repository 

https://devcenter.heroku.com/articles/war-deployment

You need to run this command only once

```
heroku plugins:install heroku-cli-deploy
```

And repeat everytime you update the project with this command to deploy it in the Heroku. 
(Rename the war file for if you change the POM file settings. And --app means your heroku project name)

```
heroku war:deploy target/dies-1.2.war --app diags
```

## External Libraries 

Following external libraries are in 'lib' folder 

```
servlet-api.jar: Has all the Java servlet related classes for the project
postgresql-42.2.5.jar: Contains the java client for the database and java. (Not this version only support from version 8 up)
```

## Live URL

* https://diags.herokuapp.com/login.jsp

Default Login: admin (password:admin)

## Versioning

This project has three remote git repositaies for Heroku, Bitbucket and Github

## Authors

* **Evan Cranney & Shalitha Karunatilleke** 

## Documentation

Documentation are in project root

## Heroku Troubleshoot
Deploy locally

```
mvn clean install
java -jar target/dependency/webapp-runner.jar target/*.war
```

more information https://devcenter.heroku.com/articles/java-webapp-runner

Deploy in server

```
mvn clearn install or mvn package

heroku plugins:install heroku-cli-deploy
heroku war:deploy target/dies-1.2.war --app diags
```

Heroku server restart

```
heroku restart --app diag
```

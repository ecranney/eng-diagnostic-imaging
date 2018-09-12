# Welcome to DIES (Diagnostic-Imaging System)
The purpose of the Diagnostic Imaging Enterprise System (DIES) is to support medium to large medical imaging practices in the provision of imaging services.

# Production 
This project dynamically configured settings for Heroku support 
`'Production URL:` [https://diags.herokuapp.com/](https://diags.herokuapp.com/)

### Live Demo URL

* https://diags.herokuapp.com/
  Default `user`: admin, `password`: admin
 
# Development 

### Version Controlling 
This project is maintained under three repositories. 

 - ~~`Heroku`~~ for Production Deployment 
 (NOTE: We are not using Heroku git version control as it is only for the war file deployment. Do not push any of the code into production environment using Heroku commands)
	 > Please read the Heroku Troubleshoot guide under this README.md file
 
 - `Bitbucket` This repository is only for the University of Melbourne 

- `Github` This is the main repository that we maintained for the project as well as for our contributions for anyone's reference (github user: ecra93, shalithakaru)

### Development Environment

##### Option 1: Eclipse 
After cloning the project add the project using following steps 

 - Click on 'Open project from file system' option to add the project into your workspace of Eclipse
 - Now right click on project properties and go to 'Project Facets' to select the checkboxes for 
   > Java  1.8
   > Dynamic Web Module 4.0  
 
 - Again go to properties by right click on project properties and go to 'Server' and add tomcat server
	 > Please note that this option will be available if you already define  Tomcat Server from Eclipse -> Windows -> Preferences -> Server -> Runtime Environments -> Add. We recommend using Tomcat 9.0
 
##### Option 2: Eclipse with Maven Support 
 - After cloning the project run the following commands 
	  ````
	  mvn eclipse: eclipse
	````
	> Please make sure you have configured the project for Dynamic Web Module 4.0 once you add it to your project path. You can also use mvn idea: idea for intellij Idea support 
 
 ##### External Libraries 

Following external libraries are in 'lib' folder under 'WEB-INF' folder 

`servlet-api.jar` Has all the Java servlet related classes for the project
`postgresql-42.2.5.jar` Contains the java client for the database and java. (Not this version only support from version 8 up)
`log4j-api-2.11.1.jar` and `log4j-core-2.11.1.jar` to support logging 

# Database
This project supports PostgreSQL database. Currently the settings for the database in DBConnection.java file 
`TODO: Move DBConnections configurations to a properties file`
> We recommend using https://teamsql.io/ or DataGrip from Jetbrains (Which is free for students) than PGAdmin4 because of the flexibility  in UI Client

##### Database Setup
Run the following SQL files to create a new database setup

 1. {TODO} We are planning to do this during submission for Part 3
 
 
# Project Structure

```
.
├── database                # Database dump files 
├── docs                    # Documentation files 
│   ├── draw.io             # {TODO} Project architechture files for modification draw.io
│   ├── unimelb             # {TODO} Assignment details
├── src                     # Source files 
├── WebContent              # Dynamic resources such as JSP files and associated metadata
├── pom.xml                 # Maven build file
├── LICENSE					# {TODO}
├── system.properties		# Heroku support file 
├── .gitignore              # To ignore all kind of IDE, build related files from git  
└── README.md

```

# Heroku Troubleshoot

#### Installations of Heroku CLI
> https://devcenter.heroku.com/articles/heroku-cli 
> Please do not configure anything for Heroku if you want to deploy it in the same server. You only need the CLI installation in your machine

#### Deployment of WAR file in Heroku  

> More info: https://devcenter.heroku.com/articles/war-deployment

 - You need to run the first command only once to install the cli
   support for deploy in your terminal 
	   ````heroku plugins:install heroku-cli-deploy```` 

- And repeat this command everytime you update
  ````mvn clean install or mvn package````
  
-  The project with this command to deploy it in the Heroku under https://diags.herokuapp.com/  
   Rename the war file {version} or  {project.name}, if you change the POM file with
   `<version>2.0</version>`  or `<artifactId>dies</artifactId>`   
   > heroku war:deploy target/{project.name}-{version}.war --app diags 
   ```Example: heroku war:deploy target/dies-2.0.war --app diags```

#### Heroku Server Restart for PostgreSQL
``` heroku restart --app diag ```

#### Heroku Dyno Restart for 
```heroku ps:restart --app diag```

#### Deploy the Project in Local Web application Manager (Tomcat) 
>(This is an extra advantage of Heroku Cli installation)
> More information https://devcenter.heroku.com/articles/java-webapp-runner
```` 
mvn clean install
java -jar target/dependency/webapp-runner.jar target/*.war
````

# Developed by

* **Evan Cranney** https://github.com/ecra93/
* **Shalitha Weerakoon Karunatilleke** https://github.com/shalithakaru/



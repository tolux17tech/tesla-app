pipeline {
    agent any

    tools {
        maven 'maven-3.8.6'
    }
    stages {
        stage("Get the code"){
            steps{
                sh "echo Cloning the latest application version"
                git changelog: false, credentialsId: 'Gitlabtech', poll: false, url: 'https://github.com/tolux17tech/tesla-app.git'
         }                
       }
        stage("Buiding and Testing our code"){
            steps{
                sh "echo Testing and building"
                sh "echo testing must move"
                sh "mvn clean package"
               }
           }
       stage("Testing code with sonarqube"){
            steps{
                sh "echo Testing code with sonarqube"
                sh "mvn sonar:sonar"
               }
          }  
      
       stage("Upload artifact to nexus"){
           steps{
                sh "echo Uploading to nexus"
                sh "mvn deploy"
           }
      }
      stage("Deploy to container"){
            steps{
                sh "echo deploying to tomcat"
                deploy adapters: [tomcat9(credentialsId: 'tomcatid', path: '', url: 'http://66.175.215.113:8080')], contextPath: null, war: 'target/*.war'
            }
      }
   }
   post{
    success{
        emailext body: 'Build was sucessful', subject: 'Build was successful', to: 'tolux17@gmail.com'
    }
    failure{
        emailext body: 'Build was sucessful', subject: 'Build failed', to: 'tolux17@gmail.com'
    }
    always{
        emailext body: 'Build was sucessful', subject: 'Job ran sucesssfully', to: 'tolux17@gmail.com'
    }
   }
}

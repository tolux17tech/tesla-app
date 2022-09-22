def gv

pipeline {
    agent any

    environment{
        TOMCAT_IP = 'http://66.175.215.113:8080'
        EMAIL = 'tolux178@gmail.com'
    }
    parameters {
        choice choices: ['1.9', '2.9', '3.0'], description: 'Telling me which parameters to use', name: 'VERSION'
    }

    tools {
        maven 'maven-3.8.6'
    }
    stages {
        stage("init"){
            steps{
              gv = load "script.groovy"  
         }                
       }
    // stages {
    //     stage("Getting code from SCM"){
    //         steps{
    //             sh "echo Cloning the latest application version"
    //             git changelog: false, credentialsId: 'Gitlabtech', poll: false, url: 'https://github.com/tolux17tech/tesla-app.git'
    //      }                
    //    }
        stage("Buiding and Testing our code"){
            steps{
                gv.buildPackage()
               }
           }
       stage("Testing code with sonarqube"){
       
            steps{
                 gv.testWithSonar()
               }
          }  
      
       stage("Upload artifact to nexus"){
           steps{
                gv.deployToNexus()
           }
      }
      stage("Deploy to container"){
            steps{
                gv.deploy2Container()
            }
      }
   }
   post{
    success{
        emailext body: 'Build was sucessful', subject: 'Build was successful', to: "${EMAIL}"
    }
    failure{
        emailext body: 'Build was sucessful', subject: 'Build failed', to: "${EMAIL}"
    }
    always{
        emailext body: 'Build was sucessful', subject: 'Job ran sucesssfully', to: "${EMAIL}"
    }
   }
}

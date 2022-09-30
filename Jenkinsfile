library identifier: 'my-library@master', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: '', remote: 'https://gitlab.com/tolux17/my-library.git', traits: [gitBranchDiscovery()]])
pipeline {
    agent any
    environment {
      TOMCAT_URL = "http://66.175.215.113:8080"
    }

    tools{
        maven 'maven'
    }
    stages {
       
        stage ('package') {
            steps {
                script{
                   packageApp()
                }
            }
        }
        stage ('sonar Testing') {
            steps {
                script{
                    sonarApp()
                }
            }
        }
        stage ('Deploy') {
            steps {
                script{
                    deployApp()
                }
            }
        }
        stage ('Deploying to UAT') {
            steps {
                script{
                    deployContainer()
                }
            }
        }
        stage ('APPROVAL GATE BEFORE PROD') {
            steps {
                script{
                   gateApp()
                }
            }
        }
       stage ('Deployb to Production') {
            steps {
                script{
                    deployContainer()
                }
            }
        }

        stage ('Kubernetes start'){
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_access_secret_access_key')
            }
            steps{
                script{
                    echo "deploying the docker images on kubernetes"
                    sh "kubectl create deployment nginx-deployment --image=nginx"
                }
            }
        } 
    }
}

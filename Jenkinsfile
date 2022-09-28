//library identifier: 'my-library', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: '', remote: 'https://gitlab.com/tolux17/my-library.git', traits: [gitBranchDiscovery()]])


library identifier: 'my-library', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: '', remote: 'https://gitlab.com/tolux17/my-library.git', traits: [gitBranchDiscovery()]])
pipeline {
    agent any

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
                    sonar()
                }
            }
        }
        stage ('Deploy') {
            steps {
                script{
                    deploy()
                }
            }
        }
        stage ('Deploying to UAT') {
            steps {
                script{
                    sh "echo deploy to UAT environment"
                    deploy adapters: [tomcat9(credentialsId: 'tomcatapp', path: '', url: 'http://66.175.215.113:8080')], contextPath: null, war: 'target/*.war'
                }
            }
        }
        stage ('APPROVAL GATE BEFORE PROD') {
            steps {
                script{
                   gate()
                }
            }
        }
       stage ('Deployb to Production') {
            steps {
                script{
                    sh "echo deploying to production environment"
                    deploy adapters: [tomcat9(credentialsId: 'tomcatapp', path: '', url: 'http://66.175.215.113:8080')], contextPath: null, war: 'target/*.war'
                }
            }
        } 
    }
}

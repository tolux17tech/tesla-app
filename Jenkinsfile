library identifier: 'Mystery ', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: '', remote: 'https://gitlab.com/tolux17/my-library.git', traits: [gitBranchDiscovery()]])
pipeline {
    agent any

    tools{
        maven 'maven'
    }
    stages {
        stage ('validate') {
            steps {
                script{
                    sh "mvn test"
                }
            }
        }
        stage ('package') {
            steps {
                script{
                    sh "mvn package"
                }
            }
        }
        stage ('sonar Testing') {
            steps {
                script{
                    sh "mvn sonar:sonar"
                }
            }
        }
        stage ('Deploy') {
            steps {
                script{
                    sh "mvn deploy"
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
                    sh "echo This is the approval gate"{
                        input id: '34787484', message: 'Please review accept or abort', ok: 'Approve'
                    }
                    
                    timeout(time: 300, unit: 'SECONDS') {
                          // some block
                    }
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
        stage ('Kubernetes start'){
            steps{
                script{
                    echo "deploying the docker images on kubernetes"
                    sh "kubectl create deployment nginx-deployment --image=nginx"
                }
            }
        }  
    }
}

node{
    def mavenHome = tool name: 'maven-3.8.6'

    stage("1 Cloneclonegit"){
        git "https://github.com/tolux17tech/tesla-app.git"
    }
    stage("2sonarqualitytest"){
        sh "${mavenHome}/bin/mvn sonar:sonar"
    }

    stage("3Buildtarget"){
        sh "${mavenHome}/bin/mvn package"
    }
    stage("4Upload2nexus"){
        sh "${mavenHome}/bin/mvn deploy"
    }

    stage("5Deployto2UAT"){
        sh "echo Deploying to UAT Environment"
        deploy adapters: [tomcat9(credentialsId: 'tomcatid', path: '', url: 'http://66.175.215.113:8080/')], contextPath: null, war: 'target/*.war'
    }
     stage("6ApprovalGate"){
        sh "echo Waiting for approval gate review before deployment"
        timeout(5) {
            input message: "Approval timedout, this will try again later"
        }
     }
     stage("7Deploy2Production"){
        deploy adapters: [tomcat9(credentialsId: 'tomcatid', path: '', url: 'http://66.175.215.113:8080/')], contextPath: null, war: 'target/*.war'
     }

    
}

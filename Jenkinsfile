node{
    def mavenHome = tool name: 'maven'
    stage('1clonecode'){
        git "https://github.com/tolux17tech/tesla-app.git"
        // sh "git clone https://github.com/LandmakTechnology/maven-web-application.git"
    }
    stage('2 Test and build'){
        sh "${mavenHome}/bin/mvn  clean package"
    }
    stage('3 Code quality'){
        sh "${mavenHome}/bin/mvn  clean sonar:sonar"
    }
    stage('4 Nexus upload'){
        sh "${mavenHome}/bin/mvn  deploy"
    }
    stage('5deploy2UAT'){
        sh "echo deploying to UAT"
        deploy adapters: [tomcat9(credentialsId: 'tomcatid', path: '', url: 'http://45.56.109.63:8080')], contextPath: null, war: 'target/*.war'
    }
    stage('6 Approval gate'){
        sh "echo Application is ready for review"
        timeout(5) {
            input message: 'Application is ready for deployment, please review'
        }
    }
    
}

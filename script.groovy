def buildPackage(){
    sh "echo Testing and building"
    sh "echo testing must move"
    sh "mvn clean package"
}

def testWithSonar(){
     sh "echo Testing code with sonarqube"
     sh "mvn sonar:sonar"
     sh "echo ${params.VERSION}"
}

def deployToNexus(){
    sh "echo Uploading to nexus"
    sh "mvn deploy"
}

def deploy2Container(){
    sh "echo deploying to tomcat"
    deploy adapters: [tomcat9(credentialsId: 'tomcatid', path: '', url: "${TOMCAT_IP}")], contextPath: null, war: 'target/*.war'
}
return this

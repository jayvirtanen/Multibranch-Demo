pipeline {
  agent any
  stages {
    stage('Maven build') {
      agent any
      steps {
        sh 'mvn clean install -f webapp/pom.xml'
      }
    }

  }
}
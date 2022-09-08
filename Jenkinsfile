pipeline {
  agent {
    label 'mypodtemplate-v1'
  }
  stages {
    stage('Maven Build') {
      steps {
        container(name: 'maven') {
          sh 'mvn clean package -f webapp/'
          sh 'ls webapp/'
        }
        archiveArtifacts 'webapp/target/*'
      }
    }
    stage('Docker Build') {
      steps {
        container(name: 'kaniko') {
          sh '#!/busybox/sh echo "hello world"'
        }
      }
    }

  }
}

@Library ('shared-libs') _
pipeline {
  stages {
    stage('Maven Build') {
      agent{
        kubernetes{
          yaml mavenTemplate()
        }
      }
      steps {
        container(name: 'maven') {
          sh 'mvn clean package -f webapp/'
          sh 'ls webapp/'
        }
        archiveArtifacts 'webapp/target/*'
      }
    }
    stage('Docker Build') {
      agent{
        kubernetes{
          yaml kanikoTemplate()
        }
      }
      steps {
        container(name: 'kaniko', shell: '/busybox/sh') {
          sh 'echo "hello world"'
        }
      }
    }

  }
}

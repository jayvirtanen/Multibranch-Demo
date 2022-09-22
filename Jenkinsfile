@Library ('shared-libs') _
pipeline {
  agent none
  stages {
    stage('Maven Build') {
      agent{
        kubernetes{
          yaml mavenTemplate()
          namespace 'cloudbees-platform'
        }
      }
      steps {
        container(name: 'maven') {
          sh 'mvn clean package -f webapp/'
          sh 'ls webapp/'
          stash(name: 'WebApp Binaries', includes: 'webapp/target/*.war')
          stash(name: 'dockerfile', includes: 'docker/Dockerfile')
        }
      }
    }
    stage('Docker Build') {
      agent{
        kubernetes{
          yaml kanikoTemplate()
          namespace 'cloudbees-platform'
        }
      }
      steps {
        container(name: 'kaniko', shell: '/busybox/sh') {
          unstash 'WebApp Binaries'
          unstash 'dockerfile'
          sh 'mv webapp/target/* docker/'
          sh 'ls docker'
          sh '/kaniko/executor --context docker/ --destination janivirtanen/java-applet:latest'
        }
      }
    }

  }
}

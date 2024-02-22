@Library ('shared-libs') _
pipeline {
  agent none
  parameters {
  string defaultValue: 'latest', name: 'version'
	}
  stages {
    stage('Maven Build') {
      agent{
        kubernetes{
          yaml mavenTemplate()
          namespace 'default'
        }
      }
      steps {
        container(name: 'maven') {
          sh 'mvn clean package -f webapp/'
          stash(name: 'WebApp Binaries', includes: 'webapp/target/*.war')
          stash(name: 'dockerfile', includes: 'docker/Dockerfile')
        }
      }
    }
    stage('Docker Build') {
      agent{
        kubernetes{
          yaml kanikoTemplate()
          namespace 'default'
        }
      }
      steps {
        container(name: 'kaniko', shell: '/busybox/sh') {
          unstash 'WebApp Binaries'
          unstash 'dockerfile'
	  sh 'echo "hello world"'
          sh 'mv webapp/target/* docker/'
          sh '/kaniko/executor --context docker/ --destination janivirtanen/java-applet:$version'
        }
      }
    }

  }
}

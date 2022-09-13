@Library ('shared-libs') _
pipeline {
  agent none
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
          stash(name: 'WebApp Binaries', includes: 'webapp/target/**')
          stash(name: 'dockerfile', includes: 'docker/Dockerfile')
        }
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
          unstash 'WebApp Binaries'
          unstash 'dockerfile'
          sh '''#!/busybox/sh
            echo "FROM jenkins/inbound-agent:latest" > Dockerfile
            /kaniko/executor --context `pwd` --destination janivirtanen/hello-kaniko:latest --verbosity debug
          '''
        }
      }
    }

  }
}

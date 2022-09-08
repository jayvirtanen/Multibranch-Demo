pipeline {
    agent { label 'mypodtemplate-v1' }
    stages {
        stage('build') {
            steps {
                container('maven'){
                sh 'mvn -version'
                sh 'ls'
                }
            }
        }
    }
}

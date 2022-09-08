pipeline {
    agent { label 'mypodtemplate-v1' }
    stages {
        stage('build') {
            steps {
                container('maven'){
                sh 'mvn clean package -f webapp/'
                sh 'ls'
                }
            }
        }
    }
}

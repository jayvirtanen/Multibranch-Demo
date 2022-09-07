
node {

    stage ('Clone') {
        git url: 'https://github.com/jayvirtanen/Multibranch-Demo.git'
    }

    stage ('Exec Maven') {
        sh 'cd webapp'
	sh 'mvn clean install'
	sh 'cd ..'
        sh 'cp webapp/target/*.war docker/'
        sh 'ls docker/'
    }

    stage ('Build docker image') {

        docker.build('webapp-container:' + "${BRANCH_NAME}" + "${BUILD_NUMBER}", 'docker')
    }
	
}

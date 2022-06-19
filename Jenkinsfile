
node {
    def server
    def rtMaven = Artifactory.newMavenBuild()
    def descriptor = Artifactory.mavenDescriptor()
    def buildInfo
    def release = false
    String snapshotRepo
    String releaseRepo
    String jpd
    String registry
    String dockerlocal
    String branch

    stage ('Env Init'){

    echo "${SNAPSHOT_REPO}"
	snapshotRepo = "${SNAPSHOT_REPO}"
    releaseRepo = "${RELEASE_REPO}"
    jpd = "${ARTIFACTORY_SERVER}"
    registry = "${REGISTRY_URL}"
    dockerlocal = "${DOCKER_REPO}"
    branch = "${BRANCH_NAME}"
    }    

    stage ('Clone') {
        git url: 'https://github.com/jayvirtanen/Multibranch-Demo.git'
    }

    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
        server = Artifactory.server jpd
        if (release==true && branch=='master'){
            descriptor.pomFile = 'webapp/pom.xml'
            descriptor.setVersion "1.0.1"
            descriptor.transform()
        }
        // Tool name from Jenkins configuration
        rtMaven.tool = 'Maven'
        rtMaven.deployer releaseRepo: releaseRepo, snapshotRepo: snapshotRepo, server: server
        rtMaven.resolver releaseRepo: releaseRepo, snapshotRepo: snapshotRepo, server: server
        rtDocker = Artifactory.docker server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec Maven') {
        rtMaven.run pom: 'webapp/pom.xml', goals: 'clean install', buildInfo: buildInfo
        sh 'rm docker/*.war'
        sh 'cp webapp/target/DropDown*.war docker/'
        sh 'ls docker/'
    }

        stage ('Add properties') {
        // Attach custom properties to the published artifacts:
        rtDocker.addProperty("project-name", "docker1").addProperty("status", "stable")
    }

    stage ('Build docker image') {

        docker.build(registry + 'webapp-container:' + branch + "${BUILD_NUMBER}", 'docker')
    }

    stage ('Push image to Artifactory') {
        rtDocker.push registry + 'webapp-container:' + branch + "${BUILD_NUMBER}", dockerlocal, buildInfo
    }

        stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}
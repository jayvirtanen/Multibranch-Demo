
node {
    def server
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo
    String snapshots
    String releases
    String jpd
    String registry
    String dockerlocal

    stage ('Env Init'){

    echo "${SNAPSHOT_REPO}"
	snapshots = "${SNAPSHOT_REPO}"
    releases = "${RELEASE_REPO}"
    jpd = "${ARTIFACTORY_SERVER}"
    registry = "${REGISTRY_URL}"
    dockerlocal = "${DOCKER_REPO}"
    }    

    stage ('Clone') {
        git url: 'https://github.com/jayvirtanen/Multibranch-Demo.git'
    }

    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
        server = Artifactory.server jpd
        // Tool name from Jenkins configuration
        rtMaven.tool = 'Maven'
        rtMaven.deployer releaseRepo: releases, snapshotRepo: snapshots, server: server
        rtMaven.resolver releaseRepo: releases, snapshotRepo: snapshots, server: server
        rtDocker = Artifactory.docker server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec Maven') {
        rtMaven.run pom: 'webapp/pom.xml', goals: 'clean install', buildInfo: buildInfo
        sh 'cp webapp/target/*.war docker/'
        sh 'ls docker/'
    }

        stage ('Add properties') {
        // Attach custom properties to the published artifacts:
        rtDocker.addProperty("project-name", "docker1").addProperty("status", "stable")
    }

    stage ('Build docker image') {

        docker.build(registry + 'webapp-container:' + "${BRANCH_NAME}" + "${BUILD_NUMBER}", 'docker')
    }

    stage ('Push image to Artifactory') {
        rtDocker.push registry + 'webapp-container:' + "${BRANCH_NAME}" + "${BUILD_NUMBER}", dockerlocal, buildInfo
    }

        stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}
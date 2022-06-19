

node {
    def server
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo
    

    stage ('Clone') {
        git url: 'https://github.com/jayvirtanen/Multibranch-Demo.git', credentialsId: ${env.GITHUB_CREDS}	
    }

    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
        server = Artifactory.server ${ARTIFACTORY_SERVER}

        // Tool name from Jenkins configuration
        rtMaven.tool = Maven
        rtMaven.deployer releaseRepo: ${RELEASE_REPO}, snapshotRepo: ${SNAPSHOT_REPO}, server: server
        rtMaven.resolver releaseRepo: ${RELEASE_REPO}, snapshotRepo: ${SNAPSHOT_REPO}, server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec Maven') {
        rtMaven.run pom: 'webapp/pom.xml', goals: 'clean package deploy war:war', buildInfo: buildInfo
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}

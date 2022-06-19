

node {
    def server
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo
    

    stage ('Clone') {
        git url: 'https://github.com/jayvirtanen/Multibranch-Demo.git', credentialsId: ${env.GITHUB_CREDS}	
    }

    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
        server = Artifactory.server 'jaytest4'

        // Tool name from Jenkins configuration
        rtMaven.tool = Maven
        rtMaven.deployer releaseRepo: 'default-maven-virtual', snapshotRepo: 'default-maven-virtual', server: server
        rtMaven.resolver releaseRepo: 'default-maven-virtual', snapshotRepo: 'default-maven-virtual', server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec Maven') {
        rtMaven.run pom: 'webapp/pom.xml', goals: 'clean install war:war', buildInfo: buildInfo
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}

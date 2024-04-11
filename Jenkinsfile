pipeline {
  agent kubernetes
  {
  yaml """
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.8.6-openjdk-11
    imagePullPolicy: Always
    command:
    - cat
    tty: true
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    imagePullPolicy: Always
    command:
    - sleep
    args:
    - 9999999
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /kaniko/.docker
  volumes:
  - name: jenkins-docker-cfg
    projected:
      sources:
      - secret:
          name: docker-credentials 
          items:
            - key: .dockerconfigjson
              path: config.json
"""
  }
  parameters {
  string defaultValue: 'latest', name: 'version'
	}
  stages {
    stage('Maven Build') {
      steps {
        container(name: 'maven') {
          sh 'mvn clean package -f webapp/'
        }
      }
    }
    stage('Docker Build') {
      steps {
        container(name: 'kaniko', shell: '/busybox/sh') {
          sh 'mv webapp/target/* docker/'
          sh '/kaniko/executor --context docker/ --destination janivirtanen/java-applet:$version'
        }
      }
    }
  }
}

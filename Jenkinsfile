pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('dockerCredentials')
    }

    stages {
        stage('Test') {
            agent {
                docker {
                    image 'maven:3.8.1-adoptopenjdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Login to docker') {
            steps {
                sh('docker login -u $DOCKER_CREDENTIALS_USR -p $DOCKER_CREDENTIALS_PSW')
            }
        }

        stage('Build docker image') {
            steps {
                sh('docker build -t $DOCKER_CREDENTIALS_USR/help-queue-spring .')
            }
        }

        stage('Push docker image to registry') {
            steps {
                sh('docker push $DOCKER_CREDENTIALS_USR/help-queue-spring')
            }
        }
    }
}

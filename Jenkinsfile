pipeline {
    agent {
        docker {
            image 'maven:3.8.1-openjdk-11'
            args '-p 6000:8080'
        }
    }

    environment {
        APP_NAME = "players"
    }

    options {
        buildDiscarder logRotator(daysToKeepStr: '15', numToKeepStr: '10')
    }

    stages {
        stage('Cleanup Workspane') {
            steps {
                cleanWs()
                sh """
                echo 'Cleaned up workspace for ${env.APP_NAME}'
                """
            }
        }

        stage('Code checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[url: 'https://github.com/grglucastr/players.git']]
                ])
            }
        }

        stage('Code build') {
            steps {
                sh 'mvn clean install -U -DskipTests'
            }
        }

        stage('Code tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

}
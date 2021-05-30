pipeline {
    agent any

    environment {
        APP_NAME = "players"
    }

    tools {
        maven 'maven3'
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
pipeline {
    agent any
    tools {
        maven 'M3'
    }
    triggers {
        pollSCM('* * * * *') // Optional: set up webhook for real-time triggering
    }
    stages {
        stage('Source Code Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/program_testing_project']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/AhmadDoobi/program_testing_project']]
                )
            }
        }

        stage('Build and Test') {
            steps {
                // Ensure tests are run and logged properly
                bat "mvn clean test package"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t husain7/bookstore:latest .'
                }
            }
        }

        stage('Push Image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'Dockerhub', variable: 'Dockerhub')]) {
                        bat 'docker login -u husain7 -p %Dockerhub%'
                    }
                    bat 'docker push husain7/bookstore:latest'
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                script {
                    bat 'docker pull husain7/bookstore:latest'
                    bat 'docker stop bookstore || true'
                    bat 'docker rm bookstore || true'
                    bat 'docker run -d -p 8088:8088 --name bookstore husain7/bookstore:latest'
                }
            }
        }
    }
}

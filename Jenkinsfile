pipeline {
    agent any
    tools {
        maven 'M3'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/program_testing_project']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AhmadDoobi/program_testing_project']])
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('Build docker image') {
            steps {
                script {
                    bat 'docker build -t husain7/bookstore:latest .'
                }
            }
        }

        stage('Push image to Hub') {
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
                    bat 'docker run -d -p 8088:8088 --name bookstore husain7/bookstore:latest'
                }
            }
        }
    }
}

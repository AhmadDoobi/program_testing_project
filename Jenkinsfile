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
                    withCredentials([sshUserPrivateKey(credentialsId: 'your-ssh-credentials-id', keyVariable: 'SSH_KEY', usernameVariable: 'SSH_USER')]) {
                        bat """
                        ssh -o StrictHostKeyChecking=no %SSH_USER%@localhost '
                            docker pull husain7/bookstore:latest && \
                            docker stop bookstore_container || true && \
                            docker rm bookstore_container || true && \
                            docker run -d --name bookstore_container -p 8080:8080 husain7/bookstore:latest
                        '
                        """
                    }
                }
            }
        }
    }
}

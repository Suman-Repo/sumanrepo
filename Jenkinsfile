pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'react_branch', url: 'https://github.com/Suman-Repo/sumanrepo.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t react-latest .'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'docker stop react-container || true'
                    sh 'docker rm react-container || true'
                    sh 'docker run -d --net my_network --ip 10.0.3.39 -p 80:80 --name react-container react:latest'
                }
            }
        }
    }
}

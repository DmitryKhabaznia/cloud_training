pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'dmitrykh/cloud_training'
        DOCKER_CONTAINER = 'cloud_training'
        ENV_PORT = 80
        APP_PORT = 8080
    }
    stages {
        stage('Build and create jar') {
            steps {
                sh 'chmod +x gradlew && ./gradlew bootJar'
            }
        }
        stage('Run test + test report') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    publishHTML target : [
                        reportFiles: 'index.html',
                        reportName: 'Test report',
                        reportDir: 'build/reports/tests/test',
                        keepAll: true,
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                    ]
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage('Push Docker image') {
            steps {
                sh 'docker push $DOCKER_IMAGE'
            }
        }
        stage("Deploy from latest docker image") {
            steps {
                sh 'docker rm -f $DOCKER_CONTAINER'
                sh 'docker run --name $DOCKER_CONTAINER -dp $ENV_PORT:$APP_PORT $DOCKER_IMAGE'
            }
        }
    }
}
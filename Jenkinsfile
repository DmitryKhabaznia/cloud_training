pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'dmitrykh/cloud_training'
        DOCKER_CONTAINER = 'cloud_training'
        ENV_PORT = 80
        APP_PORT = 8080
        INSTANCE_NAME = 'main_app'
        AWS_DEFAULT_REGION='eu-west-1'
        AWS_ACCESS_KEYS = credentials('aws_cred')
    }
    stages {
        stage('Build and create jar') {
            steps {
                sh 'chmod +x gradlew && ./gradlew bootJar'
            }
        }
        stage('Run test + test report') {
            steps {
                echo 'Running tests'
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
                echo "Building docker image"
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage('Push Docker image') {
            steps {
                echo "Push docker image"
                sh 'docker push $DOCKER_IMAGE'
            }
        }
        stage("Deploy from latest docker image") {
            steps {
                script {
                    def main_app = sh (returnStdout: true, script: """
                             aws ec2 describe-instances \
                                 --filter \"Name=tag:Name,Values=main_app\" \
                                 --query \"Reservations[*].Instances[*].[PublicIpAddress]\" \
                                 --output text | cat
                         """).trim()
                    echo "$main_app"
                    sh "ssh ubuntu@$main_app docker rm -f $DOCKER_CONTAINER || true"
                    sh "ssh ubuntu@$main_app docker run --name $DOCKER_CONTAINER -dp $ENV_PORT:$APP_PORT $DOCKER_IMAGE"

                    sh 'docker rm -f $DOCKER_CONTAINER || true'
                    sh 'docker run --name $DOCKER_CONTAINER -dp $ENV_PORT:$APP_PORT $DOCKER_IMAGE'
                }
            }
        }
    }
}
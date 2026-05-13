pipeline {
    agent any

    options {
        timeout(time: 15, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/LeticialMoraes/S107.git'
            }
        }

        stage('Build Image') {
            steps {
                sh 'docker build -t s107-tests .'
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                    docker run --rm \
                        -v $(pwd)/reports:/app/reports \
                        s107-tests
                '''
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'report.html',
                    reportName: 'Pytest Report'
                ])
            }
        }
    }

    post {
        always {
            sh 'docker rmi s107-tests || true'
        }
        success {
            echo 'Testes passaram!'
        }
        failure {
            echo 'Testes falharam!'
        }
    }
}
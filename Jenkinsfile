pipeline {
    agent any

    tools {
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw test'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw -DskipTests clean package'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'BUILD SUCCESSFUL!'
        }
    }
}
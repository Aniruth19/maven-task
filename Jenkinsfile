pipeline {
    agent any
    tools {
        maven 'maven-3.9.9'
    }
    environment {
        SONAR_HOST_URL = 'http://localhost:9000'  
        SONAR_AUTH_TOKEN = credentials('sonar-token') 
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') { 
                    bat 'mvn sonar:sonar -Dsonar.projectKey=maven-task-automation -Dsonar.projectName="maven-task-automation" -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.token=%SONAR_AUTH_TOKEN%'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
        }
    }
}

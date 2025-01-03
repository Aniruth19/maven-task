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
                    bat 'mvn clean verify sonar:sonar "-Dsonar.projectKey=maven-task-automation" "-Dsonar.projectName=maven-task-automation" "-Dsonar.host.url=http://localhost:9000" "-Dsonar.token=sqp_52cf26516cba7181d86475a979d29d0c9b7193d0"'
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

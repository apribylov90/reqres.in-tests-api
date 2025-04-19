pipeline {
    agent {
        label 'rest'
    }

    tools {
        maven 'maven-399'
        allure 'allure-commandline'
    }

    stages {
        stage('github') {
            steps {
                git credentialsId: '1c3c642f-c3f4-4496-89e4-8759c2a81671', url: 'https://github.com/apribylov90/reqres.in-tests-api'
            }
        }

        stage('run-test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('generate-allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }

    }
}
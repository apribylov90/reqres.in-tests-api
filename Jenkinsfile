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
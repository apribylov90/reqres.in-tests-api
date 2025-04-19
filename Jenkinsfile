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

        stage('Restore Allure History') {
            steps {
                script {
                    def oldHistory = 'history-backup'
                    def newHistory = 'target/allure-results/history'

                    if (fileExists(oldHistory)) {
                        sh "mkdir -p ${newHistory}"
                        sh "cp -r ${oldHistory}/* ${newHistory}/"
                    }
                }
            }
        }

        stage('generate-allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }

    }

    post {
        always {
            script {
                sh 'rm -f allure-report.zip || true'
                // Save current history for next build
                sh 'mkdir -p history-backup'
                sh 'cp -r target/allure-report/history/* history-backup/ || true'
            }

            archiveArtifacts artifacts: 'history-backup/**', fingerprint: true
        }
    }
}
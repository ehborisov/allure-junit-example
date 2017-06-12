pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh './mvnw -Dmaven.test.failure.ignore=true clean verify'
            }
        }
        stage("Reports") {
            steps {
                script {
                            allure([results: [[path: 'target/allure-results']]])
                    }
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}

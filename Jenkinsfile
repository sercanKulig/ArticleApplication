pipeline {
    agent any

    stages {

        stage ('Checkout Stage') {
            steps {
                git url: 'https://github.com/sercanKulig/ArticleApplication.git', branch: 'master'
            }
        }

        stage ('Compile Stage') {
            steps {
                withMaven(maven: 'Maven_3_5_2') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {
            steps {
                withMaven(maven: 'Maven_3_5_2') {
                    bat 'mvn test'
                }
            }
        }

        stage ('Install Stage') {
            steps {
                withMaven(maven: 'Maven_3_5_2') {
                    bat 'mvn clean install'
                }
            }
        }

        stage ('Docker Image Stage') {
            steps {
                script {
                    powershell 'docker build -f Dockerfile -t article-application .'
                    powershell 'docker image prune -f'
                }
            }
        }

        stage ('Docker Run Stage') {
            steps {
                script {
                    powershell 'docker restart article-application .'
                }
            }
        }
    }
}
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
                docker.build("-f Dockerfile -t article-application .")
            }
        }

        stage ('Docker Run Stage') {
            steps {
                docker.stop("article-application")
                docker.run("-p 9090:9090 --name article-application --link mysql-standalone:mysql -d concretepage")
            }
        }
    }
}
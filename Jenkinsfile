pipeline {
    agent any

    stages {

        stage ('Checkout') {
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

                    def pom = readMavenPom file:'pom.xml'
                    print pom.version
                    env.version = pom.version
                }
            }
        }
    }
}
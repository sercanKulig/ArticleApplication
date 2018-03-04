pipeline {
    agent any

    stages {
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
    }
}
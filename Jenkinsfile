pipeline {
    agent any
    
    stages {
        stage('Compile') {
            steps {
                gradlew('clean', 'classes')
                gradlew('compileJava')
                gradlew('compileTest')
            }
        }
        stage('Unit Tests') {
            steps {
                sh './gradlew check'
            }
            post {
                always {
                    junit 'build/test-results/**/*.xml'
                }
            }
        }
        stage('Code Analysis') {
            
            steps {
                gradlew('sonarqube')
            }
        }
        stage('Assemble') {
            steps {
                gradlew('assemble')
                stash includes: '**/build/libs/*.jar', name: 'app'
            }
        }
        stage('Promotion') {
            steps {
                gradlew('bootWar')
                sh 'cp /build/libs/supermercado-*-SNAPSHOT.war /opt/tomcat9/webapps/'
            }
        }
       
    }
    post {
        failure {
            mail to: 'carlos.sepulveda.a@usach.cl', subject: 'Build failed', body: 'Please fix!'
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}

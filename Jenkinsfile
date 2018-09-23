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
                    junit 'build/reports/**/*.xml'
                }
            }
        }
        stage('Long-running Verification') {
            
            parallel {
                stage('Integration Tests') {
                    steps {
                        gradlew('integrationTest')
                    }
                    post {
                        always {
                            junit '**/build/test-results/integrationTest/TEST-*.xml'
                        }
                    }
                }
                stage('Code Analysis') {
                    steps {
                        gradlew('sonarqube')
                    }
                }
            }
        }
        stage('Assemble') {
            steps {
                gradlew('assemble')
                stash includes: '**/build/libs/*.war', name: 'app'
            }
        }
        stage('Promotion') {
            steps {
                timeout(time: 1, unit:'DAYS') {
                    input 'Deploy to Production?'
                }
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

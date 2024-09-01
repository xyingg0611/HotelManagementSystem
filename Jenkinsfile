pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/chongbao01/HotelManagementSystem.git'
            }
        }
        stage('Build') {
            steps {

                    bat ‘start gradlew build’
                
            }
        }
        stage('Test') {
            steps {
                
                     bat ‘start gradlew test’
                  
            }
        }
        stage('Deploy') {
            steps {                
                    powershell 'java -jar build/libs/HotelManagementSystem.jar'
                 }           
        }
    
}

post {
        always {
            echo 'Cleaning up workspace'
            deleteDir() // Clean up the workspace after the build vvvvggg
        }
        success {
            echo 'Build succeeded!!'
            // You could add notification steps here, e.g., send an email
        }
        failure {
            echo 'Build failed!!'
            // You could add notification steps here, e.g., send an email or Slack message
        }
    }
}
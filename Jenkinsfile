pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/chongbao01/HotelManagementSystem.git'
            }
        }
        stages{
            stage('Build') {
            steps {

                    bat 'gradle build'
                
            }
        }
        }
        stage('Test') {
            steps {
                
                     bat 'gradle test'
                  
            }
        }
        stage('Deploy') {
            steps {                
                    script {
                    // Build the Docker image
                    docker.build('hotelmanagement:latest', '-f Dockerfile .')
                    
                    // Run the Docker container
                    docker.image('hotelmanagement:latest').run(
                        '-p 8080:8080 --name hotelmanagement-container'
                    )
                }
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
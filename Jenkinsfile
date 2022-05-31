pipeline {
	agent any
	
	environment {
		DOCKERHUB_CREDENTIALS=credentials('DOCKER_CRED')
	}
	
	tools {
		maven "3.6.0"
	}
	
	stages {
		stage("Build") {
			steps {
				bat "mvn -version"
				bat "mvn clean install"
			}
		}
		
		stage("Build Docker image") {
			steps {
				bat "docker build -t vo43sl/twitter-telegram-bot:latest ."
			}
		}
		
		stage("Login") {
			steps {
				bat "echo $DOCKERHUB_CREDENTIALS | docker login -u vo43sl -p Ramreddy@2000"
			}
		}
		
		stage("Push to DockerHub") {
			steps {
				bat "docker push vo43sl/twitter-telegram-bot:latest"
			}
		}
	}
	
	post {
		always {
			cleanWs()
		}
	}
	
}
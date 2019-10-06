pipeline {
    agent any

    stages{
            stage ('Build the project '){

            steps{
              def mvnHome = tool name: 'maven', type: 'maven'
                sh "${mvnHome}/bin/mvn clean compile"
             }
          }
    }

}
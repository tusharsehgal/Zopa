pipeline {
    agent any


    parameters {
        booleanParam(defaultValue: true, description: 'TestFlag', name: 'TestFlag')
    }


    stages{
            stage ('Build the project '){

            steps{
                echo "testing the stash"
                sh "pwd"
                sh "ls -la"
        def mvnHome = tool name: 'maven', type: 'maven'
                sh "${mvnHome}/bin/mvn clean compile"
             }
          }

          stage('Run tests'){
          steps{
          def mvnHome = tool name: 'maven', type: 'maven'
          sh "${mvnHome}/bin/mvn test"
          }
          }
    }

}

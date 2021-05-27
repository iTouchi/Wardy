pipeline {

agent any

    stages {

        stage('Compile Stage') {
            steps {
                withMaven(maven : 'maven_3_6_3'){
                    dir('wardy-back-end'){
                        sh 'pwd'
                        sh 'mvn clean compile'
                    }
                 }
            }   
        }

        stage('Testing Stage') {
            steps {
                withMaven(maven : 'maven_3_6_3'){
                    dir('wardy-back-end'){
                        sh 'mvn test'
                    }
                 }
            }   
        }
        
        stage('Front-end Stage') {
            steps {
                    dir('wardy-client'){
                        node(node : NodeJS){
                        bat label: '', 
                        script: '''node --version'''
                        }
                 }
            }   
        }


        




        
    }
}

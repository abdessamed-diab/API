pipeline {
  agent any
  environment {
    maven_is_installed = sh (script: "mvn -v", returnStatus: true)
  }

  stages {
    stage('checkout') {
      steps {
          echo "checkout $currentBuild.projectName from github repository"
          checkout scm: [$class: 'GitSCM', branches: [[name: 'refs/heads/feat-blog-api']], extensions: [], userRemoteConfigs: [[credentialsId: 'test', name: 'origin', refspec: 'refs/heads/feat-blog-api:refs/remotes/origin/feat-blog-api', url: 'https://github.com/abdessamed-diab/API.git']]]
      }
    }

    stage('requirements') {
      when {
        not {
          environment name: 'maven_is_installed', value: '0'
        }
        beforeAgent true
      }
      steps {
        script {
          $currentBuild.result = 'aborted'
        }
      }
      post {
        aborted {
          echo 'build was aborted due system requirements.'
        }
      }
    }

    stage('validate') {
      steps {
          echo "start validating source code."
          sh "mvn validate"
      }
    }

    stage('compile') {
      steps {
          sh "mvn compile"
      }
    }

    stage('test') {
      steps {
          sh "mvn test"
      }
    }

  }

}

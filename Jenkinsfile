pipeline {
  agent any
  environment {
    maven_is_installed = sh (script: "mvn -v", returnStatus: true)
    testResultSummary = 100
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
          currentBuild.result = 'aborted'
        }
      }
      post {
        aborted {
          error "build was aborted due system requirements."
        }
      }
    }

    stage('compile') {
      steps {
        sh "mvn compile"
      }
    }

    stage('test') {
      steps {
        sh "mvn --fail-never test"
        script {
          def summary = junit allowEmptyResults: true, healthScaleFactor: 2, testResults: 'target/surefire-reports/*.xml'
          def successPercentage = 100 -  (  ( 100 * summary.failCount / summary.totalCount) * 2  )
          if ( successPercentage < 90  ) {
            currentBuild.result = 'aborted'
            testResultSummary =  successPercentage
          }
        }
      }

      post {
        aborted {
          error "build is aborted due project health, check test success percentage: $testResultSummary"
        }
      }
    }

    stage('package') {
      steps {
        sh "mvn package site"
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site',
                  reportFiles: 'index.html', reportName: 'site report', reportTitles: "documentation for $currentBuild.projectName" ])
      }
    }


  }

}

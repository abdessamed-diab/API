pipeline {
    agent any 
    stages {
        stage('checkout') {
            steps {
                echo 'loading project from github repository'
                checkout 
                  changelog: false, 
                  scm: [
                    $class: 'GitSCM', 
                    branches: [[name: 'refs/heads/feat-blog-api']], 
                    extensions: [],
                    userRemoteConfigs: [[credentialsId: 'test', name: 'origin', refspec: 'refs/heads/feat-blog-api:refs/remotes/origin/feat-blog-api', url: 'https://github.com/abdessamed-diab/API.git']]
                  ]
            }
        }
      
        stage('compile') {
            steps {
                echo 'compiling branch $BRANCH_NAME'
            }
        }
      
    }
}

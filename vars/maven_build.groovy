def call() {
    node {
      stage('withEnv') {
        withEnv(["PATH+MAVEN=${tool 'mvn-3.6.2'}/bin"]) {
            sh script: "mvn clean test -Dmaven.test.failure.ignore=true"
        }
    }
    
      stage('report') {
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/**.exec'
      }
    }
}

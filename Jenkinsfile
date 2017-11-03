node {

    withMaven(maven:'Maven 3.3') {

        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean package'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Integration Tests') {
            sh 'mvn verify'
        }

        stage('Push') {
            /*
            dir ('hello-cd-webapp-docker') {
                def app = docker.build "repo.training.rgielen.net:6000/hello-cd-webapp-docker:${env.version}"
                app.push()
            }
            */
            docker.withRegistry('https://repo.training.rgielen.net:6000', 'ci-repo.training.rgielen.net') {
                sh 'mvn deploy'
            }
        }

        stage ('User Acceptance Tests') {
            dir ('hello-cd-ua') {
                sh 'mvn verify'
            }
        }

    }

}
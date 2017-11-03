node {

    withMaven(maven:'Maven 3.3', globalMavenSettingsConfig:'org.jenkinsci.plugins.configfiles.maven.GlobalMavenSettingsConfig1435422191538') {

        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean package'
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
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'ci-repo.training.rgielen.net', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                usr = USERNAME
                pswd = PASSWORD
            }
            docker.withRegistry('https://repo.training.rgielen.net:6000', 'ci-repo.training.rgielen.net') {
                sh "docker login -u ${usr} -p ${pswd} repo.training.rgielen.net:6000"
                sh 'mvn deploy'
            }
        }

        stage ('User Acceptance Tests') {
            dir ('hello-cd-ua') {
                sh 'mvn verify -Djenkins-docker'
            }
        }

    }

}
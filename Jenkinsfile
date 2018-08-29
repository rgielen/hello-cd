node {

    withMaven(maven:'Maven 3.5', globalMavenSettingsConfig:'training.rgielen.net-mavensettings') {

        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean package'
        }

        stage('Integration Tests') {
            sh 'mvn verify -Pintegration-test'
        }

        stage('Push') {
            /*
            dir ('hello-cd-webapp-docker') {
                def app = docker.build "repo.training.rgielen.net:6000/hello-cd-webapp-docker:${env.version}"
                app.push()
            }
            */
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'repo.training.rgielen.net-ci', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh "docker login -u ${USERNAME} -p ${PASSWORD} registry.training.rgielen.net"
                sh 'mvn deploy'
            }
            /* with plain docker push, one would use it within:
            docker.withRegistry('https://registry.training.rgielen.net', 'repo.training.rgielen.net-ci') {
            }
            */
        }

        stage ('User Acceptance Tests') {
            dir ('hello-cd-ua') {
                sh 'mvn verify -Djenkins-docker'
            }
        }

    }

}
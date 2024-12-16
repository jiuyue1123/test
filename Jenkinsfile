pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                // 运行单元测试
                sh "mvn -B clean test"
            }
        }

        stage('Build') {
            steps {
                // 构建项目并打包成jar/war文件
                sh "mvn -B clean package -DskipTests"
            }
        }

        stage('Deploy to Dev') {
            steps {
                // 将构建好的文件部署到开发环境
                script {
                    def jarFileName = sh(script: "ls target/*.jar", returnStdout: true).trim()
                    sj "docker run -d --name myapp-dev -v \$(pwd)/${jarFileName}:/app.jar openjdk:17-jdk-slim java -jar /app.jar"
                }
            }
        }
    }

    post {
        always {
            // 清理工作空间
            cleanWs()
        }
        success {
            // 成功时的操作
            echo 'Deployment to Dev Environment was successful!'
        }
        failure {
            // 失败时的操作
            echo 'Deployment to Dev Environment failed!'
        }
    }
}

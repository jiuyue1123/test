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
                // 构建项目并打包成jar文件
                sh "mvn -B clean package -DskipTests"
            }
        }

        stage('Deploy to Dev') {
            steps {
                script {
                    // 获取构建好的 JAR 文件名
                    def jarFileName = sh(script: "ls target/*.jar", returnStdout: true).trim()
                    // 检查是否存在同名容器，如果存在则暂停
                    def container = sh(script: "docker ps -aq --filter name=myapp-dev", returnStdout: true).trim()
                    if (container) {
                        sh "docker stop myapp-dev"
                    }
                    // 运行新的容器
                    sh "docker run --rm -d --name myapp-dev -p8077:8080 -v \$(pwd)/${jarFileName}:/app.jar openjdk:17-jdk-slim java -jar /app.jar"
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
# 使用 openjdk:17-jdk-slim 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制项目文件到容器内
COPY target/app.jar /app.jar

# 暴露应用程序端口
EXPOSE 8080

# 运行应用程序
CMD ["java", "-jar", "/app.jar"]
## 指定所需依赖的基础镜像
FROM adoptopenjdk/openjdk11:alpine
## VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
VOLUME /tmp
## 将当前目录下的jar包复制到docker容器的根目录下
## 若不存在该目录Docker会自动创建该目录
ADD target/pigeon_server-0.0.1-SNAPSHOT.jar app.jar
## 声明服务运行在8080端口
EXPOSE 8080
## 指定docker容器启动时运行jar包
ENTRYPOINT ["nohup","java","-jar","/app.jar" ,"-spring.profiles.active=release"]


#!/bin/bash

cd ../quarkus-jackson || exit 1
mvn clean package -Dquarkus.package.type=fast-jar
docker build -f src/main/docker/Dockerfile.fast-jar -t quarkus-vs-sb-performance:quarkus-jvm .
docker tag quarkus-vs-sb-performance:quarkus-jvm tomekzar/quarkus-vs-spring-boot-performance:quarkus-jvm

mvn clean package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t quarkus-vs-sb-performance:quarkus-native .
docker tag quarkus-vs-sb-performance:quarkus-native tomekzar/quarkus-vs-spring-boot-performance:quarkus-native

cd ../quarkus-jackson-mutiny || exit 1
mvn clean package -Dquarkus.package.type=fast-jar
docker build -f src/main/docker/Dockerfile.fast-jar -t quarkus-vs-sb-performance:quarkus-mutiny-jvm .
docker tag quarkus-vs-sb-performance:quarkus-mutiny-jvm tomekzar/quarkus-vs-spring-boot-performance:quarkus-mutiny-jvm

mvn clean package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t quarkus-vs-sb-performance:quarkus-mutiny-native .
docker tag quarkus-vs-sb-performance:quarkus-mutiny-native tomekzar/quarkus-vs-spring-boot-performance:quarkus-mutiny-native

cd ../spring-boot || exit 1
mvn clean package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-vs-sb-performance:spring-boot-jvm .
docker tag quarkus-vs-sb-performance:spring-boot-jvm tomekzar/quarkus-vs-spring-boot-performance:spring-boot-jvm

cd ../spring-boot-webflux || exit 1
mvn clean package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-vs-sb-performance:spring-boot-webflux-jvm .
docker tag quarkus-vs-sb-performance:spring-boot-webflux-jvm tomekzar/quarkus-vs-spring-boot-performance:spring-boot-webflux-jvm
